package first;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8591970279891007344L;

	/* POST만 쓸꺼면 이건 주석처리 해도 됨
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Get 요청"); // req : 입력 | resp : 출력 이라고 생각하면 편함 
	}									// -> 무조건 string타입임
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST 요청");
		String uid = req.getParameter("uid"); // (해당 이름을 받으면됨)
		String upw = req.getParameter("upw"); // -> login에 있는 name 값
		String ip = req.getRemoteAddr();	// ip를 IPv4가 아닌 IPv6를 가져옴 
		System.out.println(ip);				// -> IPv4로 가져올라면 톰캣서버 설정 건드리면됨
		System.out.println(req.getMethod());
		
		// response
		resp.setContentType("text/html"); // 문서인데 html 타입이다.(대분류 / 소분류)
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset='utf-8' />");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>아이디는 : "+uid+"</h1>");
		out.print("<h1>비밀번호는 : "+upw+"</h1>");
		out.print("</body>");
		out.print("</html>");
	}

	
	
}
