package Control.controlUsuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.ProductoVO;
import ModeloVO.UsuarioVO;
import Servicio.ProductoServicio;
import Servicio.UsuarioServicio;

/**
 * Servlet implementation class VistaActualizacionUsuarioServlet
 */

@WebServlet("/RutaActualizacionUsuario")
public class VistaActualizacionUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VistaActualizacionUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String idUsuario = request.getParameter("idUsuario");
		
		System.out.println(idUsuario);

		UsuarioVO usuario=UsuarioServicio.obtenerUsuarioEntero(Integer.parseInt(idUsuario));
		
		request.setAttribute("usuarioActualizar", usuario);

		request.getRequestDispatcher("ActualizacionUsuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
