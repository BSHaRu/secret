<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, utils.*" %>
<%@ page import="java.util.*, vo.*" %>   
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>

<jsp:useBean id="pm" class="utils.PageMaker" />
<c:if test="${!empty param.page }">
	<c:set target="${pm.cri }" property="page" value="${param.page }" />
</c:if>

<s:query var="r" dataSource="jdbc/MySQLDBCP">
	SELECT count(*) AS c FROM test_guestbook
</s:query>
<c:set target="${pm }" property="totalCount" value="${r.rows[0].c }" />

<s:query var="rs" dataSource="jdbc/MySQLDBCP"
		startRow="${pm.cri.startRow }" maxRows="${pm.cri.perPageNum }" >
	SELECT * FROM test_guestbook
	ORDER by id DESC
</s:query>
<c:if test="${rs.rowCount > 0 }" >
	<jsp:useBean id="gbList" class="java.util.ArrayList" type="java.util.List<vo.TestGuestBookVO>" />
	<c:forEach var="guest" items="${rs.rows }">
		<jsp:useBean id="gb" class="vo.TestGuestBookVO" />
		<c:set target="${gb }" property="id" value="${guest.id }" />
		<c:set target="${gb }" property="guestName" value="${guest.guestName }" />
		<c:set target="${gb }" property="password" value="${guest.password }" />
		<c:set target="${gb }" property="message" value="${guest.message }" />
		<c:set var="temp" value="${gbList.add(gb) }" />
		<c:remove var="gb" />
	</c:forEach>
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>방명록 메시지 목록</title>
</head>
<style>
	
</style>
<body>
<!-- 방명록 작성 전달을 위한 form tag 완성 -->
<form action="write.gc" method="post">
	<table>
		<tr>
			<td colspan=3><h1>방명록 작성</h1></td>
		</tr>
		<tr>
			<td colspan=2></td>
			<td rowspan="4">
				<input type="submit" value="메시지 남기기" style="width:100%;height:100px;margin-left:20px;"/>
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="guestName" style="width:100%;"/></td>
		<tr>
			<td>암호</td>
			<td><input type="password" name="password" style="width:100%;"/></td>
		</tr>
		<tr>
			<td>메시지</td>
			<td><textarea cols="35" name="message" rows="3"></textarea></td>
		</tr>
	</table>
</form>
<hr/>

<!-- 방명록 리스트 정보 출력 -->
	<c:choose>
		<c:when test="${!empty gbList }">
			<h1>방명록 리스트</h1>
			<c:forEach var="g" items="${gbList }">
				<table border="1" cellspacing="0" cellpadding="10">
					<tr>
						<td>
							메시지 번호 : ${g.id } <br/>
							손님 이름 : ${g.guestName } <br/>
							메시지 : ${g.message } <br/>
							<a href="confirm.gc?id=${g.id}">삭제하기</a> <br/>
						</td>
					</tr>
				</table>
				</c:forEach>
				<%-- 방명록 리스트 정보 출력 end --%>
			<%-- 방명록 정보에 따른 paging block 화면 출력 작성 --%>
			<c:if test="${pm.prev }">
				<a href="list.gc?page=1">[처음]</a>
				<a href="list.gc?page=${pm.startPage-1 }">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${pm.startPage }" end="${pm.endPage }">
				<c:choose>
					<c:when test="${pm.cri.page eq i }"> 
						<span style="color:red;">[${i }]</span>
					</c:when>
					<c:otherwise>
						<a href="list.gc?page=${i}">[${i }]</a>
					</c:otherwise>
				</c:choose>	
			</c:forEach>
			
			<c:if test="${pm.next }">
				<a href="list.gc?page=${pm.endPage+1 }">[다음]</a>
			</c:if>
			<c:if test="${pm.cri.page != pm.maxPage }">
				<a href="list.gc?page=${pm.maxPage }">[마지막]</a>
			</c:if>
			<%-- 방명록 정보에 따른 paging block 화면 출력 end --%>
		</c:when>
		<c:otherwise>
			<span>등록된 메시지가 없습니다.</span>
		</c:otherwise>
	</c:choose>
</body>
</html>