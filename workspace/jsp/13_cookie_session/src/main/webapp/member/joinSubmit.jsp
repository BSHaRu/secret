<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinSubmit.jsp</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<jsp:useBean id="joinMember" class="beans.MemberBean" scope="application" />
	<jsp:setProperty property="*" name="joinMember" /> <!-- property : 필드 이름 // name : id값 -->
	<%= application.getAttribute("joinMember") %> <br/>
	<a href="./login.jsp">로그인</a>
</body>
</html>