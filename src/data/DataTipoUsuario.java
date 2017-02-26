package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.ApplicationException;

import entidades.TipoUsuario;

public class DataTipoUsuario {

	public static ArrayList<TipoUsuario> GetAll(){
		ArrayList<TipoUsuario> retorno = new ArrayList<TipoUsuario>();
		TipoUsuario fila = null; ResultSet rs=null ; PreparedStatement stmt=null;
		String sql = "{ call TipoUsuarioGetAll }";
		try{
			Connection conn = FactoryConnection.getInstancia().getConn();
			stmt = conn.prepareCall(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				fila = new TipoUsuario();
				fila.setId(rs.getInt("id_tipo_usuario"));
				fila.setDescripcion(rs.getString("descripcion_tipo_usuario"));
				fila.setHabilitado(rs.getBoolean("habilitado"));
				retorno.add(fila);
			}
		} catch(ApplicationException e){
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		} finally{
			try {
				FactoryConnection.getInstancia().releaseConn();
				if(stmt != null) stmt = null; if(rs!=null) rs = null;
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
}
