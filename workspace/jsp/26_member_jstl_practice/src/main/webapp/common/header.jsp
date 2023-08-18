<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Member</title>
<c:url var="context" value="/"/> <%-- 절대 경로로 지정한거 --%>
<link href="${context}/css/header.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/footer.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- cookie 정보 가져오기 -->
	<%-- <c:set var="req" value="<%=request %>" />
	<c:set var="cookies" value="${req.cookies }" />
	${cookies } <br/> cookie에 뭐있는지 그냥 확인하는거
	
	<c:if test="${!empty cookies }"> cookie정보가 있을때
		<c:forEach var="c" items="${cookies }">
			${c.name } - ${c.value } <br/>
			${c.getName()} - ${c.getValue()} <br/> 한거랑 동일		
		</c:forEach>
	</c:if> 
	--%>
	
	<%-- request 쿠키 정보들 중에 name이 u_id인 쿠키 정보 --%>
	<%-- <br/> MyCookie : u_id 쿠키 : ${cookie.u_id }
	<br/> JSESSIONID 쿠키 : ${cookie.JSESSIONID }
	<br/> u_id name : ${cookie.u_id.name }
	<br/> u_id value : ${cookie.u_id.value }
	<br/> [u_id][value] : ${cookie.['u_id']['value'] } 
	--%>
	
	<%-- 해당 쿠키가 존재하면 브라우저 종료해도 쿠키를 유지해서 자동로그인 시켜주는거임 --%>
	<c:if test="${!empty cookie.u_id and empty sessionScope.member}">
		<s:query var="rs" dataSource="jdbc/MySQLDBCP">
			SELECT * FROM big_member WHERE u_id = ? 
			<s:param>${cookie.u_id.value}</s:param>
		</s:query>
		<c:if test="${rs.rowCount > 0}">
			<jsp:useBean id="member" class="vo.MemberVO" scope="session"/>
			<c:forEach var="columnName" items="${rs.columnNames}">
				<%-- ${columnName} - ${rs.rows[0][columnName]} <br/> --%> 
								<%-- 필드이름 == column명이랑 동일해서 property에 ${columnName }넣는거임 --%>
				<c:set target="${member}" property="${columnName}" value="${rs.rows[0][columnName]}" />
			</c:forEach>
			<%-- <br/> ${member} --%>
		</c:if>
	</c:if>
	
	<header>
		<div>
			<ul>
				<li><a href="<c:url value='/index.jsp'/>">home</a></li>
				<c:choose>
					<c:when test="${!empty sessionScope.member}">
						<li><a href="<c:url value='/member/info.jsp'/>">${member.u_id}</a>님 방가</li>
						<c:if test="${member.u_id eq 'admin'}">
							<li><a href="<c:url value='/management/member.jsp'/>">회원관리</a></li>
						</c:if>
						<li><a href="<c:url value='/member/logOut.jsp'/>">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="<c:url value='/member/login.jsp'/>">로그인</a></li>
						<li><a href="<c:url value='/member/join.jsp'/>">회원가입</a></li>
					</c:otherwise>
				</c:choose>
				
			</ul>
		</div>
		<div>
			<ul>
				<li><a href="${context }/board/notice/notice_list.jsp">공지사항</a></li>
				<li><a href="${context }/board/qna/qna_list.jsp">질문과답변</a></li>
			</ul>
		</div>
	</header>