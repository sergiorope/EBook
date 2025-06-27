package Servicio;

import java.util.List;

import ModeloDAO.ConfiguracionDAO;
import ModeloDAO.DetalleDAO;
import ModeloVO.ConfiguracionVO;
import ModeloVO.DetalleVO;

public class ConfiguracionServicio {

	public static String ObtenerValor(String clave) {
		return ConfiguracionDAO.selectConfiguracion(clave);
	}

	public static void ActualizarValor(String valor, String clave) {
		ConfiguracionDAO.updateConfiguracion(valor, clave);
		
	}

}
