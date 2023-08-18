<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>beansTest.jsp</title>
</head>
<body>
	<h1>Beans Test</h1>
	<%
		test.Member m = null;
		if(pageContext.findAttribute("m") == null){
			m = new test.Member();
			System.out.println(m.getName());
			m.setName("구구구");
			pageContext.setAttribute("m",m.getName());
			
			/* pageContext.setAttribute이걸 잠시 풀어 보면
			class PageContext {						 	 // 클래스가 있고
				private Map<String, Object> pageContext; // 필드로 선언하고
				
				PageContext(){							// 생성자가 있다치고
					pageContext = new HashMap~~~;	
				}
				public void setAttribute(String str, Object obj){	// 이렇게 받는다(str == "m" | obj == "m.getName()")	
					pageContext.put(str,obj);
				}
			}
			 */
		}else{
			m = (test.Member)pageContext.findAttribute("m");
		}
	%>
	<%= pageContext.getAttribute("m") %>
	
	<!-- 아래 한줄이 위에 있는 줄이랑 똑같은거래 -->
	<jsp:useBean id="aaa" class="test.Member" scope="page" />
	
	<jsp:useBean id="bbb" class="test.Member" scope="request" />
	<jsp:useBean id="ccc" class="test.Member" scope="session" />
	<jsp:useBean id="ddd" class="test.Member" scope="application" />
	
	<jsp:setProperty property="name" name="aaa" value="고길동" />
	
	<h2><%=aaa.getName() %></h2>
	<%
		test.Member member 
			= (test.Member)pageContext.getAttribute("aaa");
		out.println(member.getName()+"<br/>");
	%>
	
	<h3>
		<jsp:getProperty property="name" name="ddd" /><br/>
		<!-- getProperty를 안보이는 이유는 아래처럼 el식으로 간단하게 쓸 수 있기 때문이다. -->
		${ddd.name}
	</h3>
</body>
</html>