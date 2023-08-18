<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- application 영역에 등록된 회원 정보 -->
	<jsp:useBean id = "joinMember" class="beans.MemberBean" scope="application" />
	<!-- session 영역에 등록된 회원 정보 -->
	<jsp:useBean id = "loginMember" class="beans.MemberBean" scope="session" />    
	<%
		request.setCharacterEncoding("UTF-8");
		Cookie[] cookies = request.getCookies();
		// client에서 전달된 cookie의 정보가 존재 하고 
		// 등록된 회원정보가 존재(가입된 정보가 있을 시)
		// 로그인 된 정보가 존재하지 않을 때 cookie에서 자동로그인 정보 읽어옴
		if(cookies != null 
				&& joinMember.getId() != null 
				&& loginMember.getId() == null){
			for(int i=0; i<cookies.length; i++){
				System.out.println(cookies[i].getName()+" : "+cookies[i].getValue());
				// 자동 로그인 사용자에게 저장된 cookie == id
				// id Name값을 가진 cookie가 존재 하고
				// 등록된 회원 id와 일치 할 시 자동 로그인 처리
				if(cookies[i].getName().equals("id")	// 쿠키의 id값이 "id" 인지 비교
						&& cookies[i].getValue().equals(joinMember.getId())){ // 쿠키의 id값을 비교
					// session에 로그인 정보를 유지할 Member 정보 추가
					session.setAttribute("loginMember",joinMember);
					// useBean의 loginMember 정보 갱신
					loginMember = joinMember;
				}
			} // for end
		}
	%>
	<h1>Index JSP</h1>
	<%
		String name = loginMember.getName();
		if(name != null){
			// 로그인 완료된 상태
	%>
		<%=name %>님 ㅎㅇ | <a href="logOut.jsp">로그아웃</a>	
	<%	}else {
			// 로그인 안된 상태
	%>
		<a href="login.jsp">로그인</a> | <a href="join.jsp">회원가입</a>
	<%		
		}
	%>
</body>
</html>