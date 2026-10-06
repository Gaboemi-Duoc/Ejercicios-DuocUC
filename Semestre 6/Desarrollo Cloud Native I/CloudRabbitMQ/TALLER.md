# Taller: Spring Boot + RabbitMQ (fanout) + SQLite

Flujo final:

```
POST /mensajes ──► exchange "mensajes.fanout" ──┬─► cola "mensajes" ──► listener ──► log + SQLite
   (Postman)          (fanout, sin routing key) └─► cola "test"     ──► listener ──► log
```

## Objetivos

- Levantar RabbitMQ con Docker Compose.
- Crear un proyecto Spring Boot con Spring Initializr.
- Publicar mensajes a un **exchange** y consumirlos desde **dos colas**.
- Persistir los mensajes (UUID, mensaje, fecha) en SQLite.
- Verificar con Postman, logs y la base de datos.

## Conceptos previos (vienen de SQS)

| SQS | RabbitMQ |
|---|---|
| El producer envía **directo a la cola** | El producer publica a un **exchange**; este enruta a las colas |
| Cola | Cola (almacena hasta que el consumer lee) |
| SNS → varias SQS | Exchange **fanout** → varias colas |

- **Exchange:** recibe mensajes y decide a qué cola(s) van. No almacena nada.
- **Binding:** regla que une un exchange con una cola.
- **Fanout:** copia el mensaje a **todas** las colas con binding; ignora la routing key.
- **Bean:** objeto creado y gestionado por Spring (inyección de dependencias).

## Prerrequisitos

- Docker Desktop
- JDK 21
- Postman
- Un cliente SQLite (DB Browser for SQLite, DBeaver, o el CLI `sqlite3`)

---

## Paso 1. Levantar RabbitMQ

Crea una carpeta de trabajo `rabbitmq/` y dentro `docker-compose.yml`:

```yaml
services:
  rabbitmq:
    image: rabbitmq:4.3.6-management
    container_name: rabbitmq-server
    ports:
      - "5672:5672"     # AMQP (la app)
      - "15672:15672"   # UI de administración
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
```

```bash
docker compose up -d
```

Verifica: abre <http://localhost:15672> (usuario `guest`, clave `guest`).

## Paso 2. Crear exchange, colas y bindings (UI)

En <http://localhost:15672>:

1. **Exchanges** → *Add a new exchange*
   - Name: `mensajes.fanout`
   - Type: `fanout`
   - Durability: `Durable`
2. **Queues and Streams** → *Add a new queue* (dos veces)
   - Type: `Classic`, Durability: `Durable`
   - Names: `mensajes` y `test`
3. **Exchanges** → `mensajes.fanout` → *Bindings* → *Add binding from this exchange*
   - To queue: `mensajes` → *Bind* (routing key vacía)
   - To queue: `test` → *Bind*

Verifica: en la vista del exchange deben aparecer ambas colas en *Bindings*.

> Sin binding, el exchange descarta el mensaje **en silencio** (no hay error).

## Paso 3. Crear el proyecto (Spring Initializr)

Abre <https://start.spring.io> con:

| Campo | Valor |
|---|---|
| Project | Gradle - Groovy |
| Language | Java |
| Spring Boot | 4.1.x (la estable más reciente) |
| Group | `com.clouduno` |
| Artifact / Name | `emisor` |
| Packaging | Jar |
| Java | 21 |

Dependencies:

- Spring Web
- Spring for RabbitMQ
- Spring Data JPA
- Spring Boot DevTools (opcional, ver Paso 9)

*Generate*, descomprime dentro de `rabbitmq/` (queda `rabbitmq/emisor/`).

## Paso 4. Dependencias de SQLite

Initializr no incluye SQLite. Agrega en `emisor/build.gradle`, bloque `dependencies`:

```groovy
runtimeOnly 'org.xerial:sqlite-jdbc'
runtimeOnly 'org.hibernate.orm:hibernate-community-dialects'
```

Resultado esperado del bloque:

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-amqp'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-webmvc'
    developmentOnly 'org.springframework.boot:spring-boot-devtools' // solo develop mode
    runtimeOnly 'org.xerial:sqlite-jdbc'
    runtimeOnly 'org.hibernate.orm:hibernate-community-dialects'
    testImplementation 'org.springframework.boot:spring-boot-starter-amqp-test'
    testImplementation 'org.springframework.boot:spring-boot-starter-data-jpa-test'
    testImplementation 'org.springframework.boot:spring-boot-starter-webmvc-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

## Paso 5. Estructura del proyecto

```
emisor/src/main/java/com/clouduno/emisor/
├── EmisorApplication.java          (generado)
├── config/
│   └── RabbitConfig.java
├── health/
│   └── HealthController.java
└── mensaje/
    ├── MensajeController.java
    ├── MensajeListener.java
    ├── models/
    │   └── Mensaje.java
    └── repositories/
        └── MensajeRepository.java
emisor/src/main/resources/application.properties
```

