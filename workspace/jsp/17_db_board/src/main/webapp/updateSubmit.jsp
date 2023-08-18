<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="conn.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	// 글번호, 제목, 작성자, 내용 -> 모두 String으로 받아야됨
	String num = request.getParameter("board_num");
	String title = request.getParameter("board_title");
	String auth = request.getParameter("board_auth");
	String content = request.getParameter("board_content");
	// 사용할라면 정수로 바꿔줌
	int board_num = Integer.parseInt(num);
	
	String sql = "UPDATE board_test SET "
						+ " board_title = ?, "
						+ " board_auth = ?, "
						+ " board_content = ? "
						+ " WHERE board_num = ?";
	PreparedStatement pstmt = null;
	String msg = "";
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, auth);
		pstmt.setString(3, content);
		pstmt.setInt(4, board_num);
		int result = pstmt.executeUpdate();
		msg = result > 0 ? "수정 완료" : "수정 실패";
	}catch(Exception e){
		e.printStackTrace();
		msg = "수정 실패";
	}finally{
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
%>

<script>
	alert('<%=msg%>');
	location.href='index.jsp';
</script>

