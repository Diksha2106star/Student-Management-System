package JdbcPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DBConnection {

	private static final String CONFIG_FILE = "db.properties";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Properties properties  = new Properties();
			InputStream file = DBConnection.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
			if(file == null) {
				System.out.println("db.properties file not found!");
				return null;
			}
			properties.load(file);
			file.close();
			String url = properties.getProperty("url");
			String user = properties.getProperty("username");
			String password= properties.getProperty("password");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Database not connected!");
			e.printStackTrace();
		}
		return conn;
	}

}
