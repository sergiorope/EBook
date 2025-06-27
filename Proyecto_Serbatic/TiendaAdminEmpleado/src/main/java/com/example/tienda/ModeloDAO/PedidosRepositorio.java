package com.example.tienda.ModeloDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tienda.ModeloVO.PedidoVO;
import com.example.tienda.ModeloVO.ProductoVO;

@Repository
public interface PedidosRepositorio extends JpaRepository<PedidoVO, Integer> {

}

