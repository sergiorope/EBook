package Control.controlProducto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.ProductoVO;
import Servicio.CategoriaServicio;
import Servicio.ProductoServicio;

/**
 * Servlet implementation class DarAltaProducto
 */

@WebServlet("/AltaProducto")
public class DarAltaProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DarAltaProductoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String categoriaId = request.getParameter("categoria");

		int categoriaIdInt = Integer.parseInt(categoriaId);
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String precio = request.getParameter("precio");
		String impuesto = request.getParameter("impuesto");
		String stock = request.getParameter("stock");
		String baja = request.getParameter("baja");
		String imagen = request.getParameter("imagen");

		Double precioDouble = Double.parseDouble(precio);

		Double impuestoDouble = Double.parseDouble(impuesto);
		int stockInt = Integer.parseInt(stock);
		int bajaInt = Integer.parseInt(stock);

		ProductoVO producto = new ProductoVO();

		producto.setCategoria_id(categoriaIdInt);
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precioDouble);
		producto.setImpuesto(impuestoDouble);
		producto.setStock(stockInt);
		producto.setBaja(bajaInt);
		producto.setImagen(imagen);

		ProductoServicio.darAltaProducto(producto);

		request.getRequestDispatcher("AltaProducto.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
