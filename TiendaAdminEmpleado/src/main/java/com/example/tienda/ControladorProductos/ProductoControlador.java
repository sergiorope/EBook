package com.example.tienda.ControladorProductos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tienda.ModeloDAO.ProductoRepositorio;
import com.example.tienda.ModeloVO.CategoriaVO;
import com.example.tienda.ModeloVO.PedidoVO;
import com.example.tienda.ModeloVO.ProductoVO;
import com.example.tienda.ModeloVO.ProveedorVO;
import com.example.tienda.ModeloVO.UsuarioVO;
import com.example.tienda.servicio.CategoriaServicio;
import com.example.tienda.servicio.DetalleServicio;
import com.example.tienda.servicio.ProductoServicio;
import com.example.tienda.servicio.ProveedorServicio;
import com.example.tienda.servicio.ValoracionServicio;

@Controller
@RequestMapping("ControladorProducto")
public class ProductoControlador {

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private CategoriaServicio categoriaServicio;

	@Autowired
	private DetalleServicio detalleServicio;

	@Autowired
	private ProveedorServicio proveedorServicio;

	@Autowired
	private ValoracionServicio valoracionServicio;

	@GetMapping("/RutaAltaProducto")
	public String rutaAlta(Model model) {
		ProductoVO producto = new ProductoVO();

		model.addAttribute("producto", producto);

		List<CategoriaVO> categorias = categoriaServicio.obtenerCategorias();
		List<ProveedorVO> proveedores = proveedorServicio.obtenerProveedores();

		model.addAttribute("listacategorias", categorias);
		model.addAttribute("listaproveedores", proveedores);

		return "AltaProducto";
	}

	@GetMapping("/RutaListaProducto")
	public String rutaLista(Model model) {

		List<ProductoVO> catalogo = productoServicio.obtenerCatalogo();

		Map<Integer, String> productoporcategoria = productoServicio.MostrarListaCategoria();
		Map<Integer, String> productoporproveedor = productoServicio.MostrarListaProveedor();

		model.addAttribute("productoporcategoria", productoporcategoria);
		model.addAttribute("productoporproveedor", productoporproveedor);

		model.addAttribute("listaproductos", catalogo);
		return "ListaProductos";
	}

	@GetMapping("/RutaGrafico")
	public String rutaGrafico(Model model) {

		List<ProductoVO> listaProductos = productoServicio.obtenerCatalogo();
		Map<Integer, Integer> productoPorVenta = new HashMap<>();
		Map<Integer, Double> valoracionPorProducto = new HashMap<>();

		List<String> nombresProductos = new ArrayList<>();
		List<String> nombresProductosVentas = new ArrayList<>();

		int ventasMinimas = 2;
		double valoracionMinima = 0.5;

		for (ProductoVO producto : listaProductos) {
			int ventas = detalleServicio.obtenerVentaProducto(producto.getId());
			Double media = valoracionServicio.asociarValoracionProducto(producto.getId());

			if (ventas >= ventasMinimas) {
				nombresProductosVentas.add(producto.getNombre());
				productoPorVenta.put(producto.getId(), ventas);

			}

			if (media >= valoracionMinima) {
				nombresProductos.add(producto.getNombre());
				valoracionPorProducto.put(producto.getId(), media);

			}

		}

		for (Map.Entry<Integer, Double> entry : valoracionPorProducto.entrySet()) {
			System.out.println("ID del producto: " + entry.getKey() + ", Valoración media: " + entry.getValue());
		}

		// Agregar los datos filtrados a la vista
		model.addAttribute("valoracionporproducto", valoracionPorProducto);
		model.addAttribute("productoporventas", productoPorVenta);
		model.addAttribute("nombresProductos", nombresProductos);
		model.addAttribute("nombresProductosVentas", nombresProductosVentas);

		return "Grafico";
	}

	@GetMapping("/RutaImportacionExportacion")
	public String RutaImportacionExportacion(Model model) {

		return "ImportacionExportacion";
	}

	@PostMapping("/Importacion")
	public String importacion(Model model) {

		productoServicio.importarProductosDesdeExcel("E:/Users/MADRI/random/Proyecto_interfaces/odoo/productos.xls");

		return "redirect:/";
	}

	@PostMapping("/Exportacion")
	public String exportacion(Model model) {

		productoServicio
				.exportarProductosAExcel("D:/TiendaAdminEmpleado/src/main/resources/exportaciones/Catalogo.xls");

		return "redirect:/";
	}

	@GetMapping("/DarBaja")
	public String darBaja(Model model, @RequestParam String idProducto) {

		ProductoVO productoEcontrado = productoServicio.selectProductoPorId(Integer.parseInt(idProducto));

		productoEcontrado.setBaja(0);

		productoServicio.actualizarProducto(productoEcontrado);

		return "redirect:/ControladorProducto/RutaListaProducto";
	}

	@GetMapping("/DarAlta")
	public String darAlta(Model model, @RequestParam String idProducto) {

		ProductoVO productoEcontrado = productoServicio.selectProductoPorId(Integer.parseInt(idProducto));

		productoEcontrado.setBaja(1);

		productoServicio.actualizarProducto(productoEcontrado);

		return "redirect:/ControladorProducto/RutaListaProducto";
	}

	@GetMapping("/RutaActualizacionProducto")
	public String rutaActualizacion(Model model, @RequestParam String idProducto) {

		ProductoVO producto = productoServicio.selectProductoPorId(Integer.parseInt(idProducto));

		List<CategoriaVO> categorias = categoriaServicio.obtenerCategorias();
		List<ProveedorVO> proveedores = proveedorServicio.obtenerProveedores();
		model.addAttribute("listacategorias", categorias);
		model.addAttribute("listaproveedores", proveedores);

		model.addAttribute("Producto", producto);

		return "ActualizacionProducto";
	}

	@PostMapping("/AltaProducto")
	public String darAlta(Model model, @ModelAttribute ProductoVO producto) {

		productoServicio.insertarProducto(producto);

		return "redirect:/";
	}

	@PostMapping("/ActualizacionProducto")
	public String darActualizacion(Model model, @ModelAttribute ProductoVO producto) {

		productoServicio.actualizarProducto(producto);

		return "redirect:/";
	}
}
