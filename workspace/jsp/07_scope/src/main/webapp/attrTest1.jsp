<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>attrTest1.jsp</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		if(id != null){
			application.setAttribute("id",id);
			pageContext.setAttribute("pageContextID", id);
			
		}
	%>
	<h3>request param id : <%=id %></h3>
	<h3>애플리케이션 영역 id : <%=application.getAttribute("id") %></h3>
	<h3>pageContext id : <%=pageContext.getAttribute("pageContextID") %> </h3>
	<form action="attrTest2.jsp" method="post">
		이메일 : <input type="email" name="email" required/>
		<button>확인</button>
	</form>
</body>
</html>