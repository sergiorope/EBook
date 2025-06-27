package Servicio;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Control.controlProducto.CarritoAñadirServlet;
import ModeloDAO.CategoriaDAO;
import ModeloDAO.DetalleDAO;
import ModeloDAO.ProductoDAO;
import ModeloDAO.UsuarioDAO;
import ModeloVO.CategoriaVO;
import ModeloVO.DetalleVO;
import ModeloVO.ProductoVO;
import ModeloVO.UsuarioVO;

public class ProductoServicio {

	public static int validarStock(ProductoVO producto) {
		List<ProductoVO> productos = ProductoDAO.findAll();

		for (ProductoVO p : productos) {
			if (p.getId() == producto.getId()) {
				return p.getStock();
			}
		}

		return -1;
	}

    public void agregarProducto(HttpServletRequest request, double impuesto) {
		String precioString = request.getParameter("precio");
		double precioDouble = Double.valueOf(precioString);
		precioDouble += precioDouble * impuesto;
		CarritoAñadirServlet.dinero += precioDouble;
		actualizarDinero(request);
	}

	public void eliminarProducto(HttpServletRequest request, double impuesto) {
		String precioString = request.getParameter("precio");
		double precioDouble = Double.valueOf(precioString);
		precioDouble += precioDouble * impuesto;
		CarritoAñadirServlet.dinero -= precioDouble;
		if (CarritoAñadirServlet.dinero < 0) {
			CarritoAñadirServlet.dinero = 0;
		}
		actualizarDinero(request);
	}

	public void mostrarProducto(HttpServletRequest request, String param) {
		String id = request.getParameter("id");
		int idInt = Integer.parseInt(id);
		ProductoVO p = ProductoDAO.MostrarProducto(idInt);
		request.getSession().setAttribute("producto", p);
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>) request.getSession()
				.getAttribute("carrito");
		if (carrito != null) {
			Integer contador = carrito.getOrDefault(p, 0);
			if (param.equals("anadir")) {
				carrito.put(p, contador + 1);
			}
			if (param.equals("eliminar")) {
				if (contador <= 1) {
					carrito.remove(p);
				} else {
					carrito.put(p, contador - 1);
				}
			}
			int tamañoCarrito = carrito.size();
			request.getSession().setAttribute("numcarrito", tamañoCarrito);
		}
	}

	private void actualizarDinero(HttpServletRequest request) {
		double dineroFormateado = Math.round(CarritoAñadirServlet.dinero * 100.0) / 100.0;
		request.getSession().setAttribute("dinerofin", dineroFormateado);
	}

	public static List<ProductoVO> obtenerCatalogo() {
		return ProductoDAO.findAll();

	}

	public static void actualizarStock(ProductoVO producto) {
		ProductoDAO.ActualizarProducto(producto);

	}

	public static void actualizarProducto(ProductoVO producto) {
		ProductoDAO.ActualizarProductoEntero(producto);

	}
	
	public static ProductoVO obtenerProductoId(int id) {
		return ProductoDAO.getProductoId(id);

	}
	
	public static ProductoVO obtenerProductoRandom(int categoria_id) {
		return ProductoDAO.getProductoRandomRecomendacion(categoria_id);

	}

	public static ProductoVO mostrarProductoPorId(int id) {
		return ProductoDAO.MostrarProducto(id);

	}

	public static ProductoVO ObtenerProductoLinea(DetalleVO detalle) {
		return ProductoDAO.getNombresProductos(detalle);
	}

	public static List<ProductoVO> ObtenerPorductosCateg(int i) {
		return ProductoDAO.MostrarProductosPorCategoria(i);

	}

	public static void darAltaProducto(ProductoVO producto) {
		ProductoDAO.insertarProducto(producto);

	}

}
