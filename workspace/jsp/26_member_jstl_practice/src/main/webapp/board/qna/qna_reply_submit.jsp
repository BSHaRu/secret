<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<!-- qna_reply_submit.jsp -->
<f:requestEncoding value="utf-8" />
<jsp:useBean id="qnaBoard" class="vo.QNABoardVO" /> <!-- 원본글 -->
<jsp:setProperty property="*" name="qnaBoard" />

<!-- 원본글에 달린 답글을 밀어내는 작업 -->
<s:update dataSource="jdbc/MySQLDBCP">
	UPDATE qna_board SET qna_re_seq = qna_re_seq +1
	WHERE qna_re_ref = ? AND qna_re_seq > ?
	<s:param>${qnaBoard.qna_re_ref }</s:param>
	<s:param>${qnaBoard.qna_re_seq }</s:param>
</s:update>

<!-- 원본글 밀어냈으니 그 위에 새로운 값 추가 -->
<s:update dataSource="jdbc/MySQLDBCP">
	INSERT INTO qna_board
	VALUES(null,?,?,?,?,?,?,?,0,'N',now())
	<s:param>${qnaBoard.qna_name }</s:param>
	<s:param>${qnaBoard.qna_title }</s:param>
	<s:param>${qnaBoard.qna_content }</s:param>
	<s:param>${qnaBoard.qna_re_ref }</s:param>
	<s:param>${qnaBoard.qna_re_lev +1 }</s:param>
	<s:param>${qnaBoard.qna_re_seq +1 }</s:param>
	<s:param>${qnaBoard.qna_writer_num }</s:param>
</s:update>
<c:redirect url="qna_list.jsp" />