<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 선언부 -->
<%!
	// 필드 선언
	String s = "test"; 
	int count = 0; 
	
	
	String getStr(String str){
		s += str;
		count++;
		return s;
	}
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>time.jsp</title>
</head>
<body>
	<h1>TIME JSP</h1>
	<%= getStr("배고파요!!\t")+count %>
	<!-- 스크립트릿(scriptlet) java code 작성 -->
	<%
		java.text.SimpleDateFormat sdf =
			new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(new java.util.Date());
	%>
	현재시간 : <%= date %> <br/>
	기본시간 : <%= new java.util.Date() %> <br />
</body>
</html>