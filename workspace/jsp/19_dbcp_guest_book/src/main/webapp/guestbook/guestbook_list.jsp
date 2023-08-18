<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// locahost:8000/ => root 경로
	// 19_dbcp_guest_book -> context 경로
	String path = request.getContextPath(); // 절대경로 추가
	// index 실행시 index를 보여주는게 아니라 해당 list를 보여주게 설정했기때문에
	// 절대경로를 설정 안하면 페이지 이동시 주소가 이상하게 꼬일 수 있음.
	// -> guestbook/guestbook/guestbook_list.jsp"
%>    
<!-- guestbook_list.jsp -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 메세지 목록</title>
</head>
<body>
	<!-- 방명록 작성 전달을 위한 form tag -->
	<form action="<%=path %>/guestbook/guestbook_writer.jsp" method="post">
		<table>
			<tr>
				<td colspan="3"><h1>방명록 작성</h1></td>
			</tr>
			<tr>
				<td colspan="2"> </td>
				<td rowspan="4">
					<input style="margin-left:20px; width:100%; height:100px;" type="submit" value="메세지 남기기" />
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input name="guestName" type="text" style="width:100%;" />
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input name="password" type="password" style="width:100%;" />
				</td>
			</tr>
			<tr>
				<td>메세지</td>
				<td>
					<textarea name="message" cols="35" rows="3"></textarea>
				</td>
			</tr>
		</table>
	</form>
	<hr/>
<%@ page import="java.sql.*, util.*" %>
<%@ page import="java.util.*, vo.*" %>

<%
	// 페이징 처리
	int currentPage = 1; // 요청 들어온 현재 페이지 번호 -> 가장 최신 페이지 보여주기위해서 1로 지정함
	int pageCount = 5;	 // 한 페이지에 보여줄 게시물 수 -> 1페이지에 5개씩 보여줄라고 그냥 지정
	int startRow = 0; 	// 테이블에서 pageCount만큼 검색할 시작 인덱스
	int endRow = 0;		// 테이블에서 검색할 게시물 수 -> pageCount랑 개수가 똑같을꺼임
	
	String paramPage = request.getParameter("page");
	if(paramPage != null){
		currentPage = Integer.parseInt(paramPage);
	}
	out.print("현재 요청 페이지 : " + currentPage +"<br/>");
	
	// 검색할 방명록 게시물 시작 인덱스 번호
	startRow = (currentPage-1) * pageCount; // sql에서 limit 구하는 공식임
	// 검색할 방명록 게시물 개수
	endRow = pageCount;

	Connection conn = DBCPUtil.getConnction();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ArrayList<GuestBook> bookList = new ArrayList<>();
	
	// 게시물 검색 쿼리
	String sql = "SELECT * FROM test_guestbook ORDER by id DESC limit ?, ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, startRow);
	pstmt.setInt(2, endRow);
	
	rs = pstmt.executeQuery();
	while(rs.next()){
		GuestBook vo = new GuestBook(
			rs.getInt(1),
			rs.getString(2),
			rs.getString(3),
			rs.getString(4)
		); // 이게 vo에 생성자 재정의 해서 가능한거임
		bookList.add(vo);
	}
	DBCPUtil.close(rs,pstmt);
	//--------------------------------------------------------
	// 페이징 블럭 처리(게시물 번호 표시해주는거)
	sql = "SELECT count(id) FROM test_guestbook"; 
	pstmt = conn.prepareStatement(sql);
	
	rs = pstmt.executeQuery();
	
	int listCount = 0; 	 	// 전체 게시물 수
	int startPage = 0; 	 	// 현재 페이지 블럭에 보여줄 시작 페이지 번호
	int endPage = 0;   		// 현재 페이지 블럭에 보여줄 마지막 페이지 번호
	int maxPage = 0;  	 	// 전체 출력가능한 페이지 블럭 개수
	int displayPageNum = 5; // 한번에 보여줄 페이지 블럭 개수 
	// -> [1][2][3][4][5] -> [6][7][8][9][10] 이런식으로 5개씩 끊어서 보여주겠다는거 지정하는거임
	
	if(rs.next()){
		listCount = rs.getInt(1);
	}
	maxPage = ((listCount -1) / pageCount) +1; // 공식이니깐 이해 안되면 걍 외우셈
	// ex) 전체 게시물 개수(listCount)가 51 , pageCount가 5개 라고 했을 때
	// 51 / 5 = 10.1 -> 여기서 10개로 나누고 1개가 남으니 +1을 해줘야 된다.
	// 근데 전체 게시물이 50개라면 50 / 5 = 10이니 여기엔 +1을 해주면 안되기때문에
	// 50-1 / 5 = 9.x 가 되고 결국 +1해주면 10개가 다 보여짐
	// => 전체 게시물 개수 -1 / 보여줄 페이지 블럭 개수 +1 을 해주는거임
	out.print("전체 페이지 블럭 수 : " + maxPage);
	
	// displayPageNum = 5
	// [1][2][3][4][5] -> [6][7][8][9][10]...
	// 처음페이지번호 = ((요청페이지 -1) / 페이지 블럭 개수) * 페이지 블럭 개수 +1
	startPage = (currentPage - 1) / displayPageNum * displayPageNum + 1; // 이것도 이해 안되면 걍 외우셈
	// ex) (1 - 1) / 5 = 0, 0 * 5 + 1 = 1 즉, 젤 처음에 1부터 보여진다.
	// (5 - 1) / 5 = 0.x 결국 0이니깐 이하 계산하면 1이 되니 그대로 1이 보여짐
	// (6 - 1) / 5 = 1, 1 * 5 + 1 = 6 // 여기서부터는 6이 젤 처음에 보여지게 됨
	// ((11 -1) / 5) * 5 + 1 = 11 ...
	// -> 요청 페이지 1~5는 처음에 보여주고 6~10까지는 다음에 보여줌.
	
	//		= 처음페이지 번호 + 블럭개수 - 1
	endPage = startPage + (displayPageNum - 1); // 이것도 이해 안되면 걍 외우셈
	// currentPage가 1~5까지는 startPage의 값이 1이다.
	// -> 1 + 5 - 1 = 5 즉, 1~5일 경우 마지막 번호는 5가 된다.
	// => currentPage가 6~10이면 startPage가 2 즉, 마지막 번호는 6 + 5 -1 = 10이됨
	
	// listCount , pageCount, displayPageNum이 모두 위와 같다고 가정하면
	// 전체 게시물 개수는 51갠데, 1페이지에 5개씩 보여주고 5개로 묶어 버리면
	// 결국 10페이지가 생기고 마지막 페이지에는 게시글이 1개가 생성된다.
	// 이때, 5개씩 페이지를 보여주기때문에 11~15페이지가 생기는데
	// 12~15페이지는 내용도 없는데 페이지만 표시 해주니깐 그걸 없애기 위해서
	// 해당 if문을 쓰는거임
	if(endPage > maxPage){
		endPage = maxPage;
	}
	DBCPUtil.close(rs,pstmt,conn);
