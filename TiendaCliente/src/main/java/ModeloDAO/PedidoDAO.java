package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ModeloVO.DetalleVO;
import ModeloVO.PedidoVO;
import ModeloVO.ProductoVO;
import ModeloVO.UsuarioVO;

public class PedidoDAO {

	public static List<PedidoVO> selectPedidosTodos() {
	    List<PedidoVO> lista = new ArrayList<>();

	    String sql = "SELECT * FROM pedido";

	    try {
	        Connection con = Conexion.getConexion();
	        PreparedStatement stmt = con.prepareStatement(sql);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            PedidoVO pedidoEncontrado = new PedidoVO();

	            pedidoEncontrado.setId(rs.getInt("id"));
	            pedidoEncontrado.setUsuario_id(rs.getInt("usuario_id"));
	            pedidoEncontrado.setFecha(rs.getString("fecha"));
	            pedidoEncontrado.setMetodopago(rs.getString("metodopago"));
	            pedidoEncontrado.setNumfactura(rs.getString("numfactura"));
	            pedidoEncontrado.setEstado(rs.getString("estado"));
	            pedidoEncontrado.setTotal(rs.getDouble("total"));

	            lista.add(pedidoEncontrado);
	        }

	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}


	public static PedidoVO selectPedidos(int id) {

		PedidoVO pedidoEncontrado = new PedidoVO();

		String sql = "SELECT id, usuario_id, fecha, metodopago, numfactura, estado, total FROM pedido WHERE id=?";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				pedidoEncontrado.setId(rs.getInt("id"));
				pedidoEncontrado.setUsuario_id(rs.getInt("usuario_id"));
				pedidoEncontrado.setFecha(rs.getString("fecha"));
				pedidoEncontrado.setMetodopago(rs.getString("metodopago"));
				pedidoEncontrado.setNumfactura(rs.getString("numfactura"));
				pedidoEncontrado.setEstado(rs.getString("estado"));
				pedidoEncontrado.setTotal(rs.getInt("total"));

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedidoEncontrado;
	}

	public static List<PedidoVO> getPedidos(PedidoVO pedido) {
		List<PedidoVO> pedidos = new ArrayList<>();
		String sql = "SELECT id, usuario_id, fecha, metodopago, numfactura, estado, total FROM pedido WHERE usuario_id=? ORDER BY fecha DESC";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, pedido.getUsuario_id());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PedidoVO pedidoEncontrado = new PedidoVO();
				pedidoEncontrado.setId(rs.getInt("id"));
				pedidoEncontrado.setUsuario_id(rs.getInt("usuario_id"));
				pedidoEncontrado.setFecha(rs.getString("fecha"));
				pedidoEncontrado.setMetodopago(rs.getString("metodopago"));
				pedidoEncontrado.setNumfactura(rs.getString("numfactura"));
				pedidoEncontrado.setEstado(rs.getString("estado"));
				pedidoEncontrado.setTotal(rs.getDouble("total"));
				pedidos.add(pedidoEncontrado);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedidos;
	}

	public static int insertarPedido(PedidoVO pedido) {
		String sql = "INSERT INTO pedido (usuario_id, fecha, metodopago, numfactura, estado, total) VALUES (?, ?, ?, ?, ?, ?)";

		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaActual = formato.format(fecha);
		int idDevuelto = -1;

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, pedido.getUsuario_id());
			stmt.setString(2, fechaActual);
			stmt.setString(3, pedido.getMetodopago());
			stmt.setString(4, pedido.getNumfactura());
			// Ensure 'estado' is set
			stmt.setString(5, pedido.getEstado());
			stmt.setDouble(6, pedido.getTotal());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				idDevuelto = rs.getInt(1);
				System.out.println("id: " + idDevuelto);
			}
			rs.close();

			con.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idDevuelto;
	}

	public static void ActualizarPedido(String estado, int id) {
		String sql = "UPDATE pedido SET estado = ? WHERE id = ?";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, estado);
			stmt.setInt(2, id);

			stmt.executeUpdate();

			con.commit();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void ActualizarPedido(PedidoVO pedido) {
	    String sql = "UPDATE pedido SET usuario_id = ?, fecha = ?, metodopago = ?, numfactura = ?, estado = ?, total = ? WHERE id = ?";

	    try {
	        Connection con = Conexion.getConexion();
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setInt(1, pedido.getUsuario_id());
	        stmt.setString(2, pedido.getFecha());
	        stmt.setString(3, pedido.getMetodopago());
	        stmt.setString(4, pedido.getNumfactura());
	        stmt.setString(5, pedido.getEstado());
	        stmt.setDouble(6, pedido.getTotal());
	        stmt.setInt(7, pedido.getId());

	        stmt.executeUpdate();

	        con.commit();
	        stmt.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public static void ActualizarPedidoEnvio(String numfactura, String estado, int id) {
		String sql = "UPDATE pedido SET numfactura = ?, estado = ? WHERE id = ?";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, numfactura);
			stmt.setString(2, estado);
			stmt.setInt(3, id);

			stmt.executeUpdate();

			con.commit();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
