<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl FMT</title>
</head>
<body>
	<f:requestEncoding value="utf-8"/>
	<c:if test="${!empty param.encode }">
		<c:set var="encode" value="${param.encode }" />
	</c:if>
	<f:setLocale value="${encode }"/>
	<br/>
	현재 locale : <%= response.getLocale() %>
	<br/>
	<f:bundle basename="prop/bundle">
		<f:message var="name" key="name" >
			<f:param value="${param.name }" />
		</f:message>
		<f:message var="hobby" key="hobby" >
			<f:param value="${param.hobby }" />
		</f:message>
	</f:bundle>
	
	${name } <br/>
	${hobby} <br/>
</body>
</html>