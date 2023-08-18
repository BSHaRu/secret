<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- guestbook_writer.jsp --> 
<%@ page import="java.sql.*, javax.sql.*, javax.naming.*" %>
<%@ page import="util.*" %>
<%
	request.setCharacterEncoding("utf-8");
	String msg = "";
%>
<jsp:useBean id="guestBook" class="vo.GuestBook" />
<jsp:setProperty name = "guestBook" property="*" />
<%-- <%=guestBook %> --%>
<%
	/*
	DataSource ds = (DataSource)new InitialContext().lookup(
		"java:comp/env/jdbc/MySQLDBCP"		
	);
	Connection conn = ds.getConnection();
	*/
	Connection conn = DBCPUtil.getConnction();
	PreparedStatement pstmt = conn.prepareStatement(
		"INSERT INTO test_guestbook VALUES(null,?,?,?)"		
	);
	pstmt.setString(1, guestBook.getGuestName());
	pstmt.setString(2, guestBook.getPassword());
	pstmt.setString(3, guestBook.getMessage());
	msg = (pstmt.executeUpdate() > 0) ? "방명록 등록 성공" : "방명록 등록 실패";
	DBCPUtil.close(pstmt, conn);
	/*
	if(pstmt != null) pstmt.close();
	if(conn != null) conn.close(); // context pool로 연결해서 close하는거는 자원해제가 아니라 자원반납임
									// -> 그래서 그 차이가 뭔데?
	*/
%>									

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 작성 확인</title>
</head>
<body>
	<h3><%=msg %></h3>
	<!-- 방명록 작성 후 이동할 페이지 링크 -->
	<a href="guestbook_list.jsp">[목록 보기]</a>
</body>
</html>