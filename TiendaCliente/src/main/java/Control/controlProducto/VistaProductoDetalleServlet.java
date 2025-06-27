package Control.controlProducto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.List;

import ModeloVO.ProductoVO;
import ModeloVO.ValoracionVO;
import Servicio.ProductoServicio;
import Servicio.ValoracionServicio;

/**
 * Servlet implementation class VistaProductoDetalleServlet
 */

@WebServlet("/RutaDetalle")
public class VistaProductoDetalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VistaProductoDetalleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String idProducto = request.getParameter("id");

		int idProductoInt = Integer.parseInt(idProducto);

		ProductoVO producto = ProductoServicio.mostrarProductoPorId(idProductoInt);
		
		java.util.List<ValoracionVO> valoraciones=ValoracionServicio.ObtenerResenasProducto(idProductoInt);
		Map<ValoracionVO, String> nombreporValoracion=ValoracionServicio.ObtenerResenasNombreProducto(idProductoInt);
		
		request.setAttribute("listavaloraciones", valoraciones);
		request.setAttribute("nombreporvaloracion", nombreporValoracion);

		request.setAttribute("productoDetalle", producto);

		request.getRequestDispatcher("ProductoDetalle.jsp").forward(request, response);
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
