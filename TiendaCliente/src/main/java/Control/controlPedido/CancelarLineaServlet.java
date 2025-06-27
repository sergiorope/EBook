package Control.controlPedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;
import Servicio.DetalleServicio;
import Servicio.PedidoServicio;
import Servicio.ProductoServicio;

/**
 * Servlet implementation class CancelarLineaServlet
 */

@WebServlet("/CancelarLinea")
public class CancelarLineaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancelarLineaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idDetalle = request.getParameter("idDetalle");

		DetalleVO detalle = DetalleServicio.ObtenerDetallePorId(Integer.parseInt(idDetalle));

		PedidoVO pedido = PedidoServicio.ObtenerPedidosId(detalle.getPedido_id());

		Double total = PedidoServicio.CalcularCancelacionLinea(pedido, detalle);

		pedido.setTotal(total);

		ProductoVO producto = ProductoServicio.mostrarProductoPorId(detalle.getProducto_id());

		producto.setStock(producto.getStock() + detalle.getUnidades());

		ProductoServicio.actualizarProducto(producto);

		DetalleServicio.BorrarLinea(detalle.getId());
		PedidoServicio.Actualizar(pedido);

		request.getRequestDispatcher("/RutaMisPedidos").forward(request, response);

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
