package com.example.tienda.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tienda.ModeloDAO.CategoriaRepositorio;
import com.example.tienda.ModeloVO.CategoriaVO;

import java.util.List;

@Service
public class CategoriaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public List<CategoriaVO> obtenerCategorias() {
        return categoriaRepositorio.findAll();
    }
 

    public void insertarCategoria(CategoriaVO categoria) {
        categoriaRepositorio.save(categoria);
    }
    
    public void actualizarCategoria(CategoriaVO categoria) {
        categoriaRepositorio.save(categoria);
    }
    
    public CategoriaVO obtenerCategoriaId(int id) {
        return categoriaRepositorio.findById(id).get();
    }
}
