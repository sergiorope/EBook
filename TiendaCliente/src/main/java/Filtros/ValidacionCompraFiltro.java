package Filtros;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ModeloVO.DetalleVO;
import ModeloVO.ProductoVO;
import Servicio.DetalleServicio;

/**
 * Servlet Filter implementation class ValidacionCompraFiltro
 */

@WebFilter("/Pago")
public class ValidacionCompraFiltro extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public ValidacionCompraFiltro() {
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
		

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);
		
		System.out.println("waswwwwwwa");

		
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>) session.getAttribute("carrito");
		
		
		if (carrito.isEmpty()) {
	
			request.getRequestDispatcher("").forward(request, response);
			System.out.println("Esta vacioooo");
			
		}else {
			
			System.out.println("wasa");
			
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
