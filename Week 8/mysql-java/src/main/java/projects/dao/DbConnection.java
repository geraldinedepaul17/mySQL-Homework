/**
 * 
 */
package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projects.exception.DbException;

/**
 * @author gerriciancanelli
 *
 */
public class DbConnection {

	private static String HOST = "localhost"; 
	private static String PASSWORD = "projects"; 
	private static int PORT = 3306; 
	private static String SCHEMA = "projects"; 
	private static String USER = "projects"; 
	
	
	public static Connection getConnection() {
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, SCHEMA, USER, PASSWORD);
		
	System.out.println("Connecting with url=" + url);
		
		try {
		Connection conn = DriverManager.getConnection(url); 
		System.out.println("Sucessfully obtained connection!"); 
		return conn; 
		
		} catch (SQLException e) {
			System.out.println("Error"); 
			throw new DbException(e); 
		}
	}
}
