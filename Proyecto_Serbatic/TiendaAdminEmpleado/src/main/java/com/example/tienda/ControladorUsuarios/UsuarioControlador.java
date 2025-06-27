package com.example.tienda.ControladorUsuarios;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tienda.ModeloVO.CategoriaVO;
import com.example.tienda.ModeloVO.ProductoVO;
import com.example.tienda.ModeloVO.ProveedorVO;
import com.example.tienda.ModeloVO.RolVO;
import com.example.tienda.ModeloVO.UsuarioVO;
import com.example.tienda.servicio.CategoriaServicio;
import com.example.tienda.servicio.PedidoServicio;
import com.example.tienda.servicio.ProductoServicio;
import com.example.tienda.servicio.ProveedorServicio;
import com.example.tienda.servicio.RolServicio;
import com.example.tienda.servicio.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("ControladorUsuario")
public class UsuarioControlador {

	private final ExecutorService executorService = Executors.newCachedThreadPool();


	@Autowired
	private UsuarioServicio usuarioServicio;


	@Autowired
	private PedidoServicio pedidoServicio;

	@Autowired
	private RolServicio rolServicio;

	@GetMapping("/RutaAltaUsuario")
	public String rutaAlta(Model model) {
		UsuarioVO usuario = new UsuarioVO();

		model.addAttribute("usuario", usuario);

		return "AltaUsuario";
	}

	@GetMapping("/RutaAltaEmpleado")
	public String rutaAltaEmpleado(Model model) {
		UsuarioVO usuario = new UsuarioVO();

		model.addAttribute("usuario", usuario);

		return "AltaEmpleado";
	}

	@GetMapping("/RutaAltaAdmin")
	public String rutaAltaAdmin(Model model) {
		UsuarioVO usuario = new UsuarioVO();

		model.addAttribute("usuario", usuario);

		return "AltaAdmin";
	}



	@GetMapping("/RutaListaUsuario")
	public String rutaLista(Model model) {

		Map<Integer, String> clienteporRol = usuarioServicio.mapeoUsuarioRol(1);
		List<UsuarioVO> listaclientes = usuarioServicio.obtenerUsuariosporRol(1);

		model.addAttribute("usuarioporrol", clienteporRol);

		model.addAttribute("listausuarios", listaclientes);
		return "ListaUsuarios";
	}

	@GetMapping("/RutaCambioClave")
	public String rutaCambioClave(Model model) {

		model.addAttribute("usuario", new UsuarioVO());

		return "CambioClave";
	}

	@GetMapping("/RutaListaEmpleado")
	public String rutaListaEmpleado(Model model) {

		Map<Integer, String> empleadoporRol = usuarioServicio.mapeoUsuarioRol(2);
		List<UsuarioVO> listaempleados = usuarioServicio.obtenerUsuariosporRol(2);

		model.addAttribute("usuarioporrol", empleadoporRol);

		model.addAttribute("listausuarios", listaempleados);

		return "ListaEmpleados";
	}

	@GetMapping("/RutaListaAdmin")
	public String rutaListaAdmin(Model model, HttpSession session) {

		UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuarioSesion");

		System.out.println(usuario.getId());

		List<UsuarioVO> listaadministradores = usuarioServicio.selectAdministrador(3, usuario.getId());
		Map<Integer, String> administradorporRol = usuarioServicio.mapeoUsuarioRol(3);

		model.addAttribute("usuarioporrol", administradorporRol);

		model.addAttribute("listausuarios", listaadministradores);

		return "ListaAdmin";
	}


	@GetMapping("/RutaActualizacionUsuario")
	public String rutaActualizacion(Model model, @RequestParam String idUsuario) {

		UsuarioVO usuario = usuarioServicio.selectUsuarioPorId(Integer.parseInt(idUsuario));

		List<RolVO> roles = rolServicio.obtenerRoles();
		model.addAttribute("listaroles", roles);

		model.addAttribute("Usuario", usuario);

		return "ActualizacionUsuario";
	}

	@GetMapping("/RutaActualizacionEmpleado")
	public String rutaActualizacionEmpleado(Model model, @RequestParam String idUsuario) {

		UsuarioVO usuario = usuarioServicio.selectUsuarioPorId(Integer.parseInt(idUsuario));

		List<RolVO> roles = rolServicio.obtenerRoles();
		model.addAttribute("listaroles", roles);

		model.addAttribute("Usuario", usuario);

		return "ActualizacionEmpleado";
	}

