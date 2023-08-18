<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sessionLogin.jsp</title>
</head>
<body>
	<form action="sessionCheck.jsp" method="post">
		아이디 : <input type="text" name="uid" /> <br/>
		비밀번호 : <input type="password" name="upw" /> <br/>
		<button>로그인</button>
	</form>
</body>
</html>