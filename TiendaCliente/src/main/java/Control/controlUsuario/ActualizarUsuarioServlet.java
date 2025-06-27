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
 * Servlet implementation class ActualizarUsuarioServlet
 */

@WebServlet("/ActualizarUsuario")
public class ActualizarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String usuarioID = request.getParameter("idUsuario");
		
		System.out.println(usuarioID);
		

		int usuarioIDInt = Integer.parseInt(usuarioID);
	
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");
		String baja = request.getParameter("baja");
		
		System.out.println(nombre);
		System.out.println(apellidos);
		System.out.println(email);
		System.out.println(clave);
		System.out.println(baja);

		int bajaInt = Integer.parseInt(baja);

		UsuarioVO usuario = new UsuarioVO();
		
		
		usuario.setId(usuarioIDInt);
		usuario.setRol_id(1);
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setEmail(email);
		usuario.setClave(clave);
		usuario.setBaja(bajaInt);

		
		
		UsuarioServicio.actualizarUsuario(usuario);

		
		request.getRequestDispatcher("").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
