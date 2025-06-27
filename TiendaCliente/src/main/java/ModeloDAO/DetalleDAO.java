package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;

public class DetalleDAO {

	public static void insertarLineasDetalle(DetalleVO detalle) {

		String sql = "INSERT INTO detalle (pedido_id, producto_id, unidades, preciounidad, impuesto, total) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, detalle.getPedido_id());
			stmt.setInt(2, detalle.getProducto_id());
			stmt.setInt(3, detalle.getUnidades());
			stmt.setDouble(4, detalle.getPreciounidad());
			stmt.setDouble(5, detalle.getImpuesto());
			stmt.setDouble(6, detalle.getTotal());

			stmt.executeUpdate();

			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<DetalleVO> getDetalles(DetalleVO detalle) {
	    List<DetalleVO> detalles = new ArrayList<>();

	    String sql = "SELECT id, pedido_id, producto_id, unidades, preciounidad, impuesto, total FROM detalle  WHERE pedido_id=?";

	    try {
	        Connection con = Conexion.getConexion();
	        PreparedStatement stmt = con.prepareStatement(sql);

	        stmt.setInt(1, detalle.getPedido_id());

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            DetalleVO detalleEncontrado = new DetalleVO();

	            detalleEncontrado.setId(rs.getInt("id"));
	            detalleEncontrado.setPedido_id(rs.getInt("pedido_id"));
	            detalleEncontrado.setProducto_id(rs.getInt("producto_id"));
	            detalleEncontrado.setUnidades(rs.getInt("unidades"));
	            detalleEncontrado.setPreciounidad(rs.getDouble("preciounidad"));
	            detalleEncontrado.setImpuesto(rs.getInt("impuesto"));
	            detalleEncontrado.setTotal(rs.getDouble("total"));

	            
	            detalles.add(detalleEncontrado);
	        }

	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return detalles;
	}
	
	public static DetalleVO selectDetalle(int id) {
	    
		 DetalleVO detalleEncontrado = new DetalleVO();
	    String sql = "SELECT id, producto_id, pedido_id, unidades, preciounidad, impuesto, total FROM detalle  WHERE id=?";

	    try {
	        Connection con = Conexion.getConexion();
	        PreparedStatement stmt = con.prepareStatement(sql);

	        stmt.setInt(1, id);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	           

	            detalleEncontrado.setId(rs.getInt("id"));
	            detalleEncontrado.setProducto_id(rs.getInt("producto_id"));
	            detalleEncontrado.setPedido_id(rs.getInt("pedido_id"));
	            detalleEncontrado.setUnidades(rs.getInt("unidades"));
	            detalleEncontrado.setPreciounidad(rs.getDouble("preciounidad"));
	            detalleEncontrado.setImpuesto(rs.getInt("impuesto"));
	            detalleEncontrado.setTotal(rs.getDouble("total"));

	            
	          
	        }

	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return detalleEncontrado;
	}
	
	public static void deleteDetalle(int id) {
	    String sql = "DELETE FROM detalle WHERE id=?";
	   try { 
	    Connection con = Conexion.getConexion();
		PreparedStatement stmt = con.prepareStatement(sql);
	        
	        stmt.setInt(1, id);
	        stmt.executeUpdate();
	        
	        con.commit();
			stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
