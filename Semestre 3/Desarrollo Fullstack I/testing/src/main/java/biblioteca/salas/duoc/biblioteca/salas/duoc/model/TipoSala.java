package biblioteca.salas.duoc.biblioteca.salas.duoc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_sala")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoSala {
    @Id
    private Integer idTipo;
    private String nombre;

    // Getters and Setters
}