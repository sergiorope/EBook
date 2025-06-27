package com.example.tienda.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List; // Asegúrate de importar List
import com.example.tienda.ModeloDAO.*;
import com.example.tienda.ModeloVO.*;
import com.example.tienda.servicio.OpcionMenuServicio;
import com.example.tienda.servicio.PedidoServicio;
import com.example.tienda.servicio.ProductoServicio;
import com.example.tienda.servicio.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class EntradaControlador {

	@Autowired
	private ProductoServicio productoservicio;

	@Autowired
	private UsuarioServicio usuarioservicio;

	@Autowired
	private OpcionMenuServicio opcionmenuservicio;
	
	
	@Autowired
	private PedidoServicio pedidoServicio;

	@GetMapping("")
	public String mostrarInicio(Model model, HttpSession session) {

		UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuarioSesion");

		if (usuario != null) {

			List<OpcionMenuVO> opciones = opcionmenuservicio.obtenerOpcionesPorId(usuario.getRol_id());
			List<ProductoVO> catalogo = productoservicio.obtenerCatalogo();

			model.addAttribute("catalogo", catalogo);
			session.setAttribute("listaopciones", opciones);;

			UsuarioVO usuarioObjeto = usuarioservicio.selectUsuarioPorId(usuario.getId());

			if (usuarioObjeto.getBaja() == 0 && usuario.getRol_id() == 3 && usuario.getNombre().equals("admin")) {

				model.addAttribute("mensajeClave", "Debe cambiar la contraseña");
			}
			
		

			return "index";
			
			

		} else {

			List<ProductoVO> catalogo = productoservicio.obtenerCatalogo();

			model.addAttribute("catalogo", catalogo);

			return "index";
		}
	}

}