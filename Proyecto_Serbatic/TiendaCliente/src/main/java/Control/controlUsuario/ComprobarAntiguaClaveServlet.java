package Control.controlUsuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComprobarAntiguaClaveServlet
 */

@WebServlet("/ComproAntig")
public class ComprobarAntiguaClaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprobarAntiguaClaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clave = request.getParameter("claveAntigua");
        String claveAntigua = (String) request.getSession().getAttribute("contrasenaSesion");
        
        if (clave != null && clave.equals(claveAntigua)) {
            request.setAttribute("CoConAnti", "Es Correcta");
            request.getRequestDispatcher("CambiarContrasena.jsp").forward(request, response);
        } else {
            request.setAttribute("ErrorConAnti", "Error la contraseña no es la correcta");
            request.getRequestDispatcher("CambiarContrasena.jsp").forward(request, response);
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