> Dos clases llamadas `Controller` en paquetes distintos **chocan** (el nombre de bean por defecto es el mismo). Usa nombres únicos: `HealthController`, `MensajeController`.

## Paso 6. Código

### `application.properties`

```properties
spring.application.name=emisor

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

spring.datasource.url=jdbc:sqlite:./miapp.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.properties.hibernate.dialect=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update

# Mostrar y formatear el SQL en consola
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### `config/RabbitConfig.java`

Solo constantes: exchange y colas ya existen (creadas en la UI).

```java
package com.clouduno.emisor.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE = "mensajes";
    public static final String TEST_QUEUE = "test";
    public static final String FANOUT_EXCHANGE = "mensajes.fanout";
}
```

> Alternativa: declarar exchange, colas y bindings con `@Bean` (`FanoutExchange`, `Queue`, `Binding`) para que la app los cree al arrancar. Si lo haces, **no** los crees en la UI, o fallará con `PRECONDITION_FAILED` si los argumentos difieren.

### `health/HealthController.java`

```java
package com.clouduno.emisor.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}
```

### `mensaje/models/Mensaje.java`

```java
package com.clouduno.emisor.mensaje.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Mensaje {
    @Id private String id = UUID.randomUUID().toString();
    private String message;
    private LocalDateTime fecha = LocalDateTime.now();

    protected Mensaje() {}
    public Mensaje(String message) { this.message = message; }

    public String getId() { return id; }
    public String getMessage() { return message; }
    public LocalDateTime getFecha() { return fecha; }
    public void setId(String id) { this.id = id; }
    public void setMessage(String message) { this.message = message; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
```

### `mensaje/repositories/MensajeRepository.java`

```java
package com.clouduno.emisor.mensaje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clouduno.emisor.mensaje.models.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, String> {}
```

### `mensaje/MensajeController.java` (producer)

```java
package com.clouduno.emisor.mensaje;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clouduno.emisor.config.RabbitConfig;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    private final RabbitTemplate rabbit;

    public record MensajeRequest(String message) {}

    public MensajeController(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @PostMapping
    public ResponseEntity<Void> enviar(@RequestBody MensajeRequest req) {
        // (exchange, routingKey, mensaje). En fanout la routing key se ignora, va ""
        rabbit.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "", req.message());
        return ResponseEntity.accepted().build();
    }
}
```

> Error común: `convertAndSend(FANOUT_EXCHANGE, msg)` usa la sobrecarga `(routingKey, mensaje)` y publica al *default exchange*. El mensaje se pierde sin error. Siempre usa **3 argumentos**.

### `mensaje/MensajeListener.java` (consumers)

```java
package com.clouduno.emisor.mensaje;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.clouduno.emisor.config.RabbitConfig;
import com.clouduno.emisor.mensaje.models.Mensaje;
import com.clouduno.emisor.mensaje.repositories.MensajeRepository;

@Component
public class MensajeListener {

    private final MensajeRepository repo;

    public MensajeListener(MensajeRepository repo) {
        this.repo = repo;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void recibir(String message) {
        Mensaje m = repo.save(new Mensaje(message));
        System.out.println("Recibido: " + m.getId() + " | " + m.getFecha() + " | " + message);
    }

    @RabbitListener(queues = RabbitConfig.TEST_QUEUE)
    public void recibirTest(String message) {
        System.out.println("Recibido en test: " + message);
    }
}
```

## Paso 7. Ejecutar y probar en local

Con RabbitMQ arriba (Paso 1):

```bash
cd emisor
./gradlew bootRun
```

En otra terminal:

```bash
curl localhost:8080/health
# OK
```

### Enviar con Postman

- Método: `POST`
- URL: `http://localhost:8080/mensajes`
- Headers: `Content-Type: application/json`
- Body → raw → JSON:

```json
{ "message": "hola desde postman" }
```

Respuesta esperada: `202 Accepted`.

### Validar

**1. Logs de la app** (terminal de `bootRun`):

```
Recibido: 3f1c...-uuid | 2026-10-06T10:15:30.123 | hola desde postman
Recibido en test: hola desde postman
```

Dos líneas: el fanout copió el mensaje a **ambas colas**.

**2. UI de RabbitMQ** → *Queues*: `mensajes` y `test` con *Ready = 0* (ya consumidos). Al detener la app y enviar mensajes, *Ready* sube; al reiniciar, se consumen.

**3. SQLite** (el archivo `emisor/miapp.db`):

```bash
sqlite3 miapp.db "SELECT * FROM mensaje;"
```

O ábrelo con DB Browser for SQLite / DBeaver. Debe existir **una fila** por mensaje (solo la cola `mensajes` guarda; `test` solo imprime), con `id` (UUID), `message` y `fecha`.

## Paso 8. Dockerizar la app

### `emisor/Dockerfile`

```dockerfile
# --- Etapa 1: compilación con Gradle ---
FROM gradle:jdk-21-and-22-alpine AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradlew ./
COPY gradle ./gradle
COPY src ./src
RUN ./gradlew bootJar -x test

# --- Etapa 2: imagen final ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Usuario sin privilegios + carpeta /data con permisos (SQLite necesita escribir)
RUN addgroup -S spring && adduser -S spring -G spring \
    && mkdir /data && chown spring:spring /data
USER spring:spring

COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
```

> Sin el `mkdir /data && chown`, el volumen queda como `root` y la app falla con `SQLITE_CANTOPEN`.

### `docker-compose.yml` completo

```yaml
services:
  rabbitmq:
    image: rabbitmq:4.3.6-management
    container_name: rabbitmq-server
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
    networks:
      - red-emisor

  emisor-app:
    build: ./emisor
    container_name: spring-emisor-app
    ports:
      - "8080:8080"
    depends_on:
      - rabbitmq
    volumes:
      - sqlite_data:/data
    environment:
      # Dentro de la red de Docker el host es el nombre del servicio
      - SPRING_RABBITMQ_HOST=rabbitmq
      - SPRING_RABBITMQ_PORT=5672
      - SPRING_RABBITMQ_USERNAME=guest
      - SPRING_RABBITMQ_PASSWORD=guest
      - SPRING_DATASOURCE_URL=jdbc:sqlite:/data/miapp.db
      - SPRING_DATASOURCE_DRIVER-CLASS-NAME=org.sqlite.JDBC
      - SPRING_JPA_DATABASE-PLATFORM=org.hibernate.community.dialect.SQLiteDialect
      - SPRING_JPA_HIBERNATE_DDL-AUTO=update
    networks:
      - red-emisor

volumes:
  sqlite_data:

networks:
  red-emisor:
    driver: bridge
```

Levantar:

```bash
docker compose up --build -d
docker compose logs -f emisor-app     # espera "Started EmisorApplication"
curl localhost:8080/health
```

Enviar con Postman (igual que el Paso 7) y validar:

```bash
docker compose logs -f emisor-app     # líneas "Recibido: ..."
```

### Ver SQLite dentro de Docker

La imagen JRE no trae `sqlite3`. Copia el archivo al host:

```bash
docker cp spring-emisor-app:/data/miapp.db ./miapp-docker.db
sqlite3 miapp-docker.db "SELECT * FROM mensaje;"
```

## Paso 9 (opcional). Hot reload con devtools

`spring-boot-devtools` es `developmentOnly`: **no** va en el JAR de producción, por eso no sirve con el `Dockerfile` anterior. Para desarrollo en Docker usa un override.

`emisor/Dockerfile.dev`:

```dockerfile
# SOLO DEVELOP MODE
FROM gradle:jdk-21-and-22-alpine
WORKDIR /app
EXPOSE 8080 35729
CMD ["gradle", "bootRun", "--continuous", "-x", "test"]
```

`docker-compose.dev.yml`:

```yaml
# SOLO DEVELOP MODE
services:
  emisor-app:
    build:
      context: ./emisor
      dockerfile: Dockerfile.dev
    ports:
      - "35729:35729"
    volumes:
      - ./emisor:/app
      - gradle_cache:/home/gradle/.gradle
    environment:
      - SPRING_DEVTOOLS_RESTART_POLLING-INTERVAL=1s
      - SPRING_DEVTOOLS_RESTART_QUIET-PERIOD=500ms

volumes:
  gradle_cache:
```

```bash
docker compose -f docker-compose.yml -f docker-compose.dev.yml up --build
```

Edita un archivo en `src/` y observa el reinicio automático en los logs.

---

## Solución de problemas

| Síntoma | Causa | Solución |
|---|---|---|
| `curl: (7) Failed to connect` | La app aún arranca o crasheó | `docker compose logs --tail 50 emisor-app` |
| `ConflictingBeanDefinitionException: 'controller'` | Dos clases `Controller` | Renombrar (`HealthController`, `MensajeController`) |
| `SQLITE_CANTOPEN` en Docker | `/data` es de `root` | `mkdir /data && chown` en el Dockerfile; `docker compose down -v` y reconstruir |
| POST responde `202` pero no llega nada | Falta binding, o `convertAndSend` con 2 argumentos | Revisar bindings en la UI; usar `(exchange, "", msg)` |
| `PRECONDITION_FAILED` al arrancar | Cola/exchange creado en UI con args distintos a los `@Bean` | Usar solo UI **o** solo `@Bean` |
| `Connection refused` a RabbitMQ en local | RabbitMQ no está arriba | `docker compose up -d rabbitmq` |

## Desafíos

1. Agrega un `GET /mensajes` que liste lo guardado en SQLite.
2. Cambia el exchange a tipo `direct` con dos routing keys (`mensajes`, `test`) y envía a una sola cola.
3. Agrega un volumen para RabbitMQ y verifica que exchange y colas sobrevivan a `docker compose down`.
4. Declara exchange, colas y bindings con `@Bean` en vez de la UI.
