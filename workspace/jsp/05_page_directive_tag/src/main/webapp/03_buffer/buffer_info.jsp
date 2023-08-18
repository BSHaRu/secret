<!-- buffer="8kb" autoFlush="true" 이게 디폴트 값이긴함 -->
<!-- 이건 진짜 앵간하면 안건드는 친구들이긴함 -->
<%@ page buffer="1kb" autoFlush="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03_buffer/buffer_info.jsp</title>
</head>
<body>
	<!-- 전체 버퍼 크기 -->
	Buffer size = <%= out.getBufferSize() %> <br/>
	<!-- 버퍼에 남은 크기, 잔존하는 크기 -->
	Remaining size = <%= out.getRemaining() %> <br/>
	<%
		for(int i=0; i<1000; i++){
			out.println("   1234   ");
		}
	%>
</body>
</html>