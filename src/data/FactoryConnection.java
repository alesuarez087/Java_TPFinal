package data;

import java.sql.*;

public class FactoryConnection {
	
	private String dbDriver="com.mysql.jdbc.Driver";
	private int cantCon;
	private Connection conexion;
	//private static String host="127.10.76.2";
	private String port="3306";
	private static String user="java";
	private static String pass="java";
	private static String db="java_tpfinal";
	private static FactoryConnection instancia;
	
	private FactoryConnection(){
		try {
			Class.forName(dbDriver);
			conexion=null;
			cantCon=0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static FactoryConnection getInstancia(){
		if (instancia==null){
			instancia = new FactoryConnection();
		}
		return instancia;
	}
	
	
	
	public Connection getConnection(){
		try {
			if(conexion==null || conexion.isClosed()){
				conexion = DriverManager.getConnection("jdbc:mysql://"+"localhost"+"/"+db, user, pass);
				cantCon++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexion;
	}
	
	
	public void releaseConnection(){
		try {
			cantCon--;
			if(cantCon==0){
				conexion.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}

