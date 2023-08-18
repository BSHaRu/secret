<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>

<jsp:useBean id="pm" class="utils.PageMaker" />
<c:if test="${!empty param.page }">
	<c:set target="${pm.cri }" property="page" value="${param.page }" />
</c:if>

<s:query var="r" dataSource="jdbc/MySQLDBCP">
	SELECT count(*) AS c FROM test_board
</s:query>
<c:set target="${pm }" property="totalCount" value="${r.rows[0].c }" />

<s:query var="rs" dataSource="jdbc/MySQLDBCP" 
	startRow="${pm.cri.startRow }" maxRows="${pm.cri.perPageNum }" >
	SELECT * FROM test_board
	ORDER by board_num DESC
</s:query>
<c:if test="${rs.rowCount > 0 }" >
	<jsp:useBean id="boardList" class="java.util.ArrayList" type="java.util.List<vo.TestBoardVO>" />
	<c:forEach var="board" items="${rs.rows }">
		<jsp:useBean id="bl" class="vo.TestBoardVO" />
		<c:set target="${bl }" property="board_num" value="${board.board_num }" />
		<c:set target="${bl }" property="board_name" value="${board.board_name }" />
		<c:set target="${bl }" property="board_pass" value="${board.board_pass }" />
		<c:set target="${bl }" property="board_title" value="${board.board_title }" />
		<c:set target="${bl }" property="board_content" value="${board.board_content }" />
		<c:set target="${bl }" property="board_readcount" value="${board.board_readcount }" />
		<c:set target="${bl }" property="board_date" value="${board.board_date }" />
		<c:set var="temp" value="${boardList.add(bl) }" />
		<c:remove var="bl" />
	</c:forEach>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 목록</h1>
	<a href="board_write.jsp">게시글 작성하러 가기</a>
	<table border=1>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성시간</th>
			<th>조회수</th>
		</tr>
		<!-- 게시글 목록 -->
		<c:choose>
			<c:when test="${!empty boardList }">
				<c:forEach var="b" items="${boardList }">
					<input type="hidden" name="board_num" value=${b.board_num } />
					<tr>
						<td>${b.board_num }</td>
						<td>
							<a href="board_detail.jsp?board_num=${b.board_num }">${b.board_title }</a>
						</td>
						<td>${b.board_name }</td>
						<td>${b.board_date }</td>
						<td>${b.board_readcount }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<tr>
				<td colspan="5">
					등록된 정보가 없습니다.
				</td>
			</tr>
			</c:otherwise>
		</c:choose>
		<!-- 페이지 블럭 작성  -->
		<tr>
			<td colspan="5" align="center" >
				<c:if test="${pm.prev }">
				<a href="board_list.jsp?page=1">[처음]</a>
				<a href="board_list.jsp?page=${pm.startPage-1 }">[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${pm.startPage }" end="${pm.endPage }">
				<c:choose>
					<c:when test="${pm.cri.page eq i }"> 
						<span style="color:red;">[${i }]</span>
					</c:when>
					<c:otherwise>
						<a href="board_list.jsp?page=${i}">[${i }]</a>
					</c:otherwise>
				</c:choose>	
			</c:forEach>
			
			<c:if test="${pm.next }">
				<a href="board_list.jsp?page=${pm.endPage+1 }">[다음]</a>
			</c:if>
			<c:if test="${pm.cri.page != pm.maxPage }">
				<a href="board_list.jsp?page=${pm.maxPage }">[마지막]</a>
			</c:if>
			</td>
		</tr>
	</table>
</body>
</html>