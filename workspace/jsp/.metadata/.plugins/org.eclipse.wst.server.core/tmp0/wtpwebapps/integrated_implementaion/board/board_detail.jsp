<%@page import="utils.DBCPUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	request.setCharacterEncoding("utf-8");

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	String board_num = request.getParameter("board_num");
	int num = Integer.parseInt(board_num);
	
	String name = "";
	String title = "";
	String content = "";
	
	String sql = "SELECT board_name, board_title, board_content"
				+" FROM test_board WHERE board_num = ?";
	conn = DBCPUtil.getConnection();
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		if(rs.next()){
			name = rs.getString("board_name");
			title = rs.getString("board_title");
			content = rs.getString("board_content");
		}
		DBCPUtil.close(rs, pstmt);
		
		sql = "UPDATE test_board SET "
				+" board_readcount = board_readcount +1"
				+" WHERE board_num = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		DBCPUtil.close(rs, pstmt, conn);
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 상세내용</h1>
	<table>
		<tr>
			<td>작성자</td>
			<td><%=name %></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><%=title %></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="board_content" readonly cols=40 rows=10><%=content %></textarea>
			</td>
		</tr>
		<tr>
			<td colspan=2>
				<a href="board_update.jsp?board_num=<%=num %>">[수정]</a>
				<a href="board_delete.jsp?board_num=<%=num %>">[삭제]</a>
				<a href="board_list.jsp">[목록]</a>
			</td>
		</tr>
	</table>
</body>
</html>