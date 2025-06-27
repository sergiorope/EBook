package Filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;

import Servicio.UsuarioServicio;

/**
 * Servlet Filter implementation class RegistroFiltro
 */

@WebFilter("/urlRegistro")
public class RegistroFiltro extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public RegistroFiltro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String nombre = request.getParameter("nombreRegister");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");
		String repiteclave = request.getParameter("repiteclave");
		
		boolean emailrepetido = UsuarioServicio.comprobarEmail(email);
		
		System.out.println("ES"+emailrepetido);
		
		

		if (nombre.equals("") || apellidos.equals("") || email.equals("") || clave.equals("") || repiteclave.equals("")) {
			request.setAttribute("errorCampos", "Error: revise los campos, no pueden estar vacíos");
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			return; 
		} else if (!clave.equals(repiteclave)) {
			request.setAttribute("errorClave", "Error: las contraseñas no coinciden");
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			return; 
		} else if(emailrepetido) {
			
			request.setAttribute("errorEmail", "Error: El ese email ya está en uso");
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			return;
			
		}else {
		}
			chain.doFilter(request, response);
		}



	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
