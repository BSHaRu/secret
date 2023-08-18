<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- join.jsp -->
<script type="text/javascript" src="js/input.js"> </script>
<form action="user/joinCheck.jsp" method="post">
	<table>
		<tr>
			<td colspan="2"><h1>회원가입</h1></td>
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
			<td>이름</td>		
			<td>
				<input type="text" name="name" data-msg="이름" />
			</td>
		</tr>
		<tr>
			<td>주소</td>		
			<td>
				<input type="text" name="addr" data-msg="주소" />
			</td>
		</tr>
		<tr>
			<td>전화번호</td>		
			<td>
				<input type="text" name="phone" data-msg="전화번호" />
			</td>
		</tr>
		<tr>
			<td>성별</td>		
			<td>
				<label>
					<input type="radio" name="gender" value="남성" checked />
					남성
				</label>
				<label>
					<input type="radio" name="gender" value="여성" />
					여성
				</label>
			</td>
		</tr>
		<tr>
			<td>나이</td>		
			<td>
				<input type="number" name="age" data-msg="나이" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button>회원가입</button>
			</td>
		</tr>
	</table>
</form>