package com.example.tienda.ModeloDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tienda.ModeloVO.CategoriaVO;
import com.example.tienda.ModeloVO.RolVO;

@Repository
public interface RolRepositorio extends JpaRepository<RolVO, Integer> {



}
