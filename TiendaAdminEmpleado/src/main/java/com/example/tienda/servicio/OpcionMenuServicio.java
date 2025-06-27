package com.example.tienda.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tienda.ModeloDAO.OpcionMenuRepositorio;
import com.example.tienda.ModeloDAO.ProductoRepositorio;
import com.example.tienda.ModeloVO.OpcionMenuVO;
import com.example.tienda.ModeloVO.ProductoVO;

@Service
public class OpcionMenuServicio {
	
	@Autowired
	private OpcionMenuRepositorio opcionmenurepositorio;
	
	public List<OpcionMenuVO> obtenerOpcionesPorId(int id) {
	    return opcionmenurepositorio.findAllbyId(id);
	}
}
