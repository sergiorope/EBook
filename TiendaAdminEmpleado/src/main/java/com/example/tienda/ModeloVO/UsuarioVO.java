package com.example.tienda.ModeloVO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "usuario")

public class UsuarioVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int rol_id;
	private String email;
	private String clave;
	private String nombre;
	private String apellidos;
	private int baja;

	public UsuarioVO(int rol_id, String email, String clave, String nombre, String apellidos, int baja) {

		this.rol_id = rol_id;
		this.email = email;
		this.clave = clave;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.baja = baja;
	}

}
