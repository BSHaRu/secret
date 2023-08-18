package mysql.a_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class StatementEx {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/bigdata";
		String fullUrl = "jdbc:mysql://localhost:3306/bigdata?user=bigdata&password=12345";
		String user = "bigdata";
		String password = "12345";
		
		Properties prop = new Properties();
		prop.setProperty("user", user);
		prop.setProperty("password", password);
		
		// DB와 연결된 계정 정보를 가진 객체
		Connection conn = null;
		// 연결정보를 가지고 질의 전송을 도와주는 객체
		Statement stmt = null;
		// ResultSet : 검색 질의에 대한 결과를 저장하는 객체
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			System.out.println("Dirve Class load 완료");
			
//			conn = DriverManager.getConnection(url, user, password); // 이것도 가능하지만 아래것들도 가능함
//			conn = DriverManager.getConnection(fullUrl);
			conn = DriverManager.getConnection(url,prop);
			System.out.println(conn+" 정상적으로 연결되었습니다.");
			
			stmt = conn.createStatement();
			String sql = "SELECT * FROM emp";
			// 매개변수로 넘겨 받은 질의문 실행
			rs = stmt.executeQuery(sql);
			// rs.next() : 검색된 질의결과에 행단위로 이동
			// 더 이상 이동할 행이 없으면 false | 정상적으로 행을 이동했으면 true
			while(rs.next()) {
				// rs객체 안에서 emp table에서 검색된 1개의 행의 정보
				int empno = rs.getInt(1);			// 해당 table 순서를 알면 이렇게 숫자를 넣을 수 있고
				String ename = rs.getString("ename"); // 순서를 모를 경우엔 해당 속성이름을 넣어서 검색도 가능
				String job = rs.getString(3);
				System.out.println("emp : "+empno+", "+ename+", "+job);
			} // while문 종료
			
			rs.close();
			stmt.close();
			
			sql = "INSERT INTO member_tbl(id,pw,name) "
					+" VALUES('id010','pw010','이승기')";
			stmt = conn.createStatement();	// 위에서 stmt 있기때문에 해당 자원 close부터 시켜라는 의미로 노란줄 뜸
			int result = stmt.executeUpdate(sql);
			System.out.println(result + "개의 행이 삽입 되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver class를 찾을 수 없습니다." + e.getMessage()); // Class.forName(driver)의 try catch
		} catch (SQLException e) {
			System.out.println("DB 연결 정보가 잘못되었습니다." + e.getMessage()); // conn의 try catch
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {	}
		} // try catch 종료
		
		System.out.println("Main 종료");
	}
}
