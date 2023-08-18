<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- JNDI context -->
<%@ page import = "javax.naming.*" %>    
	<!-- DBCP(DataSource 객체를 사용) -->
<%@ page import = "javax.sql.*" %>	
	<!-- JDBC -->
<%@ page import = "java.sql.*" %>		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<!-- name = jdbc/MySQLDBCP -->
	<%
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Context ctx = new InitialContext();
			// obj로 가져오기떄문에 타입 지정 반드시 해줘야됨 
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySQLDBCP");
			// 커넥션 풀에 대기 중인 커넥션 객체를 빌려오는 작업 
			Connection conn = ds.getConnection();  
			out.print("연결 완료 : "+conn);
		}catch(ClassNotFoundException e){
			out.print("Driver가 없음");
		}catch(NamingException e){
			out.print("해당 이름의 파일이 없음");
		}catch(SQLException e){
			out.print("DB 연결 오류");
		}
	%>
	
</body>
</html>