<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<!-- qna_update.jsp -->
<jsp:include page="../../common/header.jsp" />
<section class="wrap">
<%-- 검색할 내용 가져오기 --%>
<%@ include file="qna_select.jsp" %>	
<%-- include 함으로써 QNABoardVO == ${qna} --%>
	<form action="qna_update_submit.jsp" method="post">
		<input type="hidden" name="qna_num" value="${qna.qna_num }" />
		<table>
			<tr>
				<th colspan="2"><h1>게시글 수정</h1></th>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" name="qna_name" value="${qna.qna_name }" required />
				</td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td>
					<input type="text" name="qna_title" value="${qna.qna_title }" required />
				</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>
					<textarea name="qna_content" cols="50" rows="10" required>${qna.qna_content }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="작성완료" />
					<input type="reset" value="다시쓰기" />
				</td>
			</tr>
		</table>
	</form>
</section>
<jsp:include page="../../common/footer.jsp" />