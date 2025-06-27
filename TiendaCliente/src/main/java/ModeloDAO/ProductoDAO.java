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

public class ProductoDAO {

	public static List<ProductoVO> findAll() {

		List<ProductoVO> lista = new ArrayList<ProductoVO>();
		ProductoVO producto = null;

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("Select * from producto");

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				producto = new ProductoVO();

				producto.setId(rs.getInt("id"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setStock(rs.getInt("stock"));
				producto.setBaja(rs.getInt("baja"));
				producto.setImagen(rs.getString("imagen"));

				lista.add(producto);
			}

			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;

	}

	public static List<ProductoVO> MostrarProductosPorCategoria(int categoria) {

		List<ProductoVO> lista = new ArrayList<ProductoVO>();
		ProductoVO producto = null;

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM producto WHERE categoria_id=?");

			st.setInt(1, categoria);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				producto = new ProductoVO();

				producto.setId(rs.getInt("id"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setStock(rs.getInt("stock"));
				producto.setBaja(rs.getInt("baja"));
				producto.setImagen(rs.getString("imagen"));

				lista.add(producto);
			}

			return lista;
		} catch (SQLException e) {
			// Manejo de excepciones
			e.printStackTrace();
		} finally {
			// Cerrar recursos (Connection, PreparedStatement, ResultSet) en un bloque
			// finally si es necesario
		}
		return lista;
	}

	public static void ActualizarProducto(ProductoVO producto) {
		String sql = "UPDATE producto SET stock = ? WHERE id = ?";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, producto.getStock());
			stmt.setInt(2, producto.getId());

			stmt.executeUpdate();

			con.commit();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void ActualizarProductoEntero(ProductoVO producto) {
		String sql = "UPDATE producto SET categoria_id = ?, nombre=?, descripcion=?, precio=?, impuesto=?, stock=?, baja=?, imagen=? WHERE id = ?";
		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, producto.getCategoria_id());
			stmt.setString(2, producto.getNombre());
			stmt.setString(3, producto.getDescripcion());
			stmt.setDouble(4, producto.getPrecio());
			stmt.setDouble(5, producto.getImpuesto());
			stmt.setInt(6, producto.getStock());
			stmt.setInt(7, producto.getBaja());
			stmt.setString(8, producto.getImagen());
			stmt.setInt(9, producto.getId());

			stmt.executeUpdate();

			con.commit();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ProductoVO getNombresProductos(DetalleVO detalle) {

		ProductoVO producto = new ProductoVO();

		String sql = "SELECT id, nombre, imagen FROM producto WHERE id=?";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, detalle.getProducto_id());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setImagen(rs.getString("imagen"));

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}

	public static ProductoVO getProductoId(int id) {

		ProductoVO producto = new ProductoVO();

		String sql = "SELECT * FROM producto WHERE id=?";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				producto.setId(rs.getInt("id"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				producto.setProveedor_id(rs.getInt("proveedor_id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setStock(rs.getInt("stock"));
				producto.setBaja(rs.getInt("baja"));
				producto.setImagen(rs.getString("imagen"));

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}
	
	public static ProductoVO getProductoRandomRecomendacion(int categoria_id) {

		ProductoVO producto = new ProductoVO();

		String sql = "SELECT * FROM producto WHERE categoria_id=? ORDER BY RAND() LIMIT 1;";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, categoria_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				producto.setId(rs.getInt("id"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				producto.setProveedor_id(rs.getInt("proveedor_id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setStock(rs.getInt("stock"));
				producto.setBaja(rs.getInt("baja"));
				producto.setImagen(rs.getString("imagen"));

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}

	public static ProductoVO MostrarProducto(int id) {
		ProductoVO producto = new ProductoVO();

		String sql = "SELECT * FROM producto WHERE id=?";
		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					producto.setId(rs.getInt("id"));
					producto.setCategoria_id(rs.getInt("categoria_id"));
					producto.setNombre(rs.getString("nombre"));
					producto.setDescripcion(rs.getString("descripcion"));
					producto.setPrecio(rs.getDouble("precio"));
					producto.setImpuesto(rs.getDouble("impuesto"));
					producto.setStock(rs.getInt("stock"));
					producto.setBaja(rs.getInt("baja"));
					producto.setImagen(rs.getString("imagen"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}

	public static void insertarProducto(ProductoVO producto) {
		String sql = "INSERT INTO producto (categoria_id, nombre, descripcion, precio, impuesto, stock, baja, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, producto.getCategoria_id());
			stmt.setString(2, producto.getNombre());
			stmt.setString(3, producto.getDescripcion());
			stmt.setDouble(4, producto.getPrecio());
			stmt.setDouble(5, producto.getImpuesto());
			stmt.setInt(6, producto.getStock());
			stmt.setInt(7, producto.getBaja());
			stmt.setString(8, producto.getImagen());

			stmt.executeUpdate();

			con.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
