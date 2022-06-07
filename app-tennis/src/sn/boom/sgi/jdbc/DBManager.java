package sn.boom.sgi.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBManager {
	
	private static Connection connection = null;
	private static MysqlDataSource dataSource = new MysqlDataSource();
	
	public static Connection getConnection() throws Exception {
		
		try {
			// chargement du Driver MySql
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// ouvrir la connexion
			dataSource.setServerName("localhost");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("tennis");
			dataSource.setUseSSL(false);
			dataSource.setServerTimezone("UTC");
			dataSource.setUser("root");
			dataSource.setPassword("");
			
			connection = dataSource.getConnection();
			return connection;
		}
		catch(ClassNotFoundException e) {
			throw new Exception("Driver class not found : '"+ e.getMessage() +"' ");
		}
		catch (SQLException e) {
			throw new Exception("Error : Unable to open connection wwwwwith database");
		}
	}

}
