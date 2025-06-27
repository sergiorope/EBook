package com.example.tienda.servicio;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.example.tienda.ModeloDAO.PedidosRepositorio;
import com.example.tienda.ModeloDAO.ProductoRepositorio;
import com.example.tienda.ModeloVO.DetalleVO;
import com.example.tienda.ModeloVO.PedidoVO;
import com.example.tienda.ModeloVO.ProductoVO;
import com.example.tienda.ModeloVO.UsuarioVO;

@Service
public class PedidoServicio {

	private volatile boolean isRunning = true;

	@Autowired
	private PedidosRepositorio pedidosRepositorio;

	@Autowired
	private ConfiguracionServicio configuracionservicio;

	@Autowired
	private ProductoServicio productoservicio;

	@Autowired
	private UsuarioServicio usuarioservicio;

	@Autowired
	private DetalleServicio detalleservicio;

	public List<PedidoVO> obtenerPedidos() {
		return pedidosRepositorio.findAll();
	}

	public void ConfirmarPedido(PedidoVO pedido) {
		pedidosRepositorio.save(pedido);
	}

	public PedidoVO obtenerPedidoporId(int id) {

		return pedidosRepositorio.findById(id).get();
	}

	public void EliminarPedidoporId(int id) {

		pedidosRepositorio.deleteById(id);
	}

	public StringBuilder ConstruirMsg(PedidoVO pedidoEncontrado, List<DetalleVO> listadetalles, PedidoVO pedidoObject) {

	    StringBuilder mensaje = new StringBuilder();
	    mensaje.append("MUCHAS GRACIAS POR SU COMPRA EN E-BOOK\n");
	    mensaje.append("Resumen:\n");
	    mensaje.append("-----------------------------------\n");
	    mensaje.append("Número de factura: ").append(pedidoObject.getNumfactura()).append("\n");

	    for (DetalleVO detalle : listadetalles) {
	        ProductoVO producto = productoservicio.selectProductoPorId(detalle.getProducto_id());
	        double subtotal = detalle.getPreciounidad() * detalle.getUnidades();

	        mensaje.append(producto.getNombre()).append(": €").append(detalle.getPreciounidad()).append(" x ")
	                .append(detalle.getUnidades()).append(" = €").append(subtotal).append("\n");
	    }
	    mensaje.append("TOTAL: €").append(pedidoObject.getTotal()).append("\n");

	    return mensaje;
	}

	public void envioCorreoConfirmacionPedido(UsuarioVO usuario, StringBuilder mensaje) {

		String destinatario = usuario.getEmail();
		String fullname = usuario.getNombre();
		StringBuilder message = mensaje;

		try {
			Properties prop = new Properties();

			prop.setProperty("mail.smtp.host", "smtp-mail.outlook.com");
			prop.setProperty("mail.smtp.starttls.enable", "true");
			prop.setProperty("mail.smtp.port", "587");
			prop.setProperty("mail.smtp.user", "ebook@gmail.com");

			// prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		

			// Creación de la sesión
			Session session = Session.getDefaultInstance(prop);

			session.setDebug(true);

			// Creación del mensaje
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress("tienda-online-curso@outlook.com"));
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			mimeMessage.setSubject("Mensaje de contacto desde el formulario");

			// Construcción del cuerpo del mensaje con los datos del formulario
			String emailBody = "Nombre: " + usuario.getNombre() + "\n";
			emailBody += "Correo electrónico: " + usuario.getEmail() + "\n";
			emailBody += "Mensaje: " + message + "\n";
			mimeMessage.setText(emailBody);

			// Envío del mensaje
			Transport transport = session.getTransport("smtp");
			transport.connect("tienda-online-curso@outlook.com", "CursoJava2024");
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();

		} catch (AddressException ex) {
			Logger.getLogger(PedidoServicio.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(PedidoServicio.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Async
	public void procesaPedidosAuto() throws InterruptedException {
		
		

		isRunning = true;

		while (isRunning) {

			List<PedidoVO> listaPedidos = obtenerPedidos();
			for (PedidoVO pedido : listaPedidos) {

				UsuarioVO usuario = usuarioservicio.selectUsuarioPorId(pedido.getUsuario_id());
				List<DetalleVO> listadetalles = detalleservicio.obtenerTodasLineasPedido(pedido.getId());

				
				if (pedido.getEstado().equals("PE")) {

					String valor = configuracionservicio.obtenerValor("factura");
					int valorInt = Integer.parseInt(valor);
					pedido.setNumfactura("FAC-000" + valorInt);
					StringBuilder mensaje = ConstruirMsg(pedido, listadetalles, pedido);
					pedido.setEstado("E");
					ConfirmarPedido(pedido);
					
					valorInt++;
					configuracionservicio.actualizarValor(Integer.toString(valorInt), "factura");

					envioCorreoConfirmacionPedido(usuario, mensaje);

				}
			}

			System.out.println("Se procesaron los pedidos");

			Thread.sleep(600000);
		}
	}

	public void detenerProcesamientoPedidos() {
		isRunning = false;

		System.out.println("Se detuvo el procesamiento");
	}

}
