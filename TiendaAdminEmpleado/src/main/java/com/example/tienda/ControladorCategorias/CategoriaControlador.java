package com.example.tienda.ControladorCategorias;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tienda.ModeloVO.CategoriaVO;
import com.example.tienda.ModeloVO.PedidoVO;
import com.example.tienda.ModeloVO.ProductoVO;
import com.example.tienda.ModeloVO.UsuarioVO;
import com.example.tienda.servicio.CategoriaServicio;
import com.example.tienda.servicio.ConfiguracionServicio;
import com.example.tienda.servicio.PedidoServicio;
import com.example.tienda.servicio.UsuarioServicio;

@Controller
@RequestMapping("ControladorCategoria")
public class CategoriaControlador {

	@Autowired
	private CategoriaServicio categoriaServicio;

	@GetMapping("/RutaListaCategoria")
	public String rutaLista(Model model) {

		List<CategoriaVO> listacategorias = categoriaServicio.obtenerCategorias();

		model.addAttribute("listacateg", listacategorias);

		return "ListaCategorias";
	}
	
	
	@GetMapping("/RutaAltaCategoria")
	public String rutaAlta(Model model) {

		CategoriaVO categoria = new CategoriaVO();

		model.addAttribute("categoriaObjeto", categoria);

		return "AltaCategoria";
	}


	@GetMapping("/RutaActualizacionCategoria")
	public String rutaActualizacion(Model model, @RequestParam String idCategoria) {

		CategoriaVO categoria = categoriaServicio.obtenerCategoriaId(Integer.parseInt(idCategoria));

		model.addAttribute("categoria", categoria);

		return "ActualizacionCategoria";
	}

	@PostMapping("/ActualizacionCategoria")
	public String Actualizacion(Model model, @ModelAttribute CategoriaVO categoria) {

		categoriaServicio.insertarCategoria(categoria);

		return "redirect:/ControladorCategoria/RutaListaCategoria;";
	}
	
	@PostMapping("/AltaCategoria")
	public String Alta(Model model, @ModelAttribute CategoriaVO categoria) {

		categoriaServicio.insertarCategoria(categoria);

		return "redirect:/ControladorCategoria/RutaAltaCategoria;";
	}
	
	@GetMapping("/DarBaja")
	public String darBaja(Model model, @RequestParam String idCategoria) {

		CategoriaVO categoriaEncontrada = categoriaServicio.obtenerCategoriaId(Integer.parseInt(idCategoria));

		categoriaEncontrada.setBaja(0);

		categoriaServicio.actualizarCategoria(categoriaEncontrada);

		return "redirect:/ControladorCategoria/RutaListaCategoria";
	}

	@GetMapping("/DarAlta")
	public String darAlta(Model model, @RequestParam String idCategoria) {

		CategoriaVO categoriaEncontrada = categoriaServicio.obtenerCategoriaId(Integer.parseInt(idCategoria));

		categoriaEncontrada.setBaja(1);

		categoriaServicio.actualizarCategoria(categoriaEncontrada);

		return "redirect:/ControladorCategoria/RutaListaCategoria";
	}

	
	

}