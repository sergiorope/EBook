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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ModeloVO.ProductoVO;
import Servicio.ProductoServicio;

/**
 * Servlet Filter implementation class StockFiltro
 */

@WebFilter("/Pago")
public class StockFiltro extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public StockFiltro() {
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

		boolean stock = true;

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);
		
		int numcarrito =(int) session.getAttribute("numcarrito");

		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>) session.getAttribute("carrito");

		for (Entry<ProductoVO, Integer> entry : carrito.entrySet()) {
			ProductoVO producto = entry.getKey();

			int cantidad = entry.getValue();

			if (ProductoServicio.validarStock(producto) < cantidad) {

				stock = false;

			}

		}

		if (!stock) {
		
			request.setAttribute("errorStock", "No hay suficientes unidades en Stock");
			request.getRequestDispatcher("").forward(request, response);
		
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
