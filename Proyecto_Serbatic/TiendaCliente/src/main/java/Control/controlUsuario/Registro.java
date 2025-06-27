package Control.controlUsuario;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.UsuarioDAO;
import ModeloVO.UsuarioVO;
import Servicio.UsuarioServicio;

/**
 * Servlet implementation class Registro
 */

@WebServlet("/urlRegistro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombreRegister");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");

		String claveEncriptada = Base64.getEncoder().encodeToString(clave.getBytes());

		UsuarioVO u = new UsuarioVO(email, claveEncriptada);

		u.setNombre(nombre);
		u.setApellidos(apellidos);
		u.setRol_id(1);
		u.setBaja(1);

		UsuarioServicio.registrarUsuario(u);

		request.getRequestDispatcher("login.jsp").forward(request, response);
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
