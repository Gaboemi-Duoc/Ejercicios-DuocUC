package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.modelo.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarreraService {
    @Autowired
    private CarreraRepository carreraRepository;
    
    public List<Carrera> findAll(){
        return carreraRepository.findAll();
    }

    public Carrera findById(String id){
        return carreraRepository.findById(id).get();
    }

    public Carrera save(Carrera carrera){
        return carreraRepository.save(carrera);
    }

    public void deleteById(String id){
        carreraRepository.deleteById(id);
    }


}
