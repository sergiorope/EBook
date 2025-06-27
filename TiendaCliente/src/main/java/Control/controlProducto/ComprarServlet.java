package Control.controlProducto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.ProductoDAO;
import ModeloVO.ProductoVO;

/**
 * Servlet implementation class ComprarServlet
 */

@WebServlet("/Añadir")
public class ComprarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComprarServlet() {
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

		String impuesto = request.getParameter("impuesto");

		double impuestoDouble = Double.parseDouble(impuesto);

		impuestoDouble = impuestoDouble / 100;

		if (request.getParameter("precio") != null) {

			String dinero1 = request.getParameter("precio");

			double dinero2 = Double.valueOf(dinero1);

			dinero2 += dinero2 * impuestoDouble;

			CarritoAñadirServlet.dinero += dinero2;
			
			double dineroFormateado = Math.round(CarritoAñadirServlet.dinero * 100.0) / 100.0;

			request.getSession().setAttribute("dinerofin", dineroFormateado);

		}

		if (request.getParameter("id") != null) {

			String id = request.getParameter("id");

			int idInt = Integer.parseInt(id);

			System.out.println(idInt);

			ProductoVO p = ProductoDAO.MostrarProducto(idInt);

			request.getSession().setAttribute("producto", p);

			HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>) request.getSession()
					.getAttribute("carrito");

			if (carrito != null) {
				Integer contador = carrito.getOrDefault(p, 0);

				String param = request.getParameter("action");

				if (param.equals("anadir")) {

					carrito.put(p, contador + 1);

				}

				if (param.equals("eliminar")) {

					if (contador <= 1) {

						carrito.remove(p);

					} else {

						carrito.put(p, contador - 1);
					}

				}

				int tamaño = carrito.size();

				request.getSession().setAttribute("numcarrito", tamaño);

			}

		}

//		String categoriaId = request.getParameter("categoria");
//		int id = Integer.parseInt(categoriaId);
//		request.setAttribute("seleccionada", id);

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
