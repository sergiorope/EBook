package com.example.tienda.ModeloDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tienda.ModeloVO.OpcionMenuVO;
import com.example.tienda.ModeloVO.PedidoVO;

@Repository
public interface OpcionMenuRepositorio extends JpaRepository<OpcionMenuVO, Integer> {
	
	@Query(value = "SELECT * FROM Opcionmenu WHERE rol_id = ?", nativeQuery = true)
    List<OpcionMenuVO> findAllbyId(int id);

}

