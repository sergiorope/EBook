package com.example.tienda.ControladorConfiguracion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.example.tienda.ModeloVO.ConfiguracionVO;
import com.example.tienda.ModeloVO.DetalleVO;
import com.example.tienda.ModeloVO.PedidoVO;
import com.example.tienda.ModeloVO.ProductoVO;
import com.example.tienda.ModeloVO.RolVO;
import com.example.tienda.ModeloVO.UsuarioVO;
import com.example.tienda.servicio.CategoriaServicio;
import com.example.tienda.servicio.ConfiguracionServicio;
import com.example.tienda.servicio.DetalleServicio;
import com.example.tienda.servicio.PedidoServicio;
import com.example.tienda.servicio.ProductoServicio;
import com.example.tienda.servicio.UsuarioServicio;

@Controller
@RequestMapping("ControladorConfiguracion")
public class ConfiguracionControlador {

	@Autowired
	private ConfiguracionServicio configuracionServicio;

	@GetMapping("/RutaConfiguracionActualizacion")
	public String rutaListaActualizacion(Model model, @RequestParam String idConfiguracion) {

		ConfiguracionVO configuracion = configuracionServicio.obtenerConfiguracionPorId(Integer.parseInt(idConfiguracion));

		model.addAttribute("Configuracion", configuracion);

		return "Configuracion";
	}
	
	@GetMapping("/RutaListaConfiguracion")
	public String rutaListaConfiguracion(Model model) {

		List<ConfiguracionVO> configuracion = configuracionServicio.obtenerConfiguraciones();

		model.addAttribute("configuracionlista", configuracion);

		return "ListaConfiguracion";
	}

	@PostMapping("/ActualizarConfiguracion")
	public String darConfirmar(Model model, @ModelAttribute ConfiguracionVO configuracion) {
		
		System.out.println(configuracion.toString()+"wdwdww");
		
	

		configuracionServicio.actualizarConfiguracion(configuracion);
		
		return "redirect:/";
	}

}
