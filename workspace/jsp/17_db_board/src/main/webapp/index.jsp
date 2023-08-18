<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "conn.jsp" %>    
<%
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM board_test ORDER BY board_num DESC";
	/* String sql = "SELECT ROW_NUMBER() OVER(ORDER by board_num ASC) AS rnum, "
				+ " board_test.* FROM board_test ORDER by board_num DESC"; */
	try{
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
	}catch(SQLException e){
		out.println("질의 실행 실패 <br/>");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<style>
	table tr:hover td{
		cursor:pointer;
		background-color: #797979;
		color : #fafafa;
	}
</style>
</head>
<body>
	<section>
		<br/>
		<a href="writeForm.jsp">글작성</a>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성시간</th>
				<th>기타</th>
			</tr>
			<% while(rs.next()){ %>
			<!-- 클릭시 상세보기를 만들어줄꺼임 -->
				<tr onclick="location.href='detail.jsp?board_num=<%=rs.getInt("board_num") %>'">
					<td><%=rs.getInt("board_num") %></td>
					<%-- <td><%=rs.getInt("rnum") %></td>
					<td><%=rs.getString("board_title") %></td>
					<td><%=rs.getString("board_auth") %></td>
					<td><%=rs.getTimestamp("board_date") %></td> --%>
					<td>
						<!-- 그냥 수정 삭제하면 어떤 게시글인지 모르니깐 '?게시번호를 전달해주는거임' -->
						<a href="updateForm.jsp?board_num=<%=rs.getInt("board_num") %>">수정</a>
						<a href="delete.jsp?board_num=<%=rs.getInt("board_num") %>">삭제</a>
					</td>
				</tr>
			<% }%> <!-- while end -->
		</table>
	</section>
	<%
		rs.close();
		pstmt.close();
		conn.close();
	%>
</body>
</html>