	@GetMapping("/RutaActualizacionAdmin")
	public String rutaActualizacionAdmin(Model model, @RequestParam String idUsuario) {

		UsuarioVO usuario = usuarioServicio.selectUsuarioPorId(Integer.parseInt(idUsuario));

		List<RolVO> roles = rolServicio.obtenerRoles();
		model.addAttribute("listaroles", roles);

		model.addAttribute("Usuario", usuario);

		return "ActualizacionAdmin";
	}

	@GetMapping("/RutaCambiarInformacion")
	public String rutaCambiarInformacion(Model model, HttpSession session) {

		UsuarioVO usuarioObjeto = (UsuarioVO) session.getAttribute("usuarioSesion");

		model.addAttribute("usuario", usuarioObjeto);

		return "DatosInfoEmpleado";
	}

	@GetMapping("/RutaIniciarSesion")
	public String rutaInicioSesion(Model model) {
		UsuarioVO usuario = new UsuarioVO();
		model.addAttribute("usuario", usuario);

		return "Login";
	}

	@GetMapping("/CerrarSesion")
	public String cerrarSesion(Model model, HttpSession session) throws InterruptedException {
		UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuarioSesion");

		if (usuario != null) {
			UsuarioVO usuarioObjeto = usuarioServicio.selectUsuarioPorId(usuario.getId());

			if (usuarioObjeto.getClave().equals("YWRtaW4=") && usuarioObjeto.getNombre().equals("admin")
					&& usuarioObjeto.getBaja() == 0) {
				model.addAttribute("PorfavorCambia", "No puede salir hasta que haya cambiado la contraseña");
				return "redirect:/";
			} else {
				usuarioObjeto.setBaja(1);
				usuarioServicio.actualizarUsuario(usuarioObjeto);
				pedidoServicio.detenerProcesamientoPedidos();
				session.invalidate();
				model.addAttribute("usuario", new UsuarioVO());
				return "Login";
			}
		} else {
			pedidoServicio.detenerProcesamientoPedidos();
			return "redirect:/";
		}
	}

	@GetMapping("/DarBaja")
	public String darBaja(Model model, @RequestParam String idUsuario) {

		UsuarioVO usuarioEcontrado = usuarioServicio.selectUsuarioPorId(Integer.parseInt(idUsuario));

		usuarioEcontrado.setBaja(0);

		usuarioServicio.actualizarUsuario(usuarioEcontrado);

		return "redirect:/ControladorUsuario/RutaListaUsuario";
	}

	@GetMapping("/DarBajaEmpleado")
	public String darBajaEmpleado(Model model, @RequestParam String idUsuario) {

		UsuarioVO usuarioEcontrado = usuarioServicio.selectUsuarioPorId(Integer.parseInt(idUsuario));

		usuarioEcontrado.setBaja(0);

		usuarioServicio.actualizarUsuario(usuarioEcontrado);

		return "redirect:/ControladorUsuario/RutaListaEmpleado";
	}

	@GetMapping("/DarAlta")
	public String darAlta(Model model, @RequestParam String idUsuario) {

		UsuarioVO usuarioEcontrado = usuarioServicio.selectUsuarioPorId(Integer.parseInt(idUsuario));

		usuarioEcontrado.setBaja(1);

		usuarioServicio.actualizarUsuario(usuarioEcontrado);

		return "redirect:/ControladorUsuario/RutaListaUsuario";
	}

	@GetMapping("/DarBajaAdmin")
	public String darBajaAdmin(Model model, @RequestParam String idUsuario) {

		UsuarioVO usuarioEcontrado = usuarioServicio.selectUsuarioPorId(Integer.parseInt(idUsuario));

		usuarioEcontrado.setBaja(0);

		usuarioServicio.actualizarUsuario(usuarioEcontrado);

		return "redirect:/ControladorUsuario/RutaListaAdmin";
	}

	@GetMapping("/DarAltaAdmin")
	public String darAltaAdmin(Model model, @RequestParam String idUsuario) {

		UsuarioVO usuarioEcontrado = usuarioServicio.selectUsuarioPorId(Integer.parseInt(idUsuario));

		usuarioEcontrado.setBaja(1);

		usuarioServicio.actualizarUsuario(usuarioEcontrado);

		return "redirect:/ControladorUsuario/RutaListaAdmin";
	}

	@PostMapping("/ComprobarClave")
	public String cambiarClave(@RequestParam("claveactual") String claveactual, Model model, HttpSession session) {

		if (session.getAttribute("clave").equals(claveactual)) {

			model.addAttribute("comprobado", true);

		} else {
			System.out.println("No coincide");
			model.addAttribute("erroractual", "Error la contraseña no es la actual");

		}

		return "CambioClave";
	}

