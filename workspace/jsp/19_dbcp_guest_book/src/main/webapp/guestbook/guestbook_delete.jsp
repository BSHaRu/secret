<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- guestbook_delete.jsp -->
<%@ page import="java.sql.*, util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 메세지 삭제</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String msg= "";
		String paramID = request.getParameter("id");
		String password  = request.getParameter("password");
		
		Connection conn = DBCPUtil.getConnction();
		PreparedStatement pstmt = null;
		
		try{
			int id = Integer.parseInt(paramID);
			String sql = "DELETE FROM test_guestbook WHERE id = ? AND password = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.setString(2,password);
			int result = pstmt.executeUpdate();
			msg = (result > 0) ? "삭제 성공" : "삭제 실패";
		}catch(Exception e){
			msg = "삭제 실패";
			e.printStackTrace();
		}finally{
			DBCPUtil.close(pstmt, conn);
		}
	%>
	<h3><%=msg %></h3>
	<a href="guestbook_list.jsp">[목록 보기]</a>
</body>
</html>