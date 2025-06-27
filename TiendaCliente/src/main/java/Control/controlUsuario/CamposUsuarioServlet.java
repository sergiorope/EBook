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
 * Servlet implementation class CamposUsuarioServlet
 */

@WebServlet("/CamposUsuario")
public class CamposUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CamposUsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idUsuario = (int) request.getSession().getAttribute("id_usuario");

		String nombre = request.getParameter("nombref");
		String apellidos = request.getParameter("apellidosf");
		String email = request.getParameter("emailf");
		String clave = request.getParameter("clavef");

		int idUsuarioInt = (int) request.getSession().getAttribute("id_usuario");

		UsuarioVO usuario = UsuarioServicio.obtenerUsuarioEntero(idUsuarioInt);

		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setEmail(email);

		UsuarioServicio.actualizarUsuario(usuario);

		request.getSession().setAttribute("nombre", nombre);

		request.getRequestDispatcher("").forward(request, response);

	}

}
