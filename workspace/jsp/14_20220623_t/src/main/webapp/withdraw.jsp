<%@page import="java.util.*, vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberVO> memberList
		= (List<MemberVO>)application.getAttribute("memberList");
	MemberVO loginMember
		= (MemberVO)session.getAttribute("loginMember");
	
	memberList.remove(loginMember);
	response.sendRedirect("logOut.jsp");
%>
    