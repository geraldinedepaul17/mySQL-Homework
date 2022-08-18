/**
 * 
 */
package recipes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import recipes.exception.DbException;

/**
 * @author gerriciancanelli
 *
 */
public class DbConnection {
	private static final String SCHEMA = "recipes";
	private static final String USER = "recipes";
	private static final String PASSWORD = "recipes";
	private static final String HOST = "localhost";
	private static final int PORT = 3306;

	public static Connection getConnection() {
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", HOST, PORT, SCHEMA, USER);

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
