package Control.controlPedido;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.ProductoDAO;
import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;
import ModeloVO.UsuarioVO;
import Servicio.CategoriaServicio;
import Servicio.PedidoServicio;
import Servicio.UsuarioServicio;

/**
 * Servlet implementation class VistaPedidosClientes
 */

@WebServlet("/RutaPedidosClientes")
public class VistaPedidosClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VistaPedidosClientes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		Map<PedidoVO, String> nombreporPedido = new HashMap<>();

		List<PedidoVO> pedidos = PedidoServicio.selectTodosPedidos();

		for (PedidoVO pedido : pedidos) {

			int idusuario = pedido.getUsuario_id();

			UsuarioVO usuario = UsuarioServicio.obtenerUsuarioEntero(idusuario);

			nombreporPedido.put(pedido, usuario.getNombre());
		}
		
		request.setAttribute("listaPedidos", PedidoServicio.selectTodosPedidos());
		request.setAttribute("nombreporpedido", nombreporPedido);
		
		request.getRequestDispatcher("ListaPedidos.jsp").forward(request, response);
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
