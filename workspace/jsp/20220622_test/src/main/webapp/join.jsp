<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
</head>
<style>
	
</style>
<body>
<div align="center">
	<h2>회원 가입</h2>
	<form action="joinSubmit.jsp" method="post">
		아이디 <input type="text" name="id" required/> <br>
		비밀번호 <input type="password" name="pass" required/> <br>
		이름 <input type="text" name="name" required/> <br>
		주소 <input type="text" name="addr" required/> <br>
		전화번호 <input type="text" name="phone" required/> <br>
		성별 <input type="radio" name="gender" value="male" checked /> 남성 
			<input type="radio" name="gender" value="female" /> 여성 <br />
		나이 <input type="number" name="age" /> <br />
		<button>회원가입</button>
	</form>
</div>
</body>
</html>