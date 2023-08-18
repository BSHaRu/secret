<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="conn.jsp" %>    
<%
	String num = request.getParameter("board_num");
	int board_num = Integer.parseInt(num);
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM board_test WHERE board_num = ?";
	// 해당 검색 값을 저장하기 위해 변수 3개 null로 만듬
	String title = null;
	String auth = null;
	String content = null;
	
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, board_num);
		rs = pstmt.executeQuery();
		if(rs.next()){
			title = rs.getString("board_title");
			auth = rs.getString("board_auth");
			content = rs.getString("board_content");
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm.jsp</title>
</head>
<body>
	<form action="updateSubmit.jsp" method="post">
		<!-- 수정 할 때 제목인지 작성잔지 내용인지 모르니깐 그걸 지정해줌 -->
		<input type="hidden" name="board_num" value="<%=board_num %>" />
		<table>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="board_title" autofocus required value="<%=title %>" />
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" name="board_auth" required value="<%=auth %>" />
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
												<!-- textarea는 tag와 tag 사이가 value 값임  -->
					<textarea name="board_content" rows="20" cols="50"><%=content %></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="작성완료" />
					<input type="reset" value="다시쓰기" />
					<input type="button" onclick="location.href='index.jsp';" value="main" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>