	@PostMapping("/CambiarClave")
	public String cambiarClave(@RequestParam("nueva-clave") String nuevaClave,
			@RequestParam("confirmar-clave") String confirmarClave, Model model, HttpSession session) {

		if (nuevaClave.equals(confirmarClave)) {

			UsuarioVO u = (UsuarioVO) session.getAttribute("usuarioSesion");

			UsuarioVO usuario = usuarioServicio.selectUsuarioPorId(u.getId());

			usuario.setClave(Base64.getEncoder().encodeToString(confirmarClave.getBytes()));

			usuarioServicio.actualizarUsuario(usuario);

			model.addAttribute("actualizado", "La contraseña se actualizó con exito");

		} else {
			System.out.println("No coincide");
			model.addAttribute("error", "Error la contraseña no coincide");

		}

		return "CambioClave";
	}

	@PostMapping("/CambiarInformacion")
	public String cambiarClave(@ModelAttribute UsuarioVO usuario, HttpSession session) {

		UsuarioVO usuarioObjeto = (UsuarioVO) session.getAttribute("usuarioSesion");

		usuarioObjeto.setEmail(usuario.getEmail());
		usuarioObjeto.setNombre(usuario.getNombre());
		usuarioObjeto.setApellidos(usuario.getApellidos());

		usuarioServicio.actualizarUsuario(usuarioObjeto);

		return "redirect:/";
	}

	@GetMapping("/DarAltaEmpleado")
	public String darAltaEmpleado(Model model, @RequestParam String idUsuario) {

		UsuarioVO usuarioEcontrado = usuarioServicio.selectUsuarioPorId(Integer.parseInt(idUsuario));

		usuarioEcontrado.setBaja(1);

		usuarioServicio.actualizarUsuario(usuarioEcontrado);

		return "redirect:/ControladorUsuario/RutaListaEmpleado";
	}

	@PostMapping("/AltaUsuario")
	public String darAlta(Model model, @ModelAttribute UsuarioVO usuario) {

		usuario.setClave(Base64.getEncoder().encodeToString(usuario.getClave().getBytes()));

		usuarioServicio.insertarUsuario(usuario);

		return "redirect:/";
	}

	@PostMapping("/AltaEmpleado")
	public String darAltaEmpleado(Model model, @ModelAttribute UsuarioVO usuario) {

		usuario.setClave(Base64.getEncoder().encodeToString(usuario.getClave().getBytes()));

		usuarioServicio.insertarUsuario(usuario);

		return "redirect:/";
	}



	@PostMapping("/ActualizacionUsuario")
	public String darActualizacion(Model model, @ModelAttribute UsuarioVO usuario) {

		usuario.setClave(Base64.getEncoder().encodeToString(usuario.getClave().getBytes()));

		usuarioServicio.actualizarUsuario(usuario);

		return "redirect:/";
	}

	@PostMapping("/IniciarSesion")
	public String iniciarSesion(Model model, @ModelAttribute UsuarioVO usuario, HttpSession session)
			throws InterruptedException {

		List<UsuarioVO> listausuario = usuarioServicio.obtenerTodosUsuarios();

		String claveEncriptada = Base64.getEncoder().encodeToString(usuario.getClave().getBytes());

		for (UsuarioVO usuarioObjeto : listausuario) {
		    if (usuarioObjeto.getRol_id() != 1) { 
		        if (usuarioObjeto.getEmail().equals(usuario.getEmail()) && usuarioObjeto.getClave().equals(claveEncriptada)
		                && usuarioObjeto.getBaja() == 1
		                || usuarioObjeto.getRol_id() == 3 && usuarioObjeto.getNombre().equals("admin")
		                        && usuarioObjeto.getBaja() == 0)  {

		            session.setAttribute("clave", usuario.getClave());
		            session.setAttribute("usuarioSesion", usuarioObjeto);

		            List<UsuarioVO> listaAdministradores = usuarioServicio.obtenerUsuariosporRol(3);

		            if (!listaAdministradores.isEmpty()) {

		                System.out.println("Ya existe un administrador en la base de datos");

		            } else {

		                usuarioServicio.insertarUsuario(new UsuarioVO(3, "admin@gmail.com",
		                        Base64.getEncoder().encodeToString("admin".getBytes()), "admin", "adminapellido", 0));
		                return "redirect:/";
		            }

		            executorService.submit(() -> {
		                try {
		                    pedidoServicio.procesaPedidosAuto();

		                } catch (InterruptedException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		            });

		            return "redirect:/";
		        } else {

		            System.out.println("Usuario Incorrecto");
		        }
		    }
		}
		return "redirect:/";

	}

}
