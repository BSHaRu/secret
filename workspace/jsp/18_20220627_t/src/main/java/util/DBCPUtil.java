package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCPUtil extends JDBCUtil {
	// memberList.jsp 참고
	// DBCP - DataBase Connection Pool
	private static DataSource ds;
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/java/MySqlDB");
			conn = ds.getConnection();
		} catch (NamingException e) {
			// java:comp/env까진 정해져있어서 상관없고, java/MySqlDB여기에 대한 이름을 못찾는다는거임
			System.out.println("지정된 이름으로 DBCP를 찾을 수 없음");
		} catch(SQLException e) {
			System.out.println("DB 연결 정보 오류");
		}
		
		return conn;
	}
}
