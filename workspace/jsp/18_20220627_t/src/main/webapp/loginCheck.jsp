<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="util.*, java.sql.*" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="loginMember" class="vo.MemberVO" />
<jsp:setProperty property="*" name="loginMember"/>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String msg = "";
	String nextPage = "";
	
	conn = JDBCUtil.getConnection();
	String sql = "SELECT * FROM test_member WHERE id = ? AND pass = ?";
	
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getId());
		pstmt.setString(2, loginMember.getPass());
		
		rs = pstmt.executeQuery();
		if(rs.next()){
			// 검색 결과 존재 - 인증 완료
			loginMember.setNum(rs.getInt("num"));
			loginMember.setName(rs.getString("name"));
			loginMember.setAddr(rs.getString("addr"));
			loginMember.setPhone(rs.getString("phone"));
			loginMember.setGender(rs.getString("gender"));
			loginMember.setAge(rs.getInt("age"));
			session.setAttribute("loginMember", loginMember);
			msg = "로그인 성공";
			nextPage = "default";
			
			String login = request.getParameter("login");
			if(login != null){
				// 자동로그인 요청
				Cookie cookie = new Cookie("id", loginMember.getId());
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
			}
			
		}else{
			// 검색 결과 없음 - 인증 실패
			msg = "로그인 실패";
			nextPage = "login";
		}
	}catch(Exception e){
		e.printStackTrace();
		msg = "로그인 실패";
		nextPage = "login";
	}finally{
		JDBCUtil.close(rs,pstmt,conn);
		out.print("<script>");
		out.print("alert('"+msg+"');");
		out.print("location.href='index.jsp?page="+nextPage+"';");
		out.print("</script>");
	}
%>
