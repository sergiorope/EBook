package com.example.tienda.ModeloDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tienda.ModeloVO.ConfiguracionVO;
import com.example.tienda.ModeloVO.PedidoVO;
import com.example.tienda.ModeloVO.UsuarioVO;

@Repository
public interface ConfiguracionRepositorio extends JpaRepository<ConfiguracionVO, Integer> {

	@Query(value = "SELECT valor FROM Configuracion WHERE clave = ?", nativeQuery = true)
    String findByClave(String clave);
	
	@Modifying
	@Query(value = "UPDATE Configuracion SET valor=? WHERE clave=?", nativeQuery = true)
	void updateByClave(String valor, String clave);

}
