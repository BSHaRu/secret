package mysql.a_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseEX {

	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/bigdata"; // jdbc:mysql://접근권한(ip 등):포트번호(3306)/해당 사용자 정보
			String username = "bigdata";						// localhost || 127.0.0.1 == 내 컴퓨터
			String password = "12345";							// 포트번호는 모든 프로그램마다 정해져있음 cf)포트번호는 사용자가 처음 설정할때 변경 가능
			Class.forName(driver);			// 드라이버가 "정상적으로" 들어왔는지 확인하는 친구라서 이건 꼭 필요한건 아님.
			System.out.println("Driver Class 완료"); // -> 정상적으로 들어가 있다면 없어도 실행이 됨.
													// => but, 드라이버 확인하는 용도로 Class.forName(driver);는 나두는게 좋음
			conn = DriverManager.getConnection(url, username, password);
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class를 찾을 수 없음");
		} catch (SQLException e) {
			System.out.println("DB 연결 정보가 잘못되었습니다.");
		} finally {
			try {
				conn.close();	// 외부를 통해서 연결한 자원들은 모두 close를 해줘야된다.
			} catch (SQLException e) {}
		}
		
	} // main 종료
}
