<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.sql.*, utils.*" %>  
<jsp:useBean id="gb" class="vo.TestGuestBookVO" />
<jsp:setProperty name="gb" property="*" />

<%
	String msg = "";	

	Connection conn;
	PreparedStatement pstmt;
	
	String sql = "INSERT INTO test_guestbook VALUES(null,?,?,?)";
	conn = DBCPUtil.getConnection();
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, gb.getGuestName());
	pstmt.setString(2, gb.getPassword());
	pstmt.setString(3, gb.getMessage());
	msg = (pstmt.executeUpdate() > 0 ) ? "방명록 등록 성공" : " 방명록 등록 실패";
	DBCPUtil.close(pstmt, conn);
%>
<!DOCTYPE html>
<html>
<head>
	<title>방명록 메시지 작성 확인</title>
</head>
<body>
<!-- 방명록 작성 후 처리 결과 출력 code 작성 -->
<h3><%=msg %></h3>

<!-- 방명록 작성 후 처리 결과 출력 code 작성 end-->
<a href="list.gc">[목록 보기]</a>
</body>
</html>