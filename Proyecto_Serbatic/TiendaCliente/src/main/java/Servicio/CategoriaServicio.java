package Servicio;

import java.util.List;

import ModeloDAO.CategoriaDAO;
import ModeloDAO.DetalleDAO;
import ModeloVO.CategoriaVO;
import ModeloVO.DetalleVO;

public class CategoriaServicio {

	public static List<CategoriaVO> ObtenerTodasCategorias() {
		return CategoriaDAO.SelectCategorias();

	}

	public static int Obtenerid(String categoria) {
		return CategoriaDAO.SelectidCategoria(categoria);

	}
	
	public static String Obtenerid(int id) {
		return CategoriaDAO.SelectnombreCategoria(id);

	}
	
	public static CategoriaVO ObtenerCateg(int id) {
		return CategoriaDAO.SelectnombreCategoriaID(id);

	}
	
	public static void insertCategoria(CategoriaVO categoria) {
		 CategoriaDAO.insertarCategoria(categoria);

	}
	
	public static void UpdateCategoriaActualizar(CategoriaVO categoria) {
		 CategoriaDAO.UpdateCategoria(categoria);

	}
	
	

}
