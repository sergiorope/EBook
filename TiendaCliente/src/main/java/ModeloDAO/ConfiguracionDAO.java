package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ModeloVO.ConfiguracionVO;
import ModeloVO.ProductoVO;

public class ConfiguracionDAO {

	public static String selectConfiguracion(String clave) {
		String valor = null;

		try {
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT valor FROM configuracion WHERE clave=?");
			st.setString(1, clave);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				valor = rs.getString("valor");
			}

			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return valor;
	}

	public static void updateConfiguracion(String valor, String clave) {

		try {
			Connection con = Conexion.getConexion();
			String sql = "UPDATE configuracion SET valor = ? WHERE clave = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, valor);
			st.setString(2, clave);

			st.executeUpdate();

			con.commit();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}
