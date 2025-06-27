package Servicio;

import java.util.List;

import ModeloDAO.DetalleDAO;
import ModeloDAO.PedidoDAO;
import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;

public class DetalleServicio {
	public static void registrarLineas(DetalleVO detalle) {
		DetalleDAO.insertarLineasDetalle(detalle);
	}

	public static List<DetalleVO> ObtenerDetallesPedido(DetalleVO detalle) {
		return DetalleDAO.getDetalles(detalle);
	}

	public static DetalleVO ObtenerDetallePorId(int id) {
		return DetalleDAO.selectDetalle(id);
	}

	public static void BorrarLinea(int id) {
		DetalleDAO.deleteDetalle(id);
	}

}
