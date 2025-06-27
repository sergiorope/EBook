package Control.controlValoracion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.ProductoVO;
import Servicio.ProductoServicio;

/**
 * Servlet implementation class VistaPublicarValoracionServlet
 */

@WebServlet("/RutaPublicacionValoracion")
public class VistaPublicarValoracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VistaPublicarValoracionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String idProducto= request.getParameter("idProducto");
		
		request.setAttribute("Productoid", idProducto);
		
		ProductoVO producto=ProductoServicio.mostrarProductoPorId(Integer.parseInt(idProducto));
		
		System.out.println(producto.getNombre());
		
		request.setAttribute("producto", producto);
		
		 request.getRequestDispatcher("PublicacionValoracion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
