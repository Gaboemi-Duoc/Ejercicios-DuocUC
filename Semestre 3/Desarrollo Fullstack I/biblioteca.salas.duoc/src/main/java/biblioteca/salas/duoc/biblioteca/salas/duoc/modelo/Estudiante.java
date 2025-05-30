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
@Table(name = "estudiante")
public class Estudiante {
    @Id
    private Integer id;
    @Column(unique = true,length = 9)
    private String run;
    @Column(length = 100)
    private String nombre;
    @Column(length = 100)
    private String correo;
    private char jornada;
    private Integer telefono;
    
    @ManyToOne
    @JoinColumn(name = "carrera_id", nullable = false) // Clave foránea
    private Carrera carrera;

    
}
