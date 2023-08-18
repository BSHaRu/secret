<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, utils.*" %>
<!-- 게시글 작성 요청 처리 -->
<%
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("board_name");
	String pass = request.getParameter("board_pass");
	String title = request.getParameter("board_title");
	String content = request.getParameter("board_content");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	String msg = "";
	
	String sql = "INSERT INTO test_board "
				+" VALUES(null,?,?,?,?,0,now())";
	conn = DBCPUtil.getConnection();
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, pass);
		pstmt.setString(3, title);
		pstmt.setString(4, content);
		
		msg = (pstmt.executeUpdate() > 0 ) ? "게시글 작성 완료" : "게시글 작성 실패";
	}catch(Exception e){
		e.printStackTrace();
		msg = "글 못쓰는 찐따";
	}finally{
		DBCPUtil.close(pstmt, conn);
	}
%>
<!-- 게시글 작성 결과 처리 -->
<script>
	alert('<%=msg%>');
	location.href="board_list.jsp";
</script>








