package ModeloVO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class ProductoVO implements Serializable {

private int id;	
private int categoria_id;
private int proveedor_id;
private String nombre;
private String descripcion;
private double precio;
private double impuesto;
private int stock;
private int baja;
private String imagen;

public ProductoVO(String nombre) {
	super();
	this.nombre = nombre;
}




}
