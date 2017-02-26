package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.ApplicationException;

public class FactoryConnection {
	private Connection conn;
	private int cantConn=0;
	
	
	
	private FactoryConnection() throws ApplicationException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new ApplicationException("Error del Driver JDBC", e);
		}
	}
	
	
	
	private static FactoryConnection instancia;
	
	public static FactoryConnection getInstancia() throws ApplicationException {
		if (instancia==null){
			instancia = new FactoryConnection();
		}
		return instancia;
	}
	
	public Connection getConn() throws ApplicationException {
		try {
			if(conn==null || conn.isClosed()) {
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=tpReg_java", "java", "java");
//						"jdbc:"+dbType+"://"+host+":"+port+";databaseName="+db, user, pass);
				cantConn++;
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al conectar a la DB", e);
		}
		return conn;
	}
	
	public void releaseConn() throws ApplicationException {
		try {
			cantConn--;
			if(cantConn==0) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al cerrar conexión",e);
		}
	}
}

