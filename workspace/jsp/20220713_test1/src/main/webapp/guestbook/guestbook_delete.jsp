<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*, utils.*"%>
    
<!DOCTYPE html>
<html>
<head>
	<title>방명록 메시지 삭제함</title>
</head>
<body>
<br/>

<!-- 삭제 확인 처리  알림 code 작성-->
<%
	String msg = "";
	String gid = request.getParameter("id");
	String pass = request.getParameter("password");
	int id = Integer.parseInt(gid);
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String sql = "DELETE FROM test_guestbook "
				+" WHERE id = ? AND password= ?";
	conn = DBCPUtil.getConnection();
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, pass);
		int result = pstmt.executeUpdate();
		msg = (result > 0) ? "방명록을 삭제하였습니다." : "방명록 삭제 실패 하였습니다.";
	}catch(Exception e){
		msg = "삭제 못함";
		e.printStackTrace();
	}finally{
		DBCPUtil.close(pstmt, conn);
	}
%>
	<h3><%=msg %></h3>				
	<a href="list.gc">[목록 보기]</a>
</body>
</html>