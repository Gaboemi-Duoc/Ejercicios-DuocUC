
package biblioteca.salas.duoc.biblioteca.salas.duoc.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carrera")

public class Carrera {
    @Id
    @Column(length = 100)
    private String codigo;
    @Column(length = 100)
    private String nombre;
    @OneToMany(mappedBy = "id")
    private List<Estudiante> estudiantes;
}
