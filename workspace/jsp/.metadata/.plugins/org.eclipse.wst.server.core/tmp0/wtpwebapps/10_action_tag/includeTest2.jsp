<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<hr/>
<h1>여기는 include test2.jsp</h1>
<% String str = "개 노잼인데요";%>
<%
	response.addHeader("bbb", "111");

	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
%> 
	name : <%=name %>
