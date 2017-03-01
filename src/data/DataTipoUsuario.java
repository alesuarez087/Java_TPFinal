package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.TipoUsuario;

public class DataTipoUsuario {

	public static ArrayList<TipoUsuario> GetAll(){
		ArrayList<TipoUsuario> retorno = new ArrayList<TipoUsuario>();
		TipoUsuario fila = null; ResultSet rs=null ; 
		String sql = "SELECT * FROM tipos_usuario;";
		Connection conn = FactoryConnection.getInstancia().getConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs != null){
				fila = new TipoUsuario();
				fila.setId(rs.getInt("id_tipo_usuario"));
				fila.setDescripcion(rs.getString("desc_tipo_usuario"));
				fila.setHabilitado(rs.getBoolean("habilitado"));
				retorno.add(fila);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally{
			FactoryConnection.getInstancia().releaseConnection();
			//if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return retorno;
	}
}
