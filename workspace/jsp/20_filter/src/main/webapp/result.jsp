<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result.jsp</title>
</head>
<body>
	<h3>result.jsp</h3>
	<%
		String test = request.getParameter("test");
		out.print("test : " + test);
	%>
	<a href="index.html">index</a>
</body>
</html>