/**
 * 
 */
package sn.boom.sgi.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * @author nabyFall
 *
 */
public class DBProviderMDS {
	
	public static Connection connection;
	private static MysqlDataSource singleDataSource;
	
	public static Connection getconnection() throws Exception {
		
		
		try {
			
			if (singleDataSource == null) {
				// charge the driver class
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				singleDataSource = new MysqlDataSource();
				
				// open connection 
				//String url = "jdbc:mysql://localhost:3306/tennis?useSSl=false&serverTimezone=UTC";
				//singleDataSource.setUrl(url);
				
				singleDataSource.setServerName("localhost");
				singleDataSource.setPort(3306);
				singleDataSource.setDatabaseName("tennis");
				singleDataSource.setUseSSL(false);
				singleDataSource.setServerTimezone("UTC");
				
				singleDataSource.setUser("root");
				singleDataSource.setPassword("");
				
				connection = singleDataSource.getConnection();
			}
			return connection;
		} 
		catch (ClassNotFoundException e) {
			throw new Exception("Class not found : '"+ e.getMessage() +"' !");
		}
		catch(SQLException e) {
			throw new Exception("Error : unable to open connection with database !");
		}
	}

}
