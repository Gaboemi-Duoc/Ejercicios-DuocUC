package biblioteca.salas.duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.*;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private TipoSalaRepository tipoSalaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar tipos de sala
        for (int i = 0; i < 3; i++) {
            TipoSala tipoSala = new TipoSala();
            tipoSala.setIdTipo(i + 1);
            tipoSala.setNombre(faker.book().genre());
            tipoSalaRepository.save(tipoSala);
        }

        // Generar carreras
        for (int i = 0; i < 5; i++) {
            Carrera carrera = new Carrera();
            carrera.setCodigo(faker.code().asin());
            carrera.setNombre(faker.educator().course());
            carreraRepository.save(carrera);
        }

        List<Carrera> carreras = carreraRepository.findAll();

        // Generar estudiantes
        for (int i = 0; i < 50; i++) {
            Estudiante estudiante = new Estudiante();
            estudiante.setId(i + 1);
            estudiante.setRun(faker.idNumber().valid());
            estudiante.setNombres(faker.name().fullName());
            estudiante.setCorreo(faker.internet().emailAddress());
            estudiante.setJornada(faker.options().option("D", "N").charAt(0));
            estudiante.setTelefono(faker.number().numberBetween(100000000, 999999999));
            estudiante.setCarrera(carreras.get(random.nextInt(carreras.size())));
            estudianteRepository.save(estudiante);
        }

        // Generar salas
        for (int i = 0; i < 10; i++) {
            Sala sala = new Sala();
            sala.setCodigo(i + 1);
            sala.setNombre(faker.university().name());
            sala.setCapacidad(faker.number().numberBetween(10, 100));
            sala.setIdInstituo(faker.number().randomDigit());
            sala.setTipoSala(tipoSalaRepository.findAll().get(random.nextInt(3)));
            salaRepository.save(sala);
        }

        List<Estudiante> estudiantes = estudianteRepository.findAll();
        List<Sala> salas = salaRepository.findAll();

        // Generar reservas
        for (int i = 0; i < 20; i++) {
            Reserva reserva = new Reserva(1,  new Date(), new Date(), new Date(), 1, new Estudiante(), new Sala());
            reserva.setId(i + 1);
            reserva.setEstudiante(estudiantes.get(random.nextInt(estudiantes.size())));
            reserva.setSala(salas.get(random.nextInt(salas.size())));
            reserva.setFechaSolicitada(new Date());
            reserva.setHoraSolicitada(new Date());
            reserva.setHoraCierre(new Date(System.currentTimeMillis() + faker.number().numberBetween(3600000, 7200000))); // 1-2 horas más
            reserva.setEstado(faker.number().numberBetween(0, 2));
            reservaRepository.save(reserva);
        }
    }
}