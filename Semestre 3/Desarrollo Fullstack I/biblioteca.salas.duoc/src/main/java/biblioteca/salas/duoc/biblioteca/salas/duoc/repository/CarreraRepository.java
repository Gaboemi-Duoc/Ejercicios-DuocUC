package biblioteca.salas.duoc.biblioteca.salas.duoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblioteca.salas.duoc.biblioteca.salas.duoc.modelo.Carrera;
@Repository
public interface CarreraRepository extends JpaRepository<Carrera,String> {

}
