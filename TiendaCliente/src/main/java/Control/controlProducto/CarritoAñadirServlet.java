package Control.controlProducto;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.ProductoDAO;
import ModeloVO.ProductoVO;
import Servicio.ProductoServicio;

/**
 * Servlet implementation class CarritoServlet
 */
@WebServlet("/rutaCarrito")
public class CarritoAñadirServlet extends HttpServlet {

	public static double dinero;
	private static final long serialVersionUID = 1L;
	  private ProductoServicio ProductoServicio;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	 public CarritoAñadirServlet() {
	        super();
	        ProductoServicio = new ProductoServicio();
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String impuesto = request.getParameter("impuesto");
        double impuestoDouble = Double.parseDouble(impuesto) / 100;

        String param = request.getParameter("action");

        if (request.getParameter("precio") != null && param.equals("anadir")) {
            ProductoServicio.agregarProducto(request, impuestoDouble);
        }

        if (request.getParameter("precio") != null && param.equals("eliminar")) {
        	ProductoServicio.eliminarProducto(request, impuestoDouble);
        }

        if (request.getParameter("id") != null) {
        	ProductoServicio.mostrarProducto(request, param);
        }

        request.getRequestDispatcher("Carrito.jsp").forward(request, response);
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
