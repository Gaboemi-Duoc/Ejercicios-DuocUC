package biblioteca.salas.duoc.biblioteca.salas.duoc.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_sala")
public class TipoSala {
    @Id
    private Integer idTipo;
    @Column(length = 30)
    private String nombre;
    @OneToMany(mappedBy = "codigo")
    private List<Sala> salas;
    
}
