package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ModeloVO.CategoriaVO;
import ModeloVO.ProductoVO;
import ModeloVO.UsuarioVO;

public class CategoriaDAO {

	public static List<CategoriaVO> SelectCategorias() {
		List<CategoriaVO> lista = new ArrayList<>();

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM categoria");

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				CategoriaVO categoria = new CategoriaVO();

				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));

				lista.add(categoria);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public static int SelectidCategoria(String nombre) {
		int idcategoria = -1;

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT id FROM categoria WHERE nombre = ?");
			st.setString(1, nombre);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				idcategoria = rs.getInt("id");
			}

			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idcategoria;
	}

	public static String SelectnombreCategoria(int id) {
		String nombrecateg = "";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT nombre FROM categoria WHERE id = ?");
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				nombrecateg = rs.getString("nombre");
			}

			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombrecateg;
	}
	
	public static CategoriaVO SelectnombreCategoriaID(int id) {
	    CategoriaVO categ = null;

	    try {
	        Connection con = Conexion.getConexion();
	        PreparedStatement st = con.prepareStatement("SELECT id, nombre FROM categoria WHERE id = ?");
	        st.setInt(1, id);

	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            categ = new CategoriaVO();
	            categ.setId(rs.getInt("id"));
	            categ.setNombre(rs.getString("nombre"));
	        }

	        rs.close();
	        st.close();
	  

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return categ;
	}

	public static void insertarCategoria(CategoriaVO categoria) {
		String sql = "INSERT INTO categoria (nombre) VALUES (?)";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, categoria.getNombre());

			stmt.executeUpdate();

			con.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void UpdateCategoria(CategoriaVO categoria) {

		String sql = "UPDATE categoria SET nombre = ? WHERE id = ?";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, categoria.getNombre());
			stmt.setInt(2, categoria.getId());
		

			stmt.executeUpdate();

			con.commit();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
