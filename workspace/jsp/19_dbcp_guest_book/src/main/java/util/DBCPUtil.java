package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCPUtil {
	public static Connection getConnction() {
		Connection conn = null;
		try {
			DataSource ds = (DataSource)new InitialContext().lookup(
				"java:comp/env/jdbc/MySQLDBCP"	
			);
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println("DBCP 이름의 파일이 존재하지 않음");
		} catch (SQLException e) {
			System.out.println("연결 정보 없음");
		}
		return conn;
	}
	// JDBC 자원 해제
	public static void close(AutoCloseable... closer) { 
		for(int i=0; i<closer.length; i++) {
			if(closer[i] != null) {
				try {
					closer[i].close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
