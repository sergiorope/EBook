package com.example.tienda.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.tienda.ModeloDAO.ProductoRepositorio;
import com.example.tienda.ModeloDAO.UsuarioRepositorio;
import com.example.tienda.ModeloVO.ProductoVO;
import com.example.tienda.ModeloVO.RolVO;
import com.example.tienda.ModeloVO.UsuarioVO;

@Service
public class UsuarioServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepository;

	@Autowired
	private RolServicio rolservicio;

	public List<UsuarioVO> obtenerTodosUsuarios() {

		return usuarioRepository.findAll();
	}

	public List<UsuarioVO> obtenerUsuariosporRol(int id) {

		return usuarioRepository.findByRolId(id);
	}

	public void insertarUsuario(UsuarioVO usuario) {
		usuarioRepository.save(usuario);
	}

	public void actualizarUsuario(UsuarioVO usuario) {
		usuarioRepository.save(usuario);

	}

	public UsuarioVO selectUsuarioPorId(int id) {
		return usuarioRepository.findById(id).get();
	}

	public List<UsuarioVO> selectAdministrador(int rol_id, int id) {
	    List<UsuarioVO> listaUsuarios = usuarioRepository.findByRolId(rol_id);
	    List<UsuarioVO> listaAdminId = new ArrayList<>();

	    for (UsuarioVO usuario : listaUsuarios) {
	        if (usuario.getId() != id) {
	            listaAdminId.add(usuario);
	        }
	    }

	    return listaAdminId;
	}

	public Map<Integer, String> mapeoUsuarioRol(int rol_id) {

		List<UsuarioVO> listaUsuarios = obtenerUsuariosporRol(rol_id);

		Map<Integer, String> usuarioporRol = new HashMap<>();

		for (UsuarioVO usuario : listaUsuarios) {

			RolVO rol = rolservicio.obtenerRolId(usuario.getRol_id());

			usuarioporRol.put(usuario.getId(), rol.getNombre());
		}
		return usuarioporRol;
	}

}