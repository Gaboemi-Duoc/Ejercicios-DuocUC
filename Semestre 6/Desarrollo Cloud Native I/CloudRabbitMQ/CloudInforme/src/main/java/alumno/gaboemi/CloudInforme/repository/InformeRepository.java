package alumno.gaboemi.CloudInforme.repository;

import alumno.gaboemi.CloudInforme.domain.Informe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InformeRepository extends JpaRepository<Informe, Long> {
    Optional<Informe> findByRequestId(String requestId);
    boolean existsByRequestId(String requestId);
}