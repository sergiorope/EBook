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
 * Servlet implementation class VistaActualizacionCategoriaServlet
 */

@WebServlet("/RutaActualizacionCategoria")
public class VistaActualizacionCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VistaActualizacionCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idCategoria = request.getParameter("idCategoria");
		
		CategoriaVO categoria=CategoriaServicio.ObtenerCateg(Integer.parseInt(idCategoria));
		
		request.setAttribute("categoriaActualizar", categoria);

		request.getRequestDispatcher("ActualizacionCategoria.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
