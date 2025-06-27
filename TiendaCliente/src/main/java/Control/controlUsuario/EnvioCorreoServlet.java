package Control.controlUsuario;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnvioCorreoServlet
 */

@WebServlet("/Correo")
public class EnvioCorreoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnvioCorreoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String destinatario = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");
		

		try {
			Properties prop = new Properties();
			// Configuración del servidor SMTP
			prop.setProperty("mail.smtp.host", "smtp-mail.outlook.com");
			prop.setProperty("mail.smtp.starttls.enable", "true");
			prop.setProperty("mail.smtp.port", "587");
			prop.setProperty("mail.smtp.user", "ebook@gmail.com");
		
	
			//prop.setProperty("mail.smtp.auth", "true");
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
			String emailBody = "Nombre completo: " + fullname + "\n";
			emailBody += "Correo electrónico: " + email + "\n";
			emailBody += "Teléfono: " + phone + "\n";
			emailBody += "Mensaje: " + message + "\n";
			mimeMessage.setText(emailBody);

			// Envío del mensaje
			Transport transport = session.getTransport("smtp");
			transport.connect("tienda-online-curso@outlook.com", "CursoJava2024");
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();

		} catch (AddressException ex) {
			Logger.getLogger(EnvioCorreoServlet.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(EnvioCorreoServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	

}
