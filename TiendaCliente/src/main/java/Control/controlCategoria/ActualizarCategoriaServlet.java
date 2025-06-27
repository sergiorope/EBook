package Control.controlCategoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.CategoriaVO;
import ModeloVO.UsuarioVO;
import Servicio.CategoriaServicio;
import Servicio.UsuarioServicio;

/**
 * Servlet implementation class ActualizarCategoriaServlet
 */

@WebServlet("/ActualizarCategoria")
public class ActualizarCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActualizarCategoriaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String categoriaID = request.getParameter("idCategoria");

		String nombre = request.getParameter("nombre");

		System.out.println(nombre);

		CategoriaVO categoria = new CategoriaVO();

		categoria.setId(Integer.parseInt(categoriaID));
		categoria.setNombre(nombre);

		CategoriaServicio.UpdateCategoriaActualizar(categoria);

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
