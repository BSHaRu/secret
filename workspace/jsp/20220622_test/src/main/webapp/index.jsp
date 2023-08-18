<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String nextPage = request.getParameter("page");
	if(nextPage == null){
		nextPage = "default";
	}
	nextPage = nextPage + ".jsp";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
%>
	<table>
		<tr>
			<th height="100">
				<jsp:include page="header.jsp" />
			</th>
		</tr>
		<tr>
			<td width="900" height="500">
				<jsp:include page="<%=nextPage %>" />
			</td>
		</tr>
		<tr>
			<td height="100">
				<jsp:include page="footer.jsp" />
			</td>
		</tr>
	</table>
</body>
</html>