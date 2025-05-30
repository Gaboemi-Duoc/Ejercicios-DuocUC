package biblioteca.salas.duoc.biblioteca.salas.duoc.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Sala {
    @Id
    private Integer codigo;
    @Column(length = 50)
    private String nombre;
    private Integer capacidad;
    private Integer idInstituto;
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false) // Clave foránea
    private TipoSala tipoSala;
}
