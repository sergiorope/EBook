package com.example.tienda.ModeloDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.tienda.ModeloVO.*;
import java.util.List;

@Repository
	public interface CategoriaRepositorio extends JpaRepository<CategoriaVO, Integer> {
	
	

	}




