package com.example.tienda.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tienda.ModeloDAO.ProveedorRepositorio;
import com.example.tienda.ModeloDAO.UsuarioRepositorio;
import com.example.tienda.ModeloVO.ProveedorVO;
import com.example.tienda.ModeloVO.UsuarioVO;

@Service
public class ProveedorServicio {

	@Autowired
	private ProveedorRepositorio proveedorRepositorio;
	
	public List<ProveedorVO> obtenerProveedores() {
		return proveedorRepositorio.findAll();
	}
	
	public ProveedorVO obtenerProveedorPorId(int proveedor_id) {
		return proveedorRepositorio.findById(proveedor_id).get();
	}
	
	public void insertarProveedor(ProveedorVO proveedor) {
		proveedorRepositorio.save(proveedor);
	}
	
	public void actualizarProveedor(ProveedorVO proveedor) {
		proveedorRepositorio.save(proveedor);
	}
	
	
}

