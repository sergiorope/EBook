package Control.controlUsuario;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.UsuarioVO;
import Servicio.UsuarioServicio;

/**
 * Servlet implementation class VistaInfoServlet
 */

@WebServlet("/urlInfo")
public class VistaInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VistaInfoServlet() {
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

		int idUsuario = (int) request.getSession().getAttribute("id_usuario");

		UsuarioVO UsuarioForm = UsuarioServicio.obtenerUsuarioEntero(idUsuario);

		byte[] claveBytes = Base64.getDecoder().decode(UsuarioForm.getClave());
		String claveOriginal = new String(claveBytes);

		UsuarioForm.setClave(claveOriginal);

		request.setAttribute("datosUsuario", UsuarioForm);

		request.getRequestDispatcher("InfoCuenta.jsp").forward(request, response);
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
