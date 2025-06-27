package Control.controlUsuario;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ModeloVO.UsuarioVO;
import Servicio.UsuarioServicio;

@WebServlet("/inicio")
public class IniciarSesion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(IniciarSesion.class);

    public IniciarSesion() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String log4jConfigFile = "F:/TiendaCliente/src/main/resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String clave = request.getParameter("clave");

        request.setAttribute("email", email);
        request.setAttribute("clave", clave);

        HttpSession sesion = request.getSession();
        sesion.setAttribute("contrasenaSesion", clave);

   
        String claveEncriptada = Base64.getEncoder().encodeToString(clave.getBytes());

 
        if (UsuarioServicio.validarUsuario(email, claveEncriptada)) {
            logger.info("Inicio de sesión exitoso para el usuario: " + email);
            request.getSession().setAttribute("nombre", UsuarioServicio.obtenerNombre(email, claveEncriptada));
            request.getSession().setAttribute("id_usuario", UsuarioServicio.obtenerId(email, claveEncriptada));
            request.getSession().setAttribute("id_rol", UsuarioServicio.obtenerRol_id(email, claveEncriptada));
            
           
            request.getRequestDispatcher("").forward(request, response);
        } else {
            logger.error("Inicio de sesión fallido para el usuario: " + email);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
