package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberRegServlet extends HttpServlet {
	private static final long serialVersionUID = -7667778281083375291L;

	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberRegServlet do Post 호출");
		
		req.setCharacterEncoding("UTF-8"); // 이거 지정 안하면 한글은 깨져서 출력됨
		
		String name = req.getParameter("name");
		String addr = req.getParameter("addr");
		String mobile = req.getParameter("mobile");
		String hobby = req.getParameter("hobby");
		
		System.out.println("name : " + name);
		System.out.println("addr : " + addr);
		System.out.println("mobile : " + mobile);
		System.out.println("hobby : " + hobby);
		System.out.println();
		
		System.out.println("응답(response)");
		
		// text타입의 html파일이고, utf-8로 인코딩 같이함.
		resp.setContentType("text/html;charset=utf-8"); 
		
		PrintWriter pw = resp.getWriter();
		pw.println("이름 : " + name+"<br/>");
		pw.println("주소 : " + addr+"<br/>");
		pw.println("번호 : " + mobile+"<br/>");
		pw.println("취미 : " + hobby+"<br/>");
		pw.println("<a href='index.html'>메인으로</a>");
		pw.println("<script>");
		pw.println("alert('memReg에서 알림!')");
		pw.println("</script>");
	}

	
}
