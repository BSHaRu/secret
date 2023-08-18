<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, vo.*"%>
<!-- user/loginCheck.jsp -->
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- List<MemberVO> member = new java.util.ArrayList(); -->
<!-- class에는 제네릭을 쓸 수 없어서 type을 지정해주는거임 -->
<jsp:useBean id="memberList" class="java.util.ArrayList" type="List<MemberVO>" scope="application" />

<jsp:useBean id="loginMember" class="vo.MemberVO" />
<jsp:setProperty name="loginMember" property="*" />

<%
	int index = memberList.indexOf(loginMember);
	MemberVO sessionMember = memberList.get(index);
	if(sessionMember != null
			&& loginMember.getPass().equals(sessionMember.getId())){
		// 로그인
		String rememberme = request.getParameter("rememberme");
		if(rememberme != null){
			Cookie cookie = new Cookie("id",loginMember.getId());
			cookie.setMaxAge(60*60);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		session.setAttribute("loginMember", sessionMember);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}else{
		// 로그인 실패
		out.print("<script>alert('로그인 실패'); history.go(-1); </script>");
		/* 이걸 위에 한줄로 다 쓴거임
		out.print("<script>");
		out.print("alert('로그인 실패');");
		out.print("history.go(-1);"); // 이전페이지로 이동
		out.print("</script>"); 
		*/
	}
	
%>