package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	
	// 어짜피 deriver | url | id | pw는 안바뀌니깐 static final로 잡아서 고정시킴
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bigdata";
	private static final String USER = "bigdata";
	private static final String PASS = "12345";
	
	private DBHelper() {}
	
	// conn을 초기값(null)으로 그냥 선언만 해놓고(private 있는 구문), 
	// conn이 null이면(conn이 없으면) driver연결해주고, 있으면 conn 실행안함
	// -> DB 할때마다 하나하나 치기 귀찮으니깐 static으로 만들어놨기때문에
	// 앞으로 DB사용할 때 getConn 호출만하면 자동으로 conn되게 한거임
	// => 싱글톤때 처럼 그냥 하나 만들어놓으면 앞으로 계속 사용 하려고 하는거 같음
	// ==> 한 프로젝트 내에서 DB를 여러번 쓴다고 하면 이거 하나 만들어 놓으면
	// 매번 입력안해도되고, getConn을 호출만 하면 해결되니깐 쓰는거.
	private static Connection conn;	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
				System.out.println("Driver 등록 완료");
			} catch (ClassNotFoundException e) {
				System.out.println("Driver 등록 실패");
			} catch (SQLException e) {
				System.out.println("DB 연결 실패");
			}
		}// if 종료
		return conn;
	} // getConn 종료
	
	// AutoCloseable : 모든 close를 가진 친구들을 다 가지고 있기때문에 DB를 받던, 입출력을 받던 
	// 이걸로 모두 다 받을 수 있기 때문에 이거 하나로 close를 다 해결가능
	// ...(가변형 인자열) : 변수가 몇개가 넘어 올지 모를때 사용 -> 무조건 맨뒤에 사용해줘야됨
	// -> 전달되는 변수 개수 만큼을 배열로 전달받음(1개만 있어도 배열로 취급해줌)
	/* => 아래처럼 DBHelper.close(뭐시깽이)하면서 나열하는게 귀찮으니깐
	  	DBHelper.close(rs);
		DBHelper.close(pstmt);
		DBHelper.close(stmt);
		DBHelper.close(conn);
		이걸 간단하게 해결하기위해서 ...을 쓰고, ...은 배열로 받으니깐 향상된 for문을 통해 반복하겠다.
		그럼 DBHelper.close(rs,pstmt,stmt,conn); 이렇게 사용 가능
	*/
	public static void close(AutoCloseable... closer) {
		for(AutoCloseable close : closer) {
			try {
				if(closer != null) close.close(); // 앞에 close는 AutoCloseable의 기존에 가지고 있는 close
			} catch (Exception e) {}			// 뒤에 있는 close는 향상된 for문으로 받은 변수의 close
		} // for 종료
	} // close 종료
}
