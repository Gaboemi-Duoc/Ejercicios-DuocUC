package com.example.demoDB;



import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.demoDB.model.Comuna;
import com.example.demoDB.model.Paciente;
import com.example.demoDB.repository.ComunaRepository;
import com.example.demoDB.repository.PacienteRepository;

import net.datafaker.Faker;
@Profile("dev") //debe declararse en el application.properties como  
//spring.profiles.active=dev
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    
     

    @Override
    public void run(String... args) throws Exception{

        Faker faker = new Faker();
        Random random = new Random();
        //para comuna
        for (int i = 0; i < 50; i++) {
            //crear el objeto comuna
            Comuna comuna = new Comuna();
            //settear todos los atributos 
            comuna.setNombre(faker.address().city());
            //usar el save de repositorio de comuna, pasandole el objeto creado
            comunaRepository.save(comuna);
        }

        //para paciente
        for (int i = 0; i < 1000; i++) {
            //crear el objeto paciente
            Paciente paciente = new Paciente();
            //settear todos los datos   nombre y fk
            paciente.setNombre(faker.name().fullName());
            paciente.setComuna(comunaRepository.findAll().get(random.nextInt(50)));
            //usar el save de repositorio para almacenar
            pacienteRepository.save(paciente);

        }
    }
}
