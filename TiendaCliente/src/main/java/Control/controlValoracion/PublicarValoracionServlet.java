package Control.controlValoracion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloVO.ValoracionVO;
import Servicio.ValoracionServicio;

/**
 * Servlet implementation class PublicarValoracionServlet
 */

@WebServlet("/ValoracionResena")
public class PublicarValoracionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicarValoracionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String idProducto=request.getParameter("idproducto");
		 
		 System.out.println(idProducto);
	        String valoracion=request.getParameter("rating");
	        String comentario=request.getParameter("comentario");
	        int idusuario = (int) request.getSession().getAttribute("id_usuario");
	        
	        ValoracionVO valoracionObjeto = new ValoracionVO();
	        
	        valoracionObjeto.setProducto_id(Integer.parseInt(idProducto));
	        valoracionObjeto.setUsuario_id(idusuario);
	        valoracionObjeto.setValoracion(Integer.parseInt(valoracion));
	        valoracionObjeto.setComentario(comentario);

	       ValoracionServicio.InsertarValoracion(valoracionObjeto);
	       
	       request.getRequestDispatcher("MisPedidos.jsp").forward(request, response);
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
