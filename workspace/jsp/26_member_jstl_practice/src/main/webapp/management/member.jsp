<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<jsp:include page="/common/header.jsp" />
<jsp:useBean id="pm" class="util.PageMaker"/>
<c:set target="${pm}" property="displayPageNum" value="7"/>
<c:set target="${pm.cri}" property="perPageNum" value="15"/>

<!-- 사용자가 요청한 페이지(selectPage) -->
<c:if test="${!empty param.selectPage }">
	<c:set target="${pm.cri}" property="page" 
		   value="${param.selectPage}"/>
</c:if>
<s:query var="r" dataSource="jdbc/MySQLDBCP">
	SELECT count(*) AS cnt FROM big_member
	WHERE u_id != 'admin' AND u_join = 'Y'
</s:query>
<c:set target="${pm }" property="totalCount" value="${r.rows[0].cnt }" />
<%-- 
<s:query var = "rs" dataSource="jdbc/MySQLDBCP">
	SELECT * FROM big_member 
	WHERE u_id != 'admin' AND u_join = 'Y' 
	ORDER BY u_num DESC limit ${pm.cri.startRow }, ${pm.cri.perPageNum }
	숫자를 param으로 넣어주면 param 자체가 문자열로 가져오는거라서 ? 자리에 바로 넣어줘야함 
	<s:param>${pm.cri.startRow }</s:param>
	<s:param>${pm.cri.perPageNum }</s:param>
</s:query>
 --%>
<s:query var = "rs" dataSource="jdbc/MySQLDBCP" 
		startRow="${pm.cri.startRow }" maxRows="${pm.cri.perPageNum }" >
		<%-- limit는 mysql에만 존재하기때문에 이렇게 작성하면 다른 db에서도 사용가능 --%>
		<%-- 대신 limit보다 속도는 느리다 -> 해당 쿼리문을 전체 검색한 후에 startRow랑 maxRows를 가져오는방식임 --%>
	SELECT * FROM big_member 
	WHERE u_id != 'admin' AND u_join = 'Y' 
	ORDER BY u_num DESC
</s:query>
 
<section class="wrap">
<table border=1>
	<tr>
		<th colspan="4">${pm}</th>
	</tr>
	<tr>
		<th colspan="4">회원정보</th>
	</tr>
	<tr>
		<th>회원번호</th>
		<th>아이디</th>
		<th>회원등록일</th>
		<th>기타</th>
	</tr>
	<!-- 등록된 회원 정보가 있을 시 출력 -->
<c:choose>
	<c:when test="${rs.rowCount > 0 }" >
	<c:forEach var="row" items="${rs.rows }">
	<tr>
		<td>${row.u_num }</td>
		<td>${row['u_id'] }</td> <%-- .값을 해도 되고 배열로 해도 됨 --%>
		<td>
			<f:formatDate value="${row.u_regdate }"
				pattern="yyyy-MM-dd hh:mm" />
		</td>
		<td>
			<a href="<c:url value='/management/memberUpdate.jsp'/>?u_num=${row.u_num}">수정</a> |
			<a href="<c:url value='/management/memberDelete.jsp'/>?u_num=${row.u_num}">삭제</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="4">
			<c:if test="${pm.prev}">
				<a href="<c:url value='/management/member.jsp'/>?selectPage=${pm.startPage-1}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}" step="1">
				<a href="<c:url value='/management/member.jsp'/>?selectPage=${i}">[${i}]</a>
			</c:forEach>
			<c:if test="${pm.next}">
				<a href="<c:url value='/management/member.jsp'/>?selectPage=${pm.endPage + 1}">[다음]</a>
			</c:if>
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	
	<!-- 등록된 회원 정보가 없을 시 출력 -->
	<tr>
		<td colspan="4">등록된 회원정보가 없습니다.</td>
	</tr>
	</c:otherwise>
</c:choose>	
</table>
</section>
<jsp:include page="/common/footer.jsp" />











