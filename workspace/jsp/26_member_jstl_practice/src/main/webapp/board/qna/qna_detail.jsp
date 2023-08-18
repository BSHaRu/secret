<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<!-- qna_detail.jsp -->
<jsp:include page="../../common/header.jsp" />
<%@ include file="qna_select.jsp" %>

<!-- 조회수(작성자 != 로그인이 다를 경우 조회수 증가) -->
<c:if test="${!empty member and member.u_num ne qna.qna_writer_num }">
	<s:update dataSource="jdbc/MySQLDBCP">
		UPDATE qna_board SET qna_readcount = qna_readcount +1
		WHERE qna_num = ?
		<s:param>${qna.qna_num }</s:param>
	</s:update>
</c:if>

<section class="wrap">
	<table>
		<tr>
			<th colspan="2">
				<h1>게시글 상세보기</h1>
			</th>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${qna.qna_name }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${qna.qna_title }</td>
		</tr>
		<tr>
			<td>내용</td><%-- <c:out>으로 감싸줘야 해당 내용안에 글을 작성할 때 장난 못침 --%>
			<td><c:out value="${qna.qna_content }" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<c:if test="${!empty member }"> <%-- 로그인이 되어있을 때 --%>
					<a href="qna_reply.jsp?qna_num=${qna.qna_num }">[답글]</a>
					<c:if test="${qna.qna_writer_num eq member.u_num }">
						<a href="qna_update.jsp?qna_num=${qna.qna_num }">[수정]</a>
						<a href="qna_delete.jsp?qna_num=${qna.qna_num }">[삭제]</a>
					</c:if>
				</c:if>
				<a href="qna_list.jsp">[목록]</a>
			</td>
		</tr>
	</table>
</section>
<jsp:include page="../../common/footer.jsp" />