package com.example.tienda.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tienda.ModeloDAO.CategoriaRepositorio;
import com.example.tienda.ModeloDAO.ProductoRepositorio;
import com.example.tienda.ModeloDAO.RolRepositorio;
import com.example.tienda.ModeloVO.CategoriaVO;
import com.example.tienda.ModeloVO.ProductoVO;
import com.example.tienda.ModeloVO.RolVO;

@Service
public class RolServicio {
	
	   @Autowired
	    private RolRepositorio rolRepositorio;

	    public List<RolVO> obtenerRoles() {
	        return rolRepositorio.findAll();
	    }

	    public void insertarRol(RolVO rol) {
	    	rolRepositorio.save(rol);
	    }
	    
	    public RolVO obtenerRolId(int id) {
	        return rolRepositorio.findById(id).get();
	    }

}
