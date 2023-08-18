<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, vo.*" %>
<!-- user/joinCheck.jsp -->
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- List<MemberVO> memberList = new java.util.ArrayList(); -->
<!-- class에는 제네릭을 쓸 수 없어서 type을 지정해주는거임 -->
<jsp:useBean id="memberList" class="java.util.ArrayList" type="List<MemberVO>" scope="application" />

<jsp:useBean id="joinMember" class="vo.MemberVO" />
<jsp:setProperty name="joinMember" property="*" />
<%
	if(memberList.contains(joinMember)){
		// 아이디 중복
		out.print("<script>");
		out.print("alert('이미 사ㅏㅏ요용요용ㅈ우ㅜ우웅 ㅣㅁㄴ아ㅏ앙디디ㅣ');");
		out.print("history.back();");
		out.print("</script>");
	}else{
		// 회원가입
		memberList.add(joinMember);
		
		String path = request.getContextPath(); // 이게 없으면 user 폴더 안에 있는 경로를 찾기때문에
		out.print("<script>");					// -> contextPath를 쓸 줄 알아야됨
		out.print("alert('회원가입 서ㅓㅓㅓㅓㅇ고ㅗㅗㅗㅇ');");
		out.print("location.href='"+path+"/index.jsp?page=login'");
		out.print("</script>");
	}
%>