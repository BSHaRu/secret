<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Naver cookie</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		// 클라이언트 브라우저에 저장된 cookie 항목
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c : cookies){
				System.out.println("name : " + c.getName()); 	// key 값
				System.out.println("value : " + c.getValue());
				System.out.println("domain : " + c.getDomain()); // 보안때문에 name이랑 value만 전달함 
			}
		}
		
		/*
		// cookie 생성
		Cookie cookie = new Cookie("target","LEE"); // key(name), value
		// cookie 유지 시간 - second
		cookie.setMaxAge(60*60*24*15); // 60초 * 60(1시간) * 24(24시간) * 15(15일)
		cookie.setPath("/");	// root 경로를 말하는거임
		// cookie.setDomain("127.0.0.1");
		response.addCookie(cookie);
		*/
		
		// cookie 삭제
		Cookie cookie = new Cookie("target","");
		cookie.setMaxAge(0);
		cookie.setPath("/"); 
		// 위에까지 동일해야 삭제가 가능함
		response.addCookie(cookie);
		
		response.sendRedirect("member/index.jsp");
	%>
</body>
</html>