package Control.controlPedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.ProductoDAO;
import ModeloDAO.ValoracionDAO;
import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;
import ModeloVO.ValoracionVO;
import Servicio.DetalleServicio;
import Servicio.PedidoServicio;
import Servicio.ProductoServicio;
import Servicio.ValoracionServicio;

/**
 * Servlet implementation class MisPedidos
 */

@WebServlet("/RutaMisPedidos")
public class MisPedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MisPedidos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = (int) request.getSession().getAttribute("id_usuario");

		PedidoVO pedido = new PedidoVO();

		pedido.setUsuario_id(id);
		List<PedidoVO> listaPedidos = PedidoServicio.ObtenerPedidosUsuario(pedido);
		
		int idusuario=(int) request.getSession().getAttribute("id_usuario");

		Map<Integer, List<DetalleVO>> detallesPorPedido = new HashMap<>();
		Map<DetalleVO, ProductoVO> NombrePorDetalle = new HashMap<>();
		Map<Integer, Boolean> ValoracionPorProducto = new HashMap<>();

		for (PedidoVO pedidoLista : listaPedidos) {
			DetalleVO detalle = new DetalleVO();
			detalle.setPedido_id(pedidoLista.getId());
			List<DetalleVO> detallesPedido = DetalleServicio.ObtenerDetallesPedido(detalle);
			detallesPorPedido.put(pedidoLista.getId(), detallesPedido);

			for (DetalleVO lineas : detallesPedido) {
				ProductoVO ProductoPorLinea = ProductoDAO.getNombresProductos(lineas);
				NombrePorDetalle.put(lineas, ProductoPorLinea);

				List<ValoracionVO> valoraciones = ValoracionServicio.ObtenerValoracionesComprobar(ProductoPorLinea.getId(), idusuario);

				if (valoraciones.isEmpty()) {

					ValoracionPorProducto.put(ProductoPorLinea.getId(), true);
				} else {

					ValoracionPorProducto.put(ProductoPorLinea.getId(), false);
				}

			}

		}
		
		for (Map.Entry<Integer, Boolean> entry : ValoracionPorProducto.entrySet()) {
		    Integer producto = entry.getKey();
		    Boolean valoracion = entry.getValue();
		    
		    System.out.println("Producto: " + producto.toString() + ", Valoración: " + valoracion);
		}
		
		

		request.getSession().setAttribute("valoracionporproducto", ValoracionPorProducto);
		request.getSession().setAttribute("listaProducto", NombrePorDetalle);
		request.getSession().setAttribute("pedidos", listaPedidos);
		request.getSession().setAttribute("detallesPorPedido", detallesPorPedido);

		request.getRequestDispatcher("MisPedidos.jsp").forward(request, response);
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
