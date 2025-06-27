package com.example.tienda.ModeloDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tienda.ModeloVO.DetalleVO;
import com.example.tienda.ModeloVO.PedidoVO;
import com.example.tienda.ModeloVO.UsuarioVO;

@Repository
public interface DetalleRepositorio extends JpaRepository<DetalleVO, Integer> {
	
	@Query(value = "SELECT * FROM Detalle WHERE pedido_id = ?", nativeQuery = true)
    List<DetalleVO> findByPedidoId(int pedidoId);
	
	@Query(value = "SELECT * FROM Detalle WHERE producto_id = ?", nativeQuery = true)
    List<DetalleVO> findByProductoId(int productoId);

}

