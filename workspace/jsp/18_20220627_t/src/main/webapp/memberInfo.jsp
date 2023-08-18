<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- memberInfo.jsp -->
<%@ include file="checkAdmin.jsp" %>
<%@ page import="java.sql.*, util.*, vo.*" %>
<%
	String num = request.getParameter("num");
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	MemberVO vo = null;
	
	try{
		conn = DBCPUtil.getConnection();
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(
			"SELECT id,name,addr FROM test_member WHERE num = " +num		
		);
		if(rs.next()){
			vo = new MemberVO();
			vo.setId(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setAddr(rs.getString(3));
			vo.setNum(Integer.parseInt(num));
		}else{
			out.print("<script>");
			out.print("alert('입력한 정보의 회원은 존재 안함')");
			out.print("history.back();");
			out.print("</script>");
		}
	}catch(Exception e){
		e.printStackTrace();		
	}finally{
		DBCPUtil.close(rs,stmt,conn);
	}
	// out.print(vo);
%>

<table>
	<tr>
		<th colspan="2">
			<%=vo.getNum() %>번 회원의 정보
		</th>
	</tr>
	<tr>
		<td>아이디</td>
		<td><%=vo.getId() %></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><%=vo.getName() %></td>
	</tr>
	<tr>
		<td>주소</td>
		<td><%=vo.getAddr() %></td>
	</tr>
	<tr>
		<th colspan="2">
			<a href="javascript:memberUpdate('<%=vo.getNum() %>');">수정</a>
				
			<a href="javascript:memberDelete('<%=vo.getNum() %>','<%=vo.getId() %>');">삭제</a>
		</th>
	</tr>
</table>

<script>
	function memberUpdate(num){
		location.href='index.jsp?page=memberUpdateForm&num='+num;
	}
	
	function memberDelete(num, id){
		if(id == 'admin'){
			alert('관리자 계정은 삭제 못함');
		}else{
			if(confirm(num+"회원 정보를 정말로 삭제 하시겠습니까?")){
				location.href='memberDelete.jsp?num='+num;
			}
		}
	}
</script>