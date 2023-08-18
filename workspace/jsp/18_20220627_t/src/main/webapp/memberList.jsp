<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="checkAdmin.jsp" %>    
<%@ page import="java.util.*, util.*, vo.*, java.sql.* " %>    
<!-- Connection Pool
	context.xml참고 - META-INF 폴더에 있음
	DBCPUtil.java 참고 - util폴더에 있음
 -->
<!-- 회원 목록 -->
<%
	// JNDI(Java Naming and Directory Interface)
	// -> 이름을 가지고 객체를 얻을 수 있는 친구
	// context에서 제공하는 데이터 및 객체를 발견(discover)하고 참고(lookup)하기 위한 자바 API
	// 둘 다 javax.naming 패키지에 있음
	javax.naming.Context init
		= new javax.naming.InitialContext();
		
	// DataSource가 Connection pool 관리하는 객체
	javax.sql.DataSource ds					// env : 외부에서 접근하는 응용프로그램
		= (javax.sql.DataSource)init.lookup("java:comp/env/java/MySqlDB");
							//-> java:comp/env까진 동일하고, java/MySqlDB는 context의 name을 말함
	java.sql.Connection conn = ds.getConnection();
	System.out.println(conn);
	
	ArrayList<MemberVO> memberList = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try{
		stmt = conn.createStatement();
		String sql = "SELECT * FROM test_member ORDER by num DESC";
		
		rs = stmt.executeQuery(sql);
		memberList = new ArrayList<>();
		while(rs.next()){
			MemberVO m = new MemberVO();
			m.setNum(rs.getInt(1));
			m.setId(rs.getString(2));
			m.setPass(rs.getString(3));
			m.setName(rs.getString(4));
			m.setAddr(rs.getString(5));
			m.setPhone(rs.getString(6));
			m.setGender(rs.getString(7));
			m.setAge(rs.getInt(8));
			memberList.add(m);
		}
	}catch(Exception e){
		e.printStackTrace();		
	}finally{
		DBCPUtil.close(rs,stmt,conn);
	}
%>

<table border="1">
	<tr>
		<th colspan="7"><h1>회원목록</h1></th>
	</tr>
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>주소</th>
		<th>전화번호</th>
		<th>성별</th>
		<th>나이</th>
	</tr>
	<%for(MemberVO m : memberList){ %>
										<!-- &num이하를 추가하면서 어떤 회원번호를 수정 / 삭제 할껀지 찾음 -->
		<tr onclick="location.href='index.jsp?page=memberInfo&num=<%=m.getNum()%>';">
			<td><%=m.getNum() %></td>
			<td><%=m.getId() %></td>
			<td><%=m.getName() %></td>
			<td><%=m.getAddr() %></td>
			<td><%=m.getPhone() %></td>
			<td><%=m.getGender() %></td>
			<td><%=m.getAge() %></td>
		</tr>
	<%} %>
</table>

