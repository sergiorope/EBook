package Control.controlPedido;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import Control.controlProducto.CarritoAñadirServlet;
import ModeloVO.ConfiguracionVO;
import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;
import ModeloVO.UsuarioVO;
import Servicio.ConfiguracionServicio;
import Servicio.PedidoServicio;
import Servicio.UsuarioServicio;

@WebServlet("/FacturaPedidos")
public class GenerarFacturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GenerarFacturaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<Integer, List<DetalleVO>> detallesPorPedido = (Map<Integer, List<DetalleVO>>) request.getSession()
				.getAttribute("detallesPorPedido");
		Map<DetalleVO, ProductoVO> nombrePorDetalle = (Map<DetalleVO, ProductoVO>) request.getSession()
				.getAttribute("listaProducto");

		String cif = ConfiguracionServicio.ObtenerValor("cif");
		String direccion = ConfiguracionServicio.ObtenerValor("direccion");
		String provincia = ConfiguracionServicio.ObtenerValor("provincia");
		String telefono = ConfiguracionServicio.ObtenerValor("telefono");
		int idUsuario = (int) request.getSession().getAttribute("id_usuario");

		String idpedidoString = request.getParameter("idPedidoFactura");

		PedidoVO pedido = PedidoServicio.ObtenerPedidosId(Integer.parseInt(idpedidoString));
		
		double dineroFormateado = Math.round(pedido.getTotal() * 100.0) / 100.0;
		
		

		UsuarioVO usuario = UsuarioServicio.obtenerUsuarioEntero(idUsuario);

		String nombreArchivo = pedido.getNumfactura() + ".pdf";

		try (PDDocument documento = new PDDocument()) {
			PDPage pagina = new PDPage();
			documento.addPage(pagina);

			PDPageContentStream contenido = new PDPageContentStream(documento, pagina);
			contenido.setFont(PDType1Font.HELVETICA, 12);

			 // Dibujar la imagen fuera del bloque de texto
            PDImageXObject logo = PDImageXObject.createFromFile("D:/TiendaCliente/src/main/resources/img/logo.png", documento);
            contenido.drawImage(logo, -50, 680, logo.getWidth() / 8, logo.getHeight() / 8);
			
			  contenido.setFont(PDType1Font.HELVETICA_BOLD, 24); 
	            contenido.beginText();
	            contenido.newLineAtOffset(480, 765); 
	            contenido.showText("FACTURA");
	            contenido.newLine();
	            contenido.endText();
	            
	    

			contenido.beginText();
			 contenido.setFont(PDType1Font.HELVETICA, 12);
			contenido.newLineAtOffset(100, 750);
			contenido.setLeading(14.5f);

			// Información de la empresa
			contenido.newLineAtOffset(30, -50);
			contenido.showText("E-BOOK S.A");
			contenido.newLine();
			contenido.showText("CIF: " + cif);
			contenido.newLine();
			contenido.showText("Dirección: " + direccion);
			contenido.newLineAtOffset(220, 50);
			contenido.newLine();
			contenido.showText("Provincia: " + provincia);
			contenido.newLine();
			contenido.showText("Teléfono: " + telefono);
			contenido.newLine();
			contenido.showText("Destinatario: " + usuario.getNombre() + " " + usuario.getApellidos());
			contenido.newLine();
			contenido.showText(pedido.getNumfactura());
			contenido.newLine();
			contenido.endText();
			
			contenido.beginText();
			contenido.newLineAtOffset(-120, -10);
            contenido.showText("Factura de compra:");
            contenido.newLine();
            contenido.endText();

			// Dibujar una línea sólida
			contenido.moveTo(100, 625);
			contenido.lineTo(600, 625);
			contenido.setLineWidth(1); // Grosor de la línea
			contenido.stroke();
			
			contenido.beginText();
            contenido.newLineAtOffset(120, 590);
            contenido.setLeading(14.5f);// Comenzar un nuevo bloque de texto

            contenido.showText("Servicio de compra");          
            contenido.endText();

			contenido.beginText();
			contenido.newLineAtOffset(370, 600);
			contenido.setLeading(14.5f);// Comenzar un nuevo bloque de texto

			// Detalles de la factura
			List<DetalleVO> detalles = detallesPorPedido.get(pedido.getId());
			if (detalles != null) {
				for (DetalleVO detalle : detalles) {
					ProductoVO producto = nombrePorDetalle.get(detalle);

					contenido.showText(producto.getNombre());
					contenido.newLine();
					contenido.showText("Unidades: " + detalle.getUnidades());
					contenido.showText(" Total: " + detalle.getPreciounidad()+"€");
					contenido.newLine();
					contenido.newLine();

				}

				contenido.showText("----------------------------------------");
			}

			
			contenido.newLine();
			contenido.showText("Total Pedido: " + pedido.getTotal()+"€ " + "(IVA icluido)");
			contenido.newLine();

			contenido.endText();

		
			contenido.close();

			documento.save(nombreArchivo);
			System.out.println("Factura generada correctamente como ejemplo.pdf");

			request.getRequestDispatcher("/RutaMisPedidos").forward(request, response);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
