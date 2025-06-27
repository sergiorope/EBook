package com.example.tienda.ModeloDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tienda.ModeloVO.ProductoVO;
import com.example.tienda.ModeloVO.UsuarioVO;
	
	@Repository
	public interface UsuarioRepositorio extends JpaRepository<UsuarioVO, Integer> {
		
		@Query(value = "SELECT * FROM Usuario WHERE rol_id = ?", nativeQuery = true)
	    List<UsuarioVO> findByRolId(int rolId);
		
		
		
		

	}


