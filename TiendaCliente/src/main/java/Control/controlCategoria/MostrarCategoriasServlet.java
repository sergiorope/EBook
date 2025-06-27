package Control.controlCategoria;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;
import Servicio.ProductoServicio;

/**
 * Servlet implementation class MostrarCategoriasServlet
 */

@WebServlet("/CategoriasFiltrar")
public class MostrarCategoriasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarCategoriasServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String categoriaId = request.getParameter("categoria");
		
		if(categoriaId.equals("Todo")) {
			
			request.getRequestDispatcher("").forward(request, response);
			return;
		}

		int id = Integer.parseInt(categoriaId);
		
	List<ProductoVO> lista=ProductoServicio.ObtenerPorductosCateg(id);
	

	
	request.setAttribute("listaProdu", lista);
	request.setAttribute("seleccionada", id);
	request.getRequestDispatcher("").forward(request, response);
		
		
		
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
