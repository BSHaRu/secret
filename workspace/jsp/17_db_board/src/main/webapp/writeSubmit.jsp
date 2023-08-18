<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="conn.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("board_title");
	String auth = request.getParameter("board_auth");
	String content = request.getParameter("board_content");
	
	PreparedStatement pstmt = null;
	String message = "";
	String sql = "INSERT INTO board_test VALUES(null,?,?,?,now())";
	
	try{
		pstmt = conn.prepareStatement(sql);		
		pstmt.setString(1,title);
		pstmt.setString(2,auth);
		pstmt.setString(3,content);
		
		int result = pstmt.executeUpdate();
		message = (result > 0) ? "글 작성 완료" : "글 작성 실패";
	}catch(Exception e){
		e.printStackTrace();
		message = "글 작성 실패";
	}finally{
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
%>

<script>
	alert('<%=message %>');
	location.href='index.jsp';
</script>



