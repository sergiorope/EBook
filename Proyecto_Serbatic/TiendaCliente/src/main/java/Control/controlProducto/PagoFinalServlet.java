package Control.controlProducto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.ProductoVO;
import Servicio.ProductoServicio;

/**
 * Servlet implementation class PagoFinalServlet
 */

@WebServlet("/Pago")
public class PagoFinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PagoFinalServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	   
	    HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>) request.getSession()
	            .getAttribute("carrito");

	  
	    if (carrito != null && !carrito.isEmpty()) {
	        for (Entry<ProductoVO, Integer> entry : carrito.entrySet()) {
	            ProductoVO producto = entry.getKey();
	            int cantidad = entry.getValue();


	            int resultado = ProductoServicio.validarStock(producto) - cantidad;
	            producto.setStock(resultado);
	            ProductoServicio.actualizarStock(producto);
	        }
	    }

	  
	    request.getRequestDispatcher("/Maestro").forward(request, response);
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
