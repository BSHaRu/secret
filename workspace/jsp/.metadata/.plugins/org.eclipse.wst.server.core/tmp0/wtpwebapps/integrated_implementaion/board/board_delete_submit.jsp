<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, utils.*" %>      
<!-- 게시글 삭제 요청 처리 -->
<% 
	String num = request.getParameter("board_num");

	Connection conn = DBCPUtil.getConnection();
	PreparedStatement pstmt = null;
	String msg = "";
	
	String sql = "DELETE FROM test_board "
			 	+" WHERE board_num = " + num;
	try{
		pstmt = conn.prepareStatement(sql);
		int result = pstmt.executeUpdate();
		if(result > 0){
			out.print("<script>");
			out.print("alert('삭제 완료');");
			out.print("location.href='board_list.jsp';");
			out.print("</script>");
		}else{
			out.print("<script>");
			out.print("alert('삭제 실패');");
			out.print("history.back();");
			out.print("</script>");
		}
	}catch(Exception e){
		out.print("<script>");
		out.print("alert('삭제 실패!!!');");
		out.print("history.back();");
		out.print("</script>");
		e.printStackTrace();
	}finally{
		DBCPUtil.close(pstmt,conn);
	}
%>
<!-- 게시글 삭제 요청 결과 처리 -->
<%-- <script>
	alert('<%=msg%>');
	location.href="board_list_page.jsp";
</script>
     --%>
    