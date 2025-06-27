package Servicio;

import java.util.List;

import ModeloDAO.ConfiguracionDAO;
import ModeloDAO.OpcionMenuDAO;
import ModeloVO.OpcionMenuVO;

public class OpcionmenuServicio {
	
	public static List<OpcionMenuVO> ObtenerOpciones(int rol_id) {
		return OpcionMenuDAO.SelectOpcionesMenu(rol_id);
	}

}
