import sqlite3
import hashlib

# Conexión a la base de datos (se creará automáticamente si no existe)
conn = sqlite3.connect('database.db')

# Crear un cursor
c = conn.cursor()

# Crear la tabla de usuarios
c.execute('''
    CREATE TABLE users (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        username TEXT NOT NULL,
        password TEXT NOT NULL,
        role TEXT NOT NULL
    )
''')

# Función para hash de contraseñas


def hash_password(password):
    return hashlib.sha256(password.encode()).hexdigest()


# Insertar usuarios de prueba (las contraseñas están en SHA256 de 'password')
c.execute('''
    INSERT INTO users (username, password, role) VALUES
    ('admin', ?, 'admin'),
    ('user', ?, 'user')
''', (hash_password('password'), hash_password('password')))

# Crear la tabla de tareas
c.execute('''
    CREATE TABLE tasks (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_id INTEGER NOT NULL,
        task TEXT NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users (id)
    )
''')

# Guardar los cambios y cerrar la conexión
conn.commit()
conn.close()

print("Base de datos y tablas creadas con éxito.")
