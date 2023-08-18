<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="connection.jsp" %>      
<%
	request.setCharacterEncoding("UTF-8");
	String mNum = request.getParameter("num");
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	
	try{
		String sql = "UPDATE member SET name = ?, addr = ? WHERE num = ? ";
		int num = Integer.parseInt(mNum);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,name);
		pstmt.setString(2,addr);
		pstmt.setInt(3,num);
		int result = pstmt.executeUpdate();
		
		out.print("<script>");
		out.print("alert('"+result+"개의 행 수정완료');");
		out.print("location.href='memberList.jsp';");
		out.print("</script>");
		
		pstmt.close();
		conn.close();
	}catch(Exception e){
		out.print("<script>");
		out.print("alert('수정 실패');");
		out.print("history.back();");
		out.print("</script>");
	}finally{
		
	}
%>