package ModeloVO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "usuario")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rol_id;
    private String email;
    private String nombre;
    private String apellidos;
    private String clave;
    private int baja;

    public UsuarioVO(String email, String clave) {
        super();
        this.email = email;
        this.clave = clave;
    }
}
