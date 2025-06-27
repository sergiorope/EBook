package ModeloVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionVO {
	
	private int id;
	private int producto_id;
	private int usuario_id;
	private int valoracion;
	private String comentario;
}
