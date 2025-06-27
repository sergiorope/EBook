package com.example.tienda.ControladorPedidos;

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
import com.example.tienda.ModeloVO.UsuarioVO;
import com.example.tienda.servicio.CategoriaServicio;
import com.example.tienda.servicio.ConfiguracionServicio;
import com.example.tienda.servicio.DetalleServicio;
import com.example.tienda.servicio.PedidoServicio;
import com.example.tienda.servicio.ProductoServicio;
import com.example.tienda.servicio.UsuarioServicio;

@Controller
@RequestMapping("ControladorPedido")
public class PedidoControlador {

	@Autowired
	private PedidoServicio pedidoServicio;

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private ConfiguracionServicio configuracionServicio;

	@Autowired
	private ProductoServicio productoservicio;

	@Autowired
	private DetalleServicio detalleservicio;

	@GetMapping("/RutaListaPedido")
	public String rutaLista(Model model) {

		List<PedidoVO> listapedidos = pedidoServicio.obtenerPedidos();

		Map<Integer, String> nombreporPedido = new HashMap<>();

		for (PedidoVO pedido : listapedidos) {

			UsuarioVO usuario = usuarioServicio.selectUsuarioPorId(pedido.getUsuario_id());

			nombreporPedido.put(pedido.getId(), usuario.getNombre());
		}

		model.addAttribute("nombreporpedido", nombreporPedido);
		model.addAttribute("listapedido", listapedidos);

		return "ListaPedidos";
	}

	@GetMapping("/ConfirmarPedido")
	public String darConfirmar(Model model, @ModelAttribute PedidoVO pedido) {

		PedidoVO pedidoObject = pedidoServicio.obtenerPedidoporId(pedido.getId());

		List<DetalleVO> listadetalles = detalleservicio.obtenerTodasLineasPedido(pedido.getId());

		PedidoVO pedidoEncontrado = pedidoServicio.obtenerPedidoporId(pedido.getId());
		
		UsuarioVO usuario = usuarioServicio.selectUsuarioPorId(pedidoObject.getUsuario_id());

		String valor = configuracionServicio.obtenerValor("factura");

		int valorInt = Integer.parseInt(valor);

		pedidoEncontrado.setEstado("E");
		pedidoEncontrado.setNumfactura("FAC-000" + valorInt);
		pedidoServicio.ConfirmarPedido(pedidoEncontrado);

		valorInt++;

		configuracionServicio.actualizarValor(Integer.toString(valorInt), "factura");

		StringBuilder mensaje = pedidoServicio.ConstruirMsg(pedidoEncontrado, listadetalles, pedidoObject);

		pedidoServicio.envioCorreoConfirmacionPedido(usuario, mensaje);

		return "redirect:/ControladorPedido/RutaListaPedido";
	}

	@GetMapping("/CancelarPedido")
	public String darCancelacion(Model model, @ModelAttribute PedidoVO pedido) {

		PedidoVO pedidoEncontrado = pedidoServicio.obtenerPedidoporId(pedido.getId());

		List<DetalleVO> listaDetalles = detalleservicio.obtenerTodasLineasPedido(pedidoEncontrado.getId());

		detalleservicio.ActualizarStockCancelacion(listaDetalles);

		pedidoEncontrado.setEstado("C");
		pedidoServicio.ConfirmarPedido(pedidoEncontrado);

		return "ListaPedidos";
	}

}
