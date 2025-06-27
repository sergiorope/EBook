package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ModeloVO.DetalleVO;
import ModeloVO.OpcionMenuVO;
import ModeloVO.ProductoVO;

public class OpcionMenuDAO {
	
	public static List<OpcionMenuVO> SelectOpcionesMenu(int rol_id) {
	    List<OpcionMenuVO> listaopciones = new ArrayList<OpcionMenuVO>();
	    OpcionMenuVO opcion = null;

	    try {
	        Connection con = Conexion.getConexion();
	        PreparedStatement st = con.prepareStatement("Select * from opcionmenu WHERE rol_id = ?");
	        
	      
	        st.setInt(1, rol_id);

	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            opcion = new OpcionMenuVO();

	            opcion.setId(rs.getInt("id"));
	            opcion.setRol_id(rs.getInt("rol_id"));
	            opcion.setNombre(rs.getString("nombre"));
	            opcion.setUrl(rs.getString("url"));

	            listaopciones.add(opcion);
	        }

	 
	
	        

	        return listaopciones;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listaopciones;
	}


}
