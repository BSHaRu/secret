<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<h2>로그인</h2>
	<form action="loginCheck.jsp" method="post">
		아이디 <input type="text" name="id" /> <br/>
		비밀번호 <input type="password" name="pass" /> <br />
		<label>
			<input type="checkbox" name="login" value="login" />
			로그인 상태 유지
		</label>
		<input type="submit" value="로그인" />
	</form>
</body>
</html>