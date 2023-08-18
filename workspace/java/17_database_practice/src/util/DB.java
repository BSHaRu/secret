package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bigdata";
	private static final String USER = "bigdata";
	private static final String PASS = "12345";
	
	private DB() {
	}
	
	private static Connection conn;
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
				System.out.println("Driver 등록 완료");
			} catch (ClassNotFoundException e) {
				System.out.println("Driver 등록 실패");;
			} catch (SQLException e) {
				System.out.println("DB 연결 실패");;
			}
		}// if 종료
		return conn;
	} // getConn 종료
	
	public static void close(AutoCloseable... closer) {
		for(AutoCloseable close : closer) {
			try {
				if(closer != null) close.close();
			} catch (Exception e) {}
		} // for문 종료
	} // close 종료
	
}
