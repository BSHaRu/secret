<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test/page_error.jsp</title>
</head>
<body>
<%
	String s = request.getParameter("aa");
	out.print(Integer.parseInt(s));
%>

</body>
</html>