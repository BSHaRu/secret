<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, util.*" %>
 <!-- header.jsp -->
 <%
 	Connection conn = null;
 	PreparedStatement pstmt = null;
 	ResultSet rs = null;
 
 	Cookie[] cookies = request.getCookies();
 	if(cookies != null){
 		for(Cookie c : cookies){
 			if(c.getName().equals("id")){
 				String id = c.getValue();
 				conn = JDBCUtil.getConnection();
 				String sql = "SELECT * FROM test_member WHERE id = ? ";
 				pstmt = conn.prepareStatement(sql);
 				pstmt.setString(1, id);
 				
 				rs = pstmt.executeQuery();
 				if(rs.next()){
 					vo.MemberVO vo = new vo.MemberVO();
 					vo.setNum(rs.getInt(1));
 					vo.setId(rs.getString(2));
 					vo.setPass(rs.getString(3));
 					vo.setName(rs.getString(4));
 					vo.setAddr(rs.getString(5));
 					vo.setPhone(rs.getString(6));
 					vo.setGender(rs.getString(7));
 					vo.setAge(rs.getInt(8));
 					session.setAttribute("loginMember", vo);
 				}
 			} // if > for > if end
 		}
 	} // if end
 	JDBCUtil.close(rs,pstmt,conn);
 %>
 
 <jsp:useBean id="loginMember" class="vo.MemberVO" scope="session" />
 
<ul>
 	<li><a href="index.jsp">home</a></li>
 	<% if(loginMember.getName() == null){ %>
	<li><a href="index.jsp?page=login">로그인</a></li>
 	<li><a href="index.jsp?page=join">회원가입</a></li>
 	<%}else{ %>
 	<li>
 		<!-- info -->
 		<a href="index.jsp?page=info">
 		<jsp:getProperty property="name" name="loginMember"/>
 		</a>님 ㅎㅇ염
 	</li>
 	<% if(loginMember.getId().equals("admin")){%>
	<li>
		<a href="index.jsp?page=memberList">회원관리</a>
	</li>
	<%}%>
 	<li>
 		<a href="logout.jsp">로그아웃</a>
 	</li>
 	<%} %>
 </ul>
 
 
 
 
 
 
 
 
 