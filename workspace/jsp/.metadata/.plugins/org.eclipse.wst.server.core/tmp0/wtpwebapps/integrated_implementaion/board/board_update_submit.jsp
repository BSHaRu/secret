<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, utils.*" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="upBoard" class="vo.TestBoardVO" />
<jsp:setProperty name="upBoard" property="*" />    
<!-- 게시글 수정 처리 -->
<%
	Connection conn = DBCPUtil.getConnection();
	PreparedStatement pstmt = null;
	try{
		String sql = "UPDATE test_board SET "
					+" board_name = ? ,"
					+" board_pass = ? ,"
					+" board_title = ? ,"
					+" board_content = ? "
					+" WHERE board_num = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, upBoard.getBoard_name());
		pstmt.setString(2, upBoard.getBoard_pass());
		pstmt.setString(3, upBoard.getBoard_title());
		pstmt.setString(4, upBoard.getBoard_content());
		pstmt.setInt(5, upBoard.getBoard_num());
		
		int result = pstmt.executeUpdate();
		if(result > 0){
			out.print("<script>");
			out.print("alert('수정 완료');");
			out.print("location.href='board_list.jsp';");
			out.print("</script>");
		}else{
			out.print("<script>");
			out.print("alert('수정 실패!!!');");
			out.print("history.back();");
			out.print("</script>");
		}
	}catch(Exception e){
		e.printStackTrace();
		out.print("<script>");
		out.print("alert('수정 실패');");
		out.print("history.back();");
		out.print("</script>");
	}finally{
		DBCPUtil.close(pstmt,conn);
	}
%>

<!-- 게시글 수정 결과 처리 -->
<%-- <script>
	alert('<%=msg%>');
	location.href="board_.jsp?board_num=<%=upBoard.getBoard_num()%>";
</script>
 --%>




