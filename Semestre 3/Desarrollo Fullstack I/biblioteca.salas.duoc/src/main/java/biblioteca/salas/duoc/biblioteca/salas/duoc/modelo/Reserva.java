package biblioteca.salas.duoc.biblioteca.salas.duoc.modelo;

import java.sql.Date;

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
public class Reserva {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "sala", nullable = false) // Clave foránea
    private Sala Sala;
    @ManyToOne
    @JoinColumn(name = "estudiante", nullable = false) // Clave foránea
    private Estudiante estudiante;
    private Date fechaSolicitada;
    private Date horaSolicitada;
    private Date horaCierre;
    private Integer estado;
}
