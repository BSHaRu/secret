<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="connection.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberList.jsp</title>
</head>
<body>
	<%
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member ORDER by num DESC";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
	%>
	
	<table border="1">
		<tr>
			<th colspan="3">회원정보</th>
		</tr>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>주소</th>
		</tr>
		<%
			while(rs.next()){
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String addr = rs.getString("addr");
		%>	
			<!-- 해당 값 누르면 업데이트(수정)할 수 있는 곳으로 이동 -->	
			<tr onclick="location.href='updateForm.jsp?num=<%=num%>';">
				<td><%=num %></td>
				<td><%=name %></td>
				<td><%=addr %></td>
			</tr>
		<% 
			}
			rs.close();
			stmt.close();
			conn.close();
		%>
	</table>
	<a href="index.jsp">Main go</a>
</body>
</html>