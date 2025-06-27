package Filtros;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;

import ModeloVO.UsuarioVO;
import Servicio.UsuarioServicio;

/**
 * Servlet Filter implementation class InicioSesionFiltro
 */

@WebFilter("/inicio")
public class InicioSesionFiltro extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public InicioSesionFiltro() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    String email = request.getParameter("email");
	    String clave = request.getParameter("clave");

	    UsuarioVO u = new UsuarioVO();
	    
	    String claveEncriptada = Base64.getEncoder().encodeToString(clave.getBytes());
	    
	    u.setEmail(email);
	    u.setClave(claveEncriptada);
	    
	    
	    UsuarioVO usnuevo = UsuarioServicio.obtenerUsuario(email, claveEncriptada);
	    
	    if (usnuevo == null || usnuevo.getRol_id() != 1) {
	        request.setAttribute("ErrorInicio", "Los datos introducidos son incorrectos");
	        request.getRequestDispatcher("login.jsp").forward(request, response);
	    } else {
	        chain.doFilter(request, response);
	    }
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
