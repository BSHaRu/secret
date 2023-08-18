package mysql.a_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class PreparedEx {

	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/bigdata";
		String user = "bigdata";
		String pass = "12345";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	// 검색질의 검색은 ResultSet으로 무조건 하기!!
		
		String sql = "";
		
		try {
			Class.forName(driver);
			System.out.println("Driver 로드 완료");
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("DB 연결 완료");
			// 동적쿼리 - PreparedStatement
			// 쿼리가 먼저 등록되어 있어야 실행됨.
			sql = "INSERT INTO student(stuName, stuDept, stuGrade) "
					+ " VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "홍길동"); 		// pstmt.set타입(와일드카드번호, 해당값)
			pstmt.setString(2, "Devleopers");
			pstmt.setInt(3, 4);
			
			int result = pstmt.executeUpdate();
			System.out.println(result+"명의 정보가 추가되었습니다.");
			
			pstmt.close();
			
			sql = "SELECT * FROM student WHERE stuNo = ?";
			Scanner sc = new Scanner(System.in);
			System.out.println("검색할 학생의 번호를 입력하세요.");
			int no = sc.nextInt();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) { 	// 해당 내용이 1개 밖에없기때문에 while문안쓰고 if문씀
				String stuName = rs.getString("stuName");
				String stuDept = rs.getString("stuDept");
				int stuGrade = rs.getInt("stuGrade");
				String stuClass = rs.getString("stuClass");
				char stuGender = rs.getString("stuGender").charAt(0); // getChar가 없어서 Sting으로 받고 charAt을 씀
				Date stuDate = rs.getTimestamp("stuDate"); // Date는 util로 import해야됨
				System.out.println(stuName+"-"+stuDept+"-"+stuGrade+"-"
						+stuClass+"-"+stuGender+"-"+stuDate);
				System.out.printf("이름 : %s | 과목 : %s | 학년 : %d \n"
						+ "학급 : %s | 성별 : %s | 날짜 : %s \n",
						stuName,stuDept,stuGrade,stuClass,stuGender,stuDate);
			}else {
				System.out.println(no+"번 학생정보가 존재하지 않습니다.");
			}
			
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
