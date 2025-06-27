package Servicio;

import java.util.List;

import ModeloDAO.PedidoDAO;
import ModeloDAO.UsuarioDAO;
import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;

public class PedidoServicio {

	public static int RegistrarPedidoLineas(PedidoVO pedido) {
		int id = PedidoDAO.insertarPedido(pedido);
		return id;
	}

	public static List<PedidoVO> selectTodosPedidos() {
		return PedidoDAO.selectPedidosTodos();

	}

	public static List<PedidoVO> ObtenerPedidosUsuario(PedidoVO pedido) {
		return PedidoDAO.getPedidos(pedido);
	}

	public static PedidoVO ObtenerPedidosId(int id) {
		return PedidoDAO.selectPedidos(id);
	}

	public static Double CalcularCancelacionLinea(PedidoVO pedido, DetalleVO detalle) {

		Double totalPedido = pedido.getTotal()
				- ((detalle.getTotal() * (detalle.getImpuesto() / 100)) + detalle.getTotal());

		return totalPedido;

	}

	public static void ActualizarEstado(String estado, int id) {
		PedidoDAO.ActualizarPedido(estado, id);
	}

	public static void ConfirmarEnvioPedido(String numfactura, String estado, int id) {
		PedidoDAO.ActualizarPedidoEnvio(numfactura, estado, id);
	}

	public static void Actualizar(PedidoVO pedido) {
		PedidoDAO.ActualizarPedido(pedido);
	}

}
