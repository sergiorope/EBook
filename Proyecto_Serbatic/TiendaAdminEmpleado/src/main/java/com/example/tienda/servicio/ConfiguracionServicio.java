package com.example.tienda.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tienda.ModeloDAO.ConfiguracionRepositorio;
import com.example.tienda.ModeloDAO.PedidosRepositorio;
import com.example.tienda.ModeloVO.ConfiguracionVO;
import com.example.tienda.ModeloVO.PedidoVO;

@Service
public class ConfiguracionServicio {

	@Autowired
	private ConfiguracionRepositorio configuracionrepositorio;

	public String obtenerValor(String clave) {
		return configuracionrepositorio.findByClave(clave);
	}

	public List<ConfiguracionVO> obtenerConfiguraciones() {
		return configuracionrepositorio.findAll();
	}

	public ConfiguracionVO obtenerConfiguracionPorId(int configuracion_id) {
		return configuracionrepositorio.findById(configuracion_id).get();
	}

	public void actualizarConfiguracion(ConfiguracionVO configuracion) {
		configuracionrepositorio.save(configuracion);
	}

	@Transactional
	public void actualizarValor(String valor, String clave) {
		configuracionrepositorio.updateByClave(valor, clave);
	}
}
