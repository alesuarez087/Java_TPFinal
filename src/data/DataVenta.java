package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Venta;
import utils.ApplicationException;

public class DataVenta {

	public static ArrayList<Venta> GetAll(){
		ArrayList<Venta> list = new ArrayList<Venta>();
		Venta venta = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentasGetAll };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				venta = new Venta();
				venta.setId(rs.getInt("id_venta"));
				venta.setHabilitado(rs.getBoolean("habilitado"));
				venta.setIdUsuario(rs.getInt("id_usuario"));
				venta.setMontoTotal(rs.getDouble("monto"));
				venta.setNroTarjeta(rs.getInt("nro_tarjeta"));
				venta.setTitularTarjeta(rs.getString("titular_tarjeta"));
				venta.setFecha(rs.getDate("fecha"));
				
				list.add(venta);
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
	
	public static void Insert(Venta venta){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentasInsert(?, ?, ?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, venta.getIdUsuario());
			stmt.setDouble(2, venta.getMontoTotal());
			stmt.setInt(3, venta.getNroTarjeta());
			stmt.setString(4, venta.getTitularTarjeta());
			
//			venta.setFecha(rs.getDate("fecha"));
			
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
	
	public static int ultimaVenta(){
		ResultSet rs = null; PreparedStatement stmt = null; int ret = 0;
		String sql="SELECT MAX(id_venta) FROM ventas;";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null) ret = rs.getInt(1);
			
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
		return ret;
	}
}