%>
	<h1>방명록 리스트</h1>
	<%if(!bookList.isEmpty()) {%>
		<table border="1" cellspacing="0" cellpadding="10">
			<% for(GuestBook gb : bookList){ %>
				<tr>
					<td>
						메세지 번호 : <%=gb.getId() %> <br/>
						손님 이름 : <%=gb.getGuestName() %> <br/>
						메세지 : <%=gb.getMessage() %> <br/>
						<a href="<%=path %>/guestbook/guestbook_confirm.jsp?id=<%=gb.getId()%>">[삭제]</a>
					</td>
				</tr>
			<%} %>
		</table>
	<%}else {%>
		<h2>등록된 메세지가 없습니다.</h2>
	<%} %>
	<hr/>
	<!-- 게시물 보여주고나서 번호 누르면 다음 게시글 보여주는 그런거 만드는거니 이해 안되면 외우셈 -->
	<% if(startPage != 1){%>
		<a href="<%=path %>/guestbook/guestbook_list.jsp?page=1">[처음]</a>
		<a href="<%=path %>/guestbook/guestbook_list.jsp?page=<%=startPage-1%>">[이전]</a>
	<%} %>
	
	<% for(int i=startPage; i<=endPage; i++){ %>
		<a href="<%=path %>/guestbook/guestbook_list.jsp?page=<%=i%>">[<%=i%>]</a>
	<%}	%>
	
	<%if(endPage < maxPage){%>
		<a href="<%=path %>/guestbook/guestbook_list.jsp?page=<%=endPage+1 %>">[다음]</a>
		<a href="<%=path %>/guestbook/guestbook_list.jsp?page=<%=maxPage%>">[마지막]</a>
	<%} %>
</body>
</html>