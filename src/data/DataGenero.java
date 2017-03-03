package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Genero;
import entidades.Entidad.States;
import utils.ApplicationException;

public class DataGenero {

	public static ArrayList<Genero> GetAll(){
		ArrayList<Genero> list = new ArrayList<Genero>();
		Genero gen = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call GenerosGetAll };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				gen = new Genero();
				gen.setId(rs.getInt("id_genero"));
				gen.setDescripcion(rs.getString("desc_genero"));
				gen.setHabilitado(rs.getBoolean("habilitado"));
				
				list.add(gen);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return list;
	}
	
	public static Genero GetOne(String desc){
		Genero gen = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call GenerosGetOne(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, desc);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				gen = new Genero();
				gen.setId(rs.getInt("id_genero"));
				gen.setDescripcion(rs.getString("desc_genero"));
				gen.setHabilitado(rs.getBoolean("habilitado"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
		return gen;
	}
	
	
	public static void Save(Genero gen){
		if(gen.getState()==States.Alta) Insert(gen);
		else if(gen.getState()==States.Baja) Delete(gen);
		else if(gen.getState()==States.Modificacion) Update(gen);
		else gen.setState(States.Consulta);
	}

	private static void Insert(Genero gen){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call GenerosInsert(?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, gen.getDescripcion());
			stmt.setBoolean(2, gen.isHabilitado());
			rs = stmt.executeQuery();
			
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}

	private static void Update(Genero gen){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call GenerosUpdate(?, ?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, gen.getDescripcion());
			stmt.setBoolean(2, gen.isHabilitado());
			stmt.setInt(3, gen.getId());
			rs = stmt.executeQuery();
			
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}
	
	private static void Delete(Genero gen){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call UsuariosDelete(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, gen.getId());
			rs = stmt.executeQuery();
			
		} catch(SQLException e){
			e.printStackTrace();
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		finally{
			try {
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			if(stmt != null) stmt = null;
			if(rs!=null) rs = null;
		}
	}

}
