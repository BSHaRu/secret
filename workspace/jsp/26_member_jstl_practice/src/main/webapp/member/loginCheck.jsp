<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<!-- loginCheck.jsp -->
<!--
	 로그인 요청 처리 
	 로그인 성공한 회원의 정보를 member 를 키값으로 저장
-->
<f:requestEncoding value="utf-8" />
<jsp:useBean id="member" class="vo.MemberVO" scope="session" />
<jsp:setProperty property="u_id" name="member" />
<jsp:setProperty property="u_pass" name="member" /> <!-- 어짜피 2개만 필요하니 property="*"안하고 그냥 2개를 쓴거임  -->

<c:catch var="e">
	<s:query var="rs" dataSource="jdbc/MySQLDBCP">
		SELECT * FROM big_member WHERE u_join = 'Y' <%-- u_join = 'Y' 가입상태가 되어있어야되니깐 체크해주는거 --%>
				 AND u_id = ? AND u_pass = ?
		<s:param>${param.u_id }</s:param>
		<s:param>${param.u_pass }</s:param>
	</s:query>
	<c:choose>
		<c:when test="${rs.rowCount > 0 }"> <%-- 검색된 열의 개수 --%>	<%--${rs.rows[0].u_num } 이렇게 해도됨 --%>
			<jsp:setProperty name="member" property="u_num" value="${rs.rows[0]['u_num'] }" /> <%-- 검색된 결과값이 어짜피 1개밖에없음 --%> 
			<jsp:setProperty name="member" property="u_age" value="${rs.rows[0]['u_age']}"/>
			<jsp:setProperty name="member" property="u_gender" value="${rs.rows[0]['u_gender']}"/>
			<jsp:setProperty name="member" property="u_regdate" value="${rs.rows[0]['u_regdate']}"/>
			
			<%-- session 추가 완료 확인 하고 Cookie 확인 --%>
			<c:if test="${!empty param.login }">
				<%
					Cookie cookie = new Cookie("u_id",member.getU_id());
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*7); 
					response.addCookie(cookie);
				%>
			</c:if>
			
			<script>
				alert('${member.u_id} : 로그인 성공');
				location.href='../index.jsp';
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert('로그인 실패');
				history.back();
			</script>
			<c:remove var="member" scope="session" /> <%-- session 정보 삭제 안하면 실패했을 때 기록이 그대로 남아서 --%>
		</c:otherwise>								<%-- 무조건 remove해서 삭제 해줘야된다. --%>
	</c:choose>
</c:catch>
<c:if test="${!empty e}">
	<script>
		alert('로그인 실패 : ${e.message}');
		history.go(-1);
	</script>
</c:if>

