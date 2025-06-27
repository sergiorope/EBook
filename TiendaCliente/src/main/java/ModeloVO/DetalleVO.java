package ModeloVO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVO implements Serializable {
	
	private int id;
	private int pedido_id;
	private int producto_id;
	private int unidades;
	private double preciounidad;
	private double impuesto;
	private double total;

}
