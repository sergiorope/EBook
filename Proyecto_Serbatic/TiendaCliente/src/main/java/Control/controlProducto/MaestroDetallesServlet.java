package Control.controlProducto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.ConfiguracionVO;
import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;
import Servicio.ConfiguracionServicio;
import Servicio.DetalleServicio;
import Servicio.PedidoServicio;
import Servicio.ProductoServicio;

@WebServlet("/Maestro")
public class MaestroDetallesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaestroDetallesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int usuarioId = (int) request.getSession().getAttribute("id_usuario");

		Double dinerofin = (Double) request.getSession().getAttribute("dinerofin");

		
		System.out.println(dinerofin);
		

		String metodo = request.getParameter("tipoPago");

		PedidoVO pedido = new PedidoVO();

		String valor = ConfiguracionServicio.ObtenerValor("factura");

		int ValorFac = Integer.parseInt(valor);

		pedido.setMetodopago(metodo);
		pedido.setUsuario_id(usuarioId);
		pedido.setEstado("PE");
		pedido.setTotal(dinerofin);

		int idPedido = PedidoServicio.RegistrarPedidoLineas(pedido);

		ValorFac++;

		String valorUpdate = Integer.toString(ValorFac);

		ConfiguracionServicio.ActualizarValor(valorUpdate, "factura");

		request.setAttribute("idpedido", idPedido);

		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>) request.getSession()
				.getAttribute("carrito");

		if (carrito != null && !carrito.isEmpty()) {
			for (Entry<ProductoVO, Integer> entry : carrito.entrySet()) {
				ProductoVO producto = entry.getKey();
				int cantidad = entry.getValue();

				int idProducto = producto.getId();

				double precioProducto = producto.getPrecio();

				double cantidadParcial = cantidad * precioProducto;

				DetalleVO detalle = new DetalleVO();

				detalle.setPedido_id(idPedido);
				detalle.setProducto_id(idProducto);
				detalle.setUnidades(cantidad);
				detalle.setPreciounidad(precioProducto);
				detalle.setImpuesto(21);
				detalle.setTotal(cantidadParcial);

				DetalleServicio.registrarLineas(detalle);

			}
		}

		carrito.clear();

		request.getSession().setAttribute("carrito", carrito);
		request.getSession().removeAttribute("numcarrito");
		CarritoAñadirServlet.dinero = 0;
		request.getSession().removeAttribute("dinerofin");
		request.getRequestDispatcher("PedidoCompletado.jsp").forward(request, response);

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
