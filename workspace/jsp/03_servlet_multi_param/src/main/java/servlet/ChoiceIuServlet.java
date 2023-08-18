package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/choiceIu")
public class ChoiceIuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ChoiceIuServlet post 요청");
		
		String[] ius = request.getParameterValues("iu");
		System.out.println(Arrays.toString(ius)); // Arrays.toString(값) : 값을 보여줌
		
		// 응답(해당 값) 출력
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYEP html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8' />");
		out.println("</head>");
		out.println("<body>");
		for(String iu : ius) {
			out.println(iu+"<br/>");
			out.println("<img src ='images/"+iu+"'/><br/>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
