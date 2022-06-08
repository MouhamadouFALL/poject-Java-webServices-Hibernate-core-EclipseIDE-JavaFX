package sn.boom.sgi.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBManagerBDS {
	
	private static Connection connection = null;
	private static BasicDataSource singleDataSource = null;
	
	public static Connection getConnection() throws Exception {
		
		try {
			if (connection == null) {
				// charge Driver class Mysql
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				singleDataSource = new BasicDataSource();
				
				// open connection
				String url = "jdbc:mysql://localhost:3306/tennis?useSSL=false&serverTimezone=UTC";
				singleDataSource.setInitialSize(5); // initialiser la poule de connexion
				singleDataSource.setUrl(url);
				singleDataSource.setUsername("root");
				singleDataSource.setPassword("");
				
				connection = singleDataSource.getConnection();
			}
			return connection;
		}
		catch(ClassNotFoundException e) {
			throw new Exception("Driver class not found : '"+ e.getMessage() +"' ");
		}
		catch (SQLException e) {
			throw new Exception("Error : Unable to open connection with database");
		}
	}

}
