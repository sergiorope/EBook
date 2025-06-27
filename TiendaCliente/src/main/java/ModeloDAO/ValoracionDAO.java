package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ModeloVO.DetalleVO;
import ModeloVO.ProductoVO;
import ModeloVO.UsuarioVO;
import ModeloVO.ValoracionVO;
import Util.HibernateUtil;

public class ValoracionDAO {

	public static List<ValoracionVO> getValoracion(int producto_id) {
		
	    List<ValoracionVO> valoraciones = new ArrayList<>();

		

		String sql = "SELECT * FROM valoracion  WHERE producto_id=?";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, producto_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ValoracionVO valoracion = new ValoracionVO();

				valoracion.setId(rs.getInt("id"));
				valoracion.setProducto_id(rs.getInt("producto_id"));
				valoracion.setUsuario_id(rs.getInt("usuario_id"));
				valoracion.setValoracion(rs.getInt("valoracion"));
				valoracion.setComentario(rs.getString("comentario"));
				
				valoraciones.add(valoracion);

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valoraciones;
	}
	
public static List<ValoracionVO> getValoracionComprobar(int producto_id, int usuario_id) {
		
	    List<ValoracionVO> valoraciones = new ArrayList<>();

		

		String sql = "SELECT * FROM valoracion  WHERE producto_id=? AND usuario_id=?";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, producto_id);
			stmt.setInt(2, usuario_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ValoracionVO valoracion = new ValoracionVO();

				valoracion.setId(rs.getInt("id"));
				valoracion.setProducto_id(rs.getInt("producto_id"));
				valoracion.setUsuario_id(rs.getInt("usuario_id"));
				valoracion.setValoracion(rs.getInt("valoracion"));
				valoracion.setComentario(rs.getString("comentario"));
				
				valoraciones.add(valoracion);

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valoraciones;
	}
	
	public static void insertarValoracion(ValoracionVO valoracion) {
		String sql = "INSERT INTO valoracion (producto_id, usuario_id, valoracion, comentario) VALUES (?, ?, ?, ?)";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, valoracion.getProducto_id());
			stmt.setInt(2, valoracion.getUsuario_id());
			stmt.setInt(3, valoracion.getValoracion());
			stmt.setString(4, valoracion.getComentario());

			stmt.executeUpdate();

			con.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	


}
