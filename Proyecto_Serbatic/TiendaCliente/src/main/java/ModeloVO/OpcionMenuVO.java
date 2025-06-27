package ModeloVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class OpcionMenuVO {
	
	private int id;
	private int rol_id;
	private String nombre;
	private String url;

}
