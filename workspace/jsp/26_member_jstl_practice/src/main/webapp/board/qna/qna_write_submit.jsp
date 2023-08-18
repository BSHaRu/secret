<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<!-- qna_write_submit.jsp -->
<f:requestEncoding value="utf-8" />
<jsp:useBean id="qnaBoard" class="vo.QNABoardVO" />
<jsp:setProperty property="*" name="qnaBoard" />

<s:update dataSource="jdbc/MySQLDBCP"><%-- 글번호, '작성자, 제목, 내용', 원본글, 답변 뷰, 답변 글, '작성자 번호', 조회수, 시간 --%>
	INSERT INTO qna_board VALUES(null,?,?,?,0,0,0,?,0,'N',now()) 
	<s:param value="${qnaBoard.qna_name }" />
	<s:param value="${qnaBoard.qna_title }" />
	<s:param value="${qnaBoard.qna_content }" />
	<s:param value="${qnaBoard.qna_writer_num }" />
</s:update>
<s:update dataSource="jdbc/MySQLDBCP"> 
	UPDATE qna_board SET qna_re_ref = LAST_INSERT_ID()
	WHERE qna_num = LAST_INSERT_ID();
</s:update>
<c:redirect url="qna_list.jsp" />