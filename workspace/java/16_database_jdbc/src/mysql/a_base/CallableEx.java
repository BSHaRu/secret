package mysql.a_base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallableEx {

	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/bigdata";
		String user = "bigdata";
		String pass = "12345";
		
		Connection conn = null;
		CallableStatement cstmt = null; // CallableStatement extends PreparedStatement
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println(conn);
			
			// call checkYeaar('userTbl 이름');
			String sql = "call checkYear(?)";
			cstmt = conn.prepareCall(sql); // conn.prepareStatement(sql); 이것도 사용가능
			cstmt.setString(1, "은지원");
			
			rs = cstmt.executeQuery();
			if(rs.next()) {
				String result = rs.getString(1);
				System.out.println(result);
			}
			rs.close();
			cstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
