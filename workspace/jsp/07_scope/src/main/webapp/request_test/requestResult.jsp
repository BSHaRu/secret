<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String encoding = request.getCharacterEncoding();
		out.println("encoding : " + encoding+"<br/>");
		
		request.setCharacterEncoding("UTF-8");
		
		encoding = request.getCharacterEncoding();
		out.println("encoding : " + encoding+"<br/>");
		
		String contextPath = request.getContextPath();
		out.println("contextPath : " + contextPath);
		out.println("<br/>");
		
		String requestURI = request.getRequestURI();
		out.println("requestURI : " + requestURI);
		out.println("<br/>");
		
		String queryString = request.getQueryString();
		out.println("queryString(get방식에만 존재) : " + queryString);
		out.println("<br/>");
		
		String ip = request.getRemoteAddr();
		out.println(ip+"<br/>");
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String[] hobbys = request.getParameterValues("hobby");
	%>
	이름 : <%=name %> <br/>
	성별 : 
	<% if(gender.equals("male")){ %>
	남성
	<%}else{ %>
	여성 
	<%} %>
	<br/>
	취미 : 
	<% for(String hobby : hobbys){ %> 
		<%=hobby %> &nbsp;&nbsp;&nbsp;
	<%} %>
	<br/>
	<%
		java.util.Map<String,String[]> parameterMap = 
			request.getParameterMap();
		for(String key : parameterMap.keySet()){
			out.print(key + " : " + java.util.Arrays.toString(parameterMap.get(key)));
			out.print("<br/>");
		}
	%>
</body>
</html>