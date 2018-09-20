package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnection {
	public Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
//		final Properties prop = new Properties();
//		final InputStream inputStream = OracleConnection.class.getClassLoader()
//				.getResourceAsStream("resources/db.properties");
//		prop.load(inputStream);
//		
//		System.out.printf("driver=%s%nurl=%s%nuser=%s%npassword=%s%n");
//		
//		Class.forName(prop.getProperty("driver"));
//		final Connection connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
//				prop.getProperty("password"));
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:pjd";
		String userame = "c##pjd";
//		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//		String userame = "c##gca";
		String password = "password";
		Connection connection = DriverManager.getConnection(url, userame, password);
		
		connection.setAutoCommit(false);
		
		return connection;
	}
}
