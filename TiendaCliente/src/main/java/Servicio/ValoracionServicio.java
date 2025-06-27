package Servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ModeloDAO.OpcionMenuDAO;
import ModeloDAO.ValoracionDAO;
import ModeloVO.OpcionMenuVO;
import ModeloVO.UsuarioVO;
import ModeloVO.ValoracionVO;

public class ValoracionServicio {

	public static Double ObtenerValoracionMedia(int producto_id) {
		double media = 0;
		int cont = 0;

		List<ValoracionVO> listavaloraciones = ValoracionDAO.getValoracion(producto_id);

		for (ValoracionVO valoracion : listavaloraciones) {

			cont++;

			media += valoracion.getValoracion();

		}

		return media = media / cont;
	}

	public static List<ValoracionVO> ObtenerResenasProducto(int producto_id) {

		List<ValoracionVO> listavaloraciones = ValoracionDAO.getValoracion(producto_id);

		return listavaloraciones;

	}

	public static Map<ValoracionVO, String> ObtenerResenasNombreProducto(int producto_id) {

		Map<ValoracionVO, String> nombreporValoracion = new HashMap<>();

		List<ValoracionVO> listavaloraciones = ValoracionDAO.getValoracion(producto_id);

		for (ValoracionVO valoracion : listavaloraciones) {
			
			System.out.println(valoracion.getUsuario_id()+"efer");

			UsuarioVO usuario = UsuarioServicio.obtenerUsuarioEntero(valoracion.getUsuario_id());
			
			System.out.println(usuario.getId()+"ww");

			nombreporValoracion.put(valoracion, usuario.getNombre());
		}

		return nombreporValoracion;

	}
	
	
	public static List<ValoracionVO> ObtenerValoracionesComprobar(int producto_id, int usuario_id) {

		List<ValoracionVO> listavaloraciones = ValoracionDAO.getValoracionComprobar(producto_id, usuario_id);

		
		return listavaloraciones;

	}
	
	public static void InsertarValoracion(ValoracionVO valoracion) {

		ValoracionDAO.insertarValoracion(valoracion);

	}
	
	

}
