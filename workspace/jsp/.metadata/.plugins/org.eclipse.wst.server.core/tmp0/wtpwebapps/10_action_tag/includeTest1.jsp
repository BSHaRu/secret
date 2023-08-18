<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>includeTest1.jsp</title>
</head>
<body>
	<% response.addHeader("aaa", "159"); %>
	<h1>여기는 include test1.jsp 입니다.</h1>
	<%-- <%@ include file="includeTest2.jsp" %> --%>
	<jsp:include page="includeTest2.jsp" >
		<jsp:param name="name" value="go home" />
	</jsp:include>
	
	<%-- <%= str %> --%>
</body>
</html>