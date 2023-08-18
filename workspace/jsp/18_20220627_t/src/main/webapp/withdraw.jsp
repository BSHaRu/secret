<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, util.*" %>

<jsp:useBean id = "loginMember" class="vo.MemberVO" scope="session" />

<%
	if(loginMember.getName() == null){
%>		<script>
			alert("잘못된 접근입니다.");
			history.back();
		</script>	
	<% }else if(loginMember.getId().equals("admin")){ %>
		<script>
			alert("관리자 계정은 삭제할 수 없습니다.");
			history.go(-1);
		</script>
	<%}else{
		Connection conn = null;
		Statement stmt = null;
		
		conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM test_member WHERE num = "
					+ loginMember.getNum();
		stmt = conn.createStatement();
		
		int result = stmt.executeUpdate(sql);
		if(result > 0){
			response.sendRedirect("logout.jsp");
		}else{
			out.print("<script>");
			out.print("alert('회원탈퇴 실패');");
			out.print("history.go(-1)");
			out.print("</script>");
		}
		JDBCUtil.close(stmt,conn);
	}
%> 