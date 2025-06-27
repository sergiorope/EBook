package Control.controlProducto;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.ProductoDAO;
import ModeloVO.DetalleVO;
import ModeloVO.ProductoVO;
import Servicio.CategoriaServicio;

/**
 * Servlet implementation class VistaListaProductosServlet
 */

@WebServlet("/RutaListaProductos")
public class VistaListaProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VistaListaProductosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("listaProductos", ProductoDAO.findAll());

		Map<ProductoVO, String> categoriaporProducto = new HashMap<>();

		List<ProductoVO> productos = ProductoDAO.findAll();

		for (ProductoVO producto : productos) {

			int idcateg = producto.getCategoria_id();

			String nombreCateg = CategoriaServicio.Obtenerid(idcateg);

			categoriaporProducto.put(producto, nombreCateg);
		}
		
		for (Map.Entry<ProductoVO, String> entry : categoriaporProducto.entrySet()) {
		    ProductoVO producto = entry.getKey();
		    String categoria = entry.getValue();
		    System.out.println("Producto: " + producto.getNombre() + ", Categoría: " + categoria);
		}

		
		request.setAttribute("listaProductos", ProductoDAO.findAll());
		request.setAttribute("categporproducto", categoriaporProducto);

	

		request.getRequestDispatcher("ListaProductos.jsp").forward(request, response);
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
