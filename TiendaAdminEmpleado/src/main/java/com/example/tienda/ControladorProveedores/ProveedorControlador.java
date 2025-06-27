package com.example.tienda.ControladorProveedores;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tienda.ModeloVO.ProveedorVO;
import com.example.tienda.ModeloVO.RolVO;
import com.example.tienda.ModeloVO.UsuarioVO;
import com.example.tienda.servicio.PedidoServicio;
import com.example.tienda.servicio.ProveedorServicio;
import com.example.tienda.servicio.RolServicio;
import com.example.tienda.servicio.UsuarioServicio;

@Controller
@RequestMapping("ControladorProveedor")
public class ProveedorControlador {

	@Autowired
	private ProveedorServicio proveedorServicio;

	@GetMapping("/RutaAltaProveedor")
	public String rutaAltaProveedor(Model model) {
		ProveedorVO proveedor = new ProveedorVO();

		model.addAttribute("proveedor", proveedor);

		return "AltaProveedor";
	}

	@GetMapping("/RutaListaProveedor")
	public String rutaLista(Model model) {

		List<ProveedorVO> listaproveedores = proveedorServicio.obtenerProveedores();

		model.addAttribute("listaproveedores", listaproveedores);
		return "ListaProveedores";
	}

	@GetMapping("/RutaActualizacionProveedor")
	public String rutaActualizacion(Model model, @RequestParam String idProveedor) {

		ProveedorVO proveedor = proveedorServicio.obtenerProveedorPorId(Integer.parseInt(idProveedor));

		model.addAttribute("Proveedor", proveedor);

		return "ActualizacionProveedor";
	}

	@GetMapping("/DarBajaProveedor")
	public String darBaja(Model model, @RequestParam String idProveedor) {

		ProveedorVO proveedor = proveedorServicio.obtenerProveedorPorId(Integer.parseInt(idProveedor));

		proveedor.setBaja(0);

		proveedorServicio.actualizarProveedor(proveedor);

		return "redirect:/ControladorUsuario/RutaListaProveedor";
	}

	@GetMapping("/DarAltaProveedor")
	public String darAlta(Model model, @RequestParam String idProveedor) {

		ProveedorVO proveedor = proveedorServicio.obtenerProveedorPorId(Integer.parseInt(idProveedor));

		proveedor.setBaja(1);

		proveedorServicio.actualizarProveedor(proveedor);

		return "redirect:/ControladorUsuario/RutaListaProveedor";
	}

	@PostMapping("/AltaProveedor")
	public String darAltaProveedor(Model model, @ModelAttribute ProveedorVO proveedor) {

		proveedorServicio.insertarProveedor(proveedor);

		return "redirect:/";
	}

	@PostMapping("/ActualizacionProveedor")
	public String darActualizacion(Model model, @ModelAttribute ProveedorVO proveedor) {

		proveedorServicio.actualizarProveedor(proveedor);

		return "redirect:/";
	}

}