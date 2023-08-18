<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm.jsp</title>
</head>
<body>
	<form action="writeSubmit.jsp" method="post">
		<table>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="board_title" autofocus required />
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" name="board_auth" required />
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="board_content" rows="20" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="작성완료" />
					<input type="reset" value="다시쓰기" />
					<input type="button" onclick="location.href='index.jsp';" value="집으로" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>