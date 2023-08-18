<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="jstlFMT.jsp" method="post">
		<select name="encode">
			<option value="ko_KR">한국어</option>
			<option value="en_US">English</option>			
		</select> 
		<br/>
		<input type="text" name="name" /> <br/>
		<input type="text" name="hobby" /> <br/>
		<button>확인</button>
	</form>
</body>
</html>