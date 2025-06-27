package Control.controlProducto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.ProductoDAO;
import ModeloVO.ProductoVO;

import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;
import ModeloVO.ValoracionVO;
import Servicio.CategoriaServicio;
import Servicio.OpcionmenuServicio;
import Servicio.ProductoServicio;
import Servicio.UsuarioServicio;
import Servicio.ValoracionServicio;

/**
 * Servlet implementation class EntradaServlet
 */
@WebServlet("")
public class EntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EntradaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pagina = "index.jsp";

		String language = request.getParameter("lang");
		Locale locale;
		if (language != null && language.equals("en")) {
			locale = new Locale("en", "US");
		} else {
			locale = new Locale("es", "ES");
		}
		request.getSession().setAttribute("locale", locale);

		if (request.getSession().getAttribute("carrito") == null) {

			request.getSession().setAttribute("carrito", new HashMap<ProductoVO, Integer>());

		}

		Integer idrol = (Integer) request.getSession().getAttribute("id_rol");

		System.out.println("el id:" + idrol);

		if (idrol != null) {

			request.getSession().setAttribute("opcionesMenu", OpcionmenuServicio.ObtenerOpciones(idrol));

		} else {

			request.getSession().setAttribute("opcionesMenu", OpcionmenuServicio.ObtenerOpciones(0));

		}

		if (request.getSession().getAttribute("MisPedidosLista") == null) {

			request.getSession().setAttribute("MisPedidosLista", new ArrayList<PedidoVO>());

		}

		if (request.getSession().getAttribute("MisDetallesLista") == null) {

			request.getSession().setAttribute("MisDetallesLista", new ArrayList<DetalleVO>());

		}

		if (request.getSession().getAttribute("id_usuario") != null) {

			ProductoVO ProductoRecomendado = UsuarioServicio.Recomendacion(request);

			request.setAttribute("ProductoRecomendado", ProductoRecomendado);

		}

		List<ProductoVO> productos = ProductoServicio.obtenerCatalogo();
		Map<ProductoVO, Double> valoracionporProducto = new HashMap<>();

		for (ProductoVO producto : productos) {

			double valoracion = ValoracionServicio.ObtenerValoracionMedia(producto.getId());

			valoracionporProducto.put(producto, valoracion);

		}

		request.setAttribute("valoraciones", valoracionporProducto);

		Integer seleccionada = (Integer) request.getAttribute("seleccionada");
		request.setAttribute("seleccionada", seleccionada);

		request.getSession().setAttribute("categorias", CategoriaServicio.ObtenerTodasCategorias());
		request.setAttribute("ListaCategorias", request.getAttribute("listaProdu"));
		request.setAttribute("catalogo", ProductoDAO.findAll());
		request.getRequestDispatcher(pagina).forward(request, response);
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
