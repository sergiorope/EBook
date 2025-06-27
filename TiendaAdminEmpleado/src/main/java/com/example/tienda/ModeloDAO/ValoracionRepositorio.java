package com.example.tienda.ModeloDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tienda.ModeloVO.DetalleVO;
import com.example.tienda.ModeloVO.UsuarioVO;
import com.example.tienda.ModeloVO.ValoracionVO;

@Repository
public interface ValoracionRepositorio extends JpaRepository<ValoracionVO, Integer> {
	
	@Query(value = "SELECT * FROM Valoracion WHERE producto_id = ?", nativeQuery = true)
    List<ValoracionVO> findByProductoId(int producto_id);
	

}
