package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.TipoSalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoSalaService {
    @Autowired
    private TipoSalaRepository tipoSalaRepository;

    public List<TipoSala> findAll() {
        return tipoSalaRepository.findAll();
    }

    public TipoSala findById(Integer id) {
        return tipoSalaRepository.findById(id).orElse(null);
    }

    public TipoSala save(TipoSala tipoSala) {
        return tipoSalaRepository.save(tipoSala);
    }

    public void deleteById(Integer id) {
        tipoSalaRepository.deleteById(id);
    }
}
