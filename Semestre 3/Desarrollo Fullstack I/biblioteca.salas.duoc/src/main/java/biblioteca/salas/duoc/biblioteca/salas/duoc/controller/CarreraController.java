package biblioteca.salas.duoc.biblioteca.salas.duoc.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import biblioteca.salas.duoc.biblioteca.salas.duoc.modelo.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.CarreraService;

import java.util.List;

@RestController
@RequestMapping("/api/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public List<Carrera> getAll() {
        return carreraService.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Carrera> getById(@PathVariable String codigo) {
        Carrera carrera = carreraService.findById(codigo);
        if (carrera == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrera);
    }

    @PostMapping
    public Carrera create(@RequestBody Carrera carrera) {
        return carreraService.save(carrera);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Carrera> update(@PathVariable String codigo, @RequestBody Carrera carreraDetails) {
        Carrera carrera = carreraService.findById(codigo);
        if (carrera == null) {
            return ResponseEntity.notFound().build();
        }
        carrera.setNombre(carreraDetails.getNombre());
        // Si quieres actualizar estudiantes, manejar aparte
        Carrera updated = carreraService.save(carrera);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable String codigo) {
        Carrera carrera = carreraService.findById(codigo);
        if (carrera == null) {
            return ResponseEntity.notFound().build();
        }
        carreraService.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
