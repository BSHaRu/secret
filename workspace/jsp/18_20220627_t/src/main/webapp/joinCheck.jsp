<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, util.*"%>
<%
	request.setCharacterEncoding("UTF-8");

	/* <jsp:useBean>을 쓰면 이걸 한줄로 표현 가능  
	vo.MemberVO joinMember 
		= (vo.MemberVO)pageContext.getAttribute("joinMember");
	if(joinMember == null){
		joinMember = new vo.MemberVO();
		pageContext.setAttribute("joinMember", joinMember);
	} */
	
	/* 이게 <jsp:setProperty> 내용임
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	String phone = request.getParameter("phone");
	String gender = request.getParameter("gender");
	String strAge = request.getParameter("age");
	int age = Integer.parseInt(strAge);
	
	joinMember.setId(id);
	joinMember.setPass(pass);
	joinMember.setName(name);
	joinMember.setAddr(addr);
	joinMember.setPhone(phone);
	joinMember.setGender(gender);
	joinMember.setAge(age); */
%>
		<!-- scope는 생략가능한데 이때 default는 page임 -->
<jsp:useBean id="joinMember" class="vo.MemberVO" />
<jsp:setProperty name="joinMember" property="*" />
<%= joinMember %>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String msg = "";
	String nextPage = "";
	
	try{
		conn = JDBCUtil.getConnection();
		String sql = "SELECT id FROM test_member WHERE id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, joinMember.getId());
		rs = pstmt.executeQuery();
		if(rs.next()){
			// id 존재
			msg = "이미 존재하는 id 입니다.";
			nextPage = "join";
		}else{
			// 중복된 id 없음 
			if(pstmt != null) pstmt.close();
			// 위에서 pstmt는 select을 찾던거라서 한번 종료해주고 새로 sql를 만드는거임
			sql = "INSERT INTO test_member VALUES(null,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,joinMember.getId());
			pstmt.setString(2,joinMember.getPass());
			pstmt.setString(3,joinMember.getName());
			pstmt.setString(4,joinMember.getAddr());
			pstmt.setString(5,joinMember.getPhone());
			pstmt.setString(6,joinMember.getGender());
			pstmt.setInt(7,joinMember.getAge());
			
			if(pstmt.executeUpdate() > 0){
				msg = "회원가입 완료";
				nextPage = "login";
			}
		}
	}catch(Exception e){
		System.out.println(joinMember);
		e.printStackTrace();
		msg = "회원가입도 못하는 찐다쉨";
		nextPage = "join";
	}finally{
		JDBCUtil.close(rs,pstmt,conn);
		out.print("<script>");
		out.print("alert('"+msg+"');");
		out.print("location.href='index.jsp?page="+nextPage+"';");
		out.print("</script>");
	}
	
%>















