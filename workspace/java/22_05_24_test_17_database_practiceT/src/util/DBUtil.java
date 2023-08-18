package util;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

	private DBUtil() {
	}
	
	private static Connection conn;
	
	public static Connection getConnection() {
		// conn 객체가 참조하는 값이 없으면 생성
		if(conn == null) {
			try {
				Properties prop = new Properties();
				File file = new File("src/prop/mysql.properties");
				String path = file.getPath();
				System.out.println(path);
				prop.load(new FileReader(file));
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
//				String user = prop.getProperty("user");
//				String password = prop.getProperty("password");
				Class.forName(driver);
//				conn = DriverManager.getConnection(url, user, password);
				conn = DriverManager.getConnection(url, prop);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return conn;
	}
	
	public static void close(AutoCloseable... closers) {
		for(AutoCloseable closer : closers) {
			try {
				if(closer != null) closer.close();
			} catch (Exception e) {	}
		}
	}
	
}
