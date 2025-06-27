package Control.controlPedido;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ModeloVO.PedidoVO;

@WebServlet("/FiltrarFecha")
public class FiltrarFechaPedidoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public FiltrarFechaPedidoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
        String fechaSeleccionadaStr = request.getParameter("fecha");

       
        try {
            Date fechaSeleccionada = formato.parse(fechaSeleccionadaStr);

            // Obtener la lista de pedidos de la sesión
            List<PedidoVO> pedidos = (List<PedidoVO>) request.getSession().getAttribute("pedidos");

        
            List<PedidoVO> pedidosFiltrados = new ArrayList<>();

          
            if (pedidos != null && fechaSeleccionada != null) {
                for (PedidoVO pedido : pedidos) {
                   
                    Date fechaPedido = formato.parse(pedido.getFecha());

               
                    if (fechaPedido.equals(fechaSeleccionada)) {
                        pedidosFiltrados.add(pedido);
                    }
                }
            }

           
            request.getSession().setAttribute("pedidosFiltrados", pedidosFiltrados);

            
            request.getRequestDispatcher("MisPedidos.jsp").forward(request, response);
        } catch (ParseException e) {
        
            e.printStackTrace();
           
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
