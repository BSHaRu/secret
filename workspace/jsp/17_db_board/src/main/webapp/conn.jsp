<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<%
	Connection conn = null;
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		out.println("Driver class 로드 완료");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/userdb", 
				"root", "1234"
		);
		out.println("connetion 생성 완료");
	}catch(ClassNotFoundException e){
		out.println("Driver class를 찾지 못함");
	}catch(SQLException e){
		out.println("db 연결 정보 오류 : " + e.toString());
	}
%>