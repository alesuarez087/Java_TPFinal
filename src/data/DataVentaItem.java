package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.VentaItem;
import utils.ApplicationException;

public class DataVentaItem {

	public static ArrayList<VentaItem> GetAll(int IdUsuario){
		ArrayList<VentaItem> list = new ArrayList<VentaItem>();
		VentaItem vi = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentaItemGetAll(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, IdUsuario);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				vi = new VentaItem();
				vi.setCantidad(rs.getInt("cantidad"));
				vi.setId(rs.getInt("id_usuario"));
				vi.setIdItem(rs.getInt("id_item"));
				vi.setIdVenta(rs.getInt("id_venta"));
				
				list.add(vi);
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
	
	public static VentaItem GetOne(int idVentaItem){
		VentaItem vi = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentaItemGetOne(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idVentaItem);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				vi = new VentaItem();
				vi.setCantidad(rs.getInt("cantidad"));
				vi.setId(rs.getInt("id_usuario"));
				vi.setIdItem(rs.getInt("id_item"));
				vi.setIdVenta(rs.getInt("id_venta"));
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
		return vi;
	}
	
	
	public static void Insert(VentaItem ventaItem){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentaItemInsert(?, ?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ventaItem.getCantidad());
			stmt.setInt(2, ventaItem.getIdItem());
			stmt.setInt(3, ventaItem.getIdVenta());
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
