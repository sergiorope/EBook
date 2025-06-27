package Control.controlUsuario;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Servicio.UsuarioServicio;

/**
 * Servlet implementation class CambioContrasenaServlet
 */

@WebServlet("/CambioPass")
public class CambioClaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambioClaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUsuario = (int) request.getSession().getAttribute("id_usuario");
		
		String clave = request.getParameter("claveNueva");
		String clave2 = request.getParameter("claveNuevaRep");
		
		System.out.println(clave + clave2);
		
		
		
		if(clave.equals(clave2)) {
			
			request.getSession().setAttribute("contrasenaSesion",clave);
			
			String claveEncriptada = Base64.getEncoder().encodeToString(clave.getBytes());
			
			UsuarioServicio.actualizarUsuarioContrasena(claveEncriptada, idUsuario);
			
			request.getRequestDispatcher("").forward(request, response);
	
		}else {
			
			request.setAttribute("ErrorCoinci", "Error la contraseña nueva no coincide");	
			
			request.getRequestDispatcher("CambiarContrasena.jsp").forward(request, response);

		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
