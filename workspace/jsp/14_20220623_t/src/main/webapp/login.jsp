<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- login.jsp -->
<!-- 지금은 html이 js밖에 없어서 type은 생략가능함 -->
<script type="text/javascript" src="js/input.js"> </script>
<form action="user/loginCheck.jsp" method="post">
	<table>
		<tr>
			<td colspan="2"><h1>로그인</h1></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>
				<!-- data-속성 = "사용자가 임의로 Dom에 저장" -->
				<!-- 이 속성값을 통해서 js에서 for > if문 돌려서 해당 빈값 작성해달라고 메세지 날릴 수 있음 -->
				<input type="text" name="id" data-msg="아이디" />
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="pass" data-msg="비밀번호" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label>
					<input type="checkbox" name="rememberme" />
					로그인 상태 유지
				</label>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button>로그인</button>
			</td>
		</tr>
	</table>
</form>