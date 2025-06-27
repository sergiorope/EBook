package Servicio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ModeloDAO.UsuarioDAO;
import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;
import ModeloVO.UsuarioVO;

public class UsuarioServicio {

	public static boolean validarUsuario(String email, String clave) {

		UsuarioVO usuario = UsuarioDAO.getUsuario(email, clave);

		if (usuario.getRol_id() != 1) {

			return false;
		} else {

			return UsuarioDAO.getUsuario(email, clave) != null;
		}

	}

	public static String obtenerNombre(String email, String clave) {
		UsuarioVO usuarioEncontrado = UsuarioDAO.getUsuario(email, clave);
		return usuarioEncontrado != null ? usuarioEncontrado.getNombre() : null;
	}

	public static Boolean comprobarEmail(String email) {

		boolean repetido = false;

		List<UsuarioVO> listausuario = UsuarioDAO.findAllUsuarios();

		for (UsuarioVO usuario : listausuario) {

			System.out.println(usuario.getEmail());

			if (usuario.getEmail().equals(email)) {

				repetido = true;
			}
		}

		return repetido;

	}

	public static ProductoVO Recomendacion(HttpServletRequest request) {

		int idusuario = (int) request.getSession().getAttribute("id_usuario");

		List<DetalleVO> listadetalles = new ArrayList<>();
		List<ProductoVO> listaproductos = new ArrayList<>();
		List<Integer> listacategoria = new ArrayList<>();

		UsuarioVO usuario = obtenerUsuarioEntero(idusuario);

		PedidoVO pedido = new PedidoVO();

		pedido.setUsuario_id(idusuario);

		List<PedidoVO> listapedidos = PedidoServicio.ObtenerPedidosUsuario(pedido);

		for (PedidoVO pedidos : listapedidos) {

			System.out.println(pedidos.toString());

			DetalleVO detalle = new DetalleVO();
			detalle.setPedido_id(pedidos.getId());

			List<DetalleVO> detalles = DetalleServicio.ObtenerDetallesPedido(detalle);

			listadetalles.addAll(detalles);
		}

		for (DetalleVO detalle : listadetalles) {

			ProductoVO producto = ProductoServicio.obtenerProductoId(detalle.getProducto_id());

			listaproductos.add(producto);
		}

		for (ProductoVO producto : listaproductos) {

			listacategoria.add(producto.getCategoria_id());
		}

		int catRep = encontrarNumeroMasRepetido(listacategoria);

		ProductoVO producto = ProductoServicio.obtenerProductoRandom(catRep);

		return producto;
	}

	public static int encontrarNumeroMasRepetido(List<Integer> lista) {

		if (lista.isEmpty()) {
			System.out.println("La lista esta vacia");
		} else {
			Collections.sort(lista);

			int contador = 1;
			int maxContador = 1;

			int numeroMasRepetido = lista.get(0);
			int numeroActual = lista.get(0);

			for (int i = 1; i < lista.size(); i++) {
				if (lista.get(i) == numeroActual) {
					contador++;
				} else {
					if (contador > maxContador) {
						maxContador = contador;
						numeroMasRepetido = numeroActual;
					}
					contador = 1;
					numeroActual = lista.get(i);
				}
			}

			if (contador > maxContador) {
				numeroMasRepetido = numeroActual;
			}

			return numeroMasRepetido;

		}
		return 0;

	}

	public static int obtenerRol_id(String email, String clave) {
		UsuarioVO usuarioEncontrado = UsuarioDAO.getUsuario(email, clave);
		return usuarioEncontrado.getRol_id();
	}

	public static int obtenerId(String email, String clave) {
		UsuarioVO usuarioEncontrado = UsuarioDAO.getUsuario(email, clave);

		System.out.println(usuarioEncontrado.getId());

		return usuarioEncontrado.getId();

	}

	public static UsuarioVO obtenerUsuario(String email, String clave) {
		return UsuarioDAO.getUsuario(email, clave);
	}

	public static void actualizarUsuario(UsuarioVO usuario) {
		UsuarioDAO.updateUsuario(usuario);
	}

	public static void actualizarUsuarioContrasena(String clave, int id) {
		UsuarioDAO.updateUsuarioClave(id, clave);
	}

	public static UsuarioVO obtenerUsuarioEntero(int id) {
		return UsuarioDAO.getUsuarioEntero(id);
	}

	public static List<UsuarioVO> obtenerUsuarioEnteroRol(int rol_id) {
		return UsuarioDAO.getUsuariosPorRol(rol_id);
	}

	public static void registrarUsuario(UsuarioVO usuario) {
		UsuarioDAO.insertarUsuario(usuario);
	}
}
