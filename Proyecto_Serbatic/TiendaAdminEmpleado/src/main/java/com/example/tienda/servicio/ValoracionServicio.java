package com.example.tienda.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tienda.ModeloDAO.ConfiguracionRepositorio;
import com.example.tienda.ModeloDAO.ValoracionRepositorio;
import com.example.tienda.ModeloVO.ValoracionVO;

@Service
public class ValoracionServicio {

	@Autowired
	private ValoracionRepositorio valoracionrepositorio;

	public List<ValoracionVO> obtenerValoracionesProducto(int usuario_id) {
		return valoracionrepositorio.findByProductoId(usuario_id);
	}

	public Double asociarValoracionProducto(int producto_id) {
	    double suma = 0;

	    List<ValoracionVO> listavaloraciones = obtenerValoracionesProducto(producto_id);

	    for (ValoracionVO valoracion : listavaloraciones) {
	        suma += valoracion.getValoracion();
	    }
	    if (!listavaloraciones.isEmpty()) {
	        return suma / listavaloraciones.size();
	    } else {
	        return 0.0; 
	    }
	}

}
