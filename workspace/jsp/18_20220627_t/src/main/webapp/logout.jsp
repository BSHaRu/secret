<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	session.removeAttribute("loginMember");

	Cookie cookie = new Cookie("id","");
	cookie.setMaxAge(0);
	response.addCookie(cookie);
%>
<script>
	alert("로그아웃 완료");
	location.href="index.jsp";
</script>
