<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	ul{
		list-style : none;
	}
	a{
		text-decoration-line : none;
	}
</style>    

<%
	request.setCharacterEncoding("UTF-8");
%>

<div align="right">
	<ul>
		<li>
			<a href="index.jsp?page=default">home</a>
			<a href="index.jsp?page=login">로그인</a>
			<a href="index.jsp?page=join">회원가입</a>
		</li>
	</ul>
</div>    
<hr/>