package Control.controlUsuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.ProductoVO;
import ModeloVO.UsuarioVO;
import Servicio.ProductoServicio;
import Servicio.UsuarioServicio;

/**
 * Servlet implementation class DarAltaUsuarioServlet
 */

@WebServlet("/AltaUsuario")
public class DarAltaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DarAltaUsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String rolid = request.getParameter("rol_id");

		int rolidInt = Integer.parseInt(rolid);

		String email = request.getParameter("email");
		String clave = request.getParameter("contrasena");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String baja = request.getParameter("baja");

		int bajaInt = Integer.parseInt(baja);

		UsuarioVO usuario = new UsuarioVO();

		usuario.setRol_id(rolidInt);
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setClave(clave);
		usuario.setApellidos(apellidos);
		usuario.setBaja(bajaInt);
	

		UsuarioServicio.registrarUsuario(usuario);

		request.getRequestDispatcher("AltaUsuario.jsp").forward(request, response);
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
