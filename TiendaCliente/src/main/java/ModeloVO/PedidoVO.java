package ModeloVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class PedidoVO {
	
	private int id;
	private int usuario_id;
	private String fecha;
	private String metodopago;
	private String numfactura;
	private String estado;
	private double total;
	
	

}
