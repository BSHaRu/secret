<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, utils.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String board_num = request.getParameter("board_num");
	int num = Integer.parseInt(board_num);
	
	String sql = "SELECT board_name, board_pass, board_title, board_content "
				+" FROM test_board WHERE board_num = ?";
	
	Connection conn = DBCPUtil.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String name = "";
	String pass = "";
	String title = "";
	String content = "";
	
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		if(rs.next()){
			name = rs.getString("board_name");
			pass = rs.getString("board_pass");
			title = rs.getString("board_title");
			content = rs.getString("board_content");
		}
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
	<h1>게시물 수정</h1>
	<form action="board_update_submit.jsp" method="post">
		<input type="hidden" name="board_num" value="<%=num %>" />
			<table>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="board_name" value="<%=name %>" /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="board_pass" value="<%=pass %>" required/></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="board_title" value="<%=title %>" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="board_content" rows=10 cols=40><%=content %></textarea></td>
				</tr>
				<tr>
					<td colspan=2>
						<input type="submit" value="수정완료"/>
					</td>
				</tr>
			</table>
	</form>
</body>
</html>