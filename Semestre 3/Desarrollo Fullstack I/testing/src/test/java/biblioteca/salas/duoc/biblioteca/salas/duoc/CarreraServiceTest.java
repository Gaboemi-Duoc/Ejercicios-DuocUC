package biblioteca.salas.duoc.biblioteca.salas.duoc;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.CarreraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CarreraServiceTest {

    // Inyecta el servicio de Carrera para ser probado.
    @Autowired
    private CarreraService carreraService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private CarreraRepository carreraRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con una Carrera.
        when(carreraRepository.findAll()).thenReturn(List.of(new Carrera("1", "Ingeniería")));

        // Llama al método findAll() del servicio.
        List<Carrera> carreras = carreraService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(carreras);
        assertEquals(1, carreras.size());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "1";
        Carrera carrera = new Carrera(codigo, "Ingeniería");

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve una Carrera opcional.
        when(carreraRepository.findById(codigo)).thenReturn(Optional.of(carrera));

        // Llama al método findByCodigo() del servicio.
        Carrera found = carreraService.findByCodigo(codigo);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el código esperado.
        assertNotNull(found);
        assertEquals(codigo, found.getCodigo());
    }

    @Test
    public void testSave() {
        Carrera carrera = new Carrera("1", "Ingeniería");

        // Define el comportamiento del mock: cuando se llame a save(), devuelve la Carrera proporcionada.
        when(carreraRepository.save(carrera)).thenReturn(carrera);

        // Llama al método save() del servicio.
        Carrera saved = carreraService.save(carrera);

        // Verifica que la Carrera guardada no sea nula y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Ingeniería", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "1";

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(carreraRepository).deleteById(codigo);

        // Llama al método deleteByCodigo() del servicio.
        carreraService.deleteByCodigo(codigo);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(carreraRepository, times(1)).deleteById(codigo);
    }
}
