package com.example.tienda.ModeloDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tienda.ModeloVO.ProveedorVO;

@Repository
public interface ProveedorRepositorio extends JpaRepository<ProveedorVO, Integer> {



}
