package Control.controlPedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.PedidoVO;
import Servicio.ConfiguracionServicio;
import Servicio.PedidoServicio;

/**
 * Servlet implementation class ConfirmarEnvioServlet
 */

@WebServlet("/ConfirmarEnvio")
public class ConfirmarEnvioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmarEnvioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idPedido = request.getParameter("idPedido");

		int idPedidoInt = Integer.parseInt(idPedido);

		String valor = ConfiguracionServicio.ObtenerValor("factura");

		int ValorFac = Integer.parseInt(valor);

		String valorUpdate = Integer.toString(ValorFac);

		ConfiguracionServicio.ActualizarValor(valorUpdate, "factura");

		PedidoServicio.ConfirmarEnvioPedido("FAC-0000" + ValorFac, "E", idPedidoInt);
		
		request.getRequestDispatcher("").forward(request, response);

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
