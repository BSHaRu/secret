package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/test") - web.xml에 servlet 등록한거랑 똑같음
//						-> 어노테이션으로 등록시키던 web.xml에 등록시키던 상관은 없지만,
// 						 요청경로는 동일하면 "무조건" 오류발생
//						=> 둘 다 등록시켜도 되긴하지만, 요청경로는 무조건 다르게 지정해야됨
public class TestServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test doGet 호출");
		String name = request.getParameter("name");
		System.out.println("Get param : " +name);
		response.addHeader("Content-Type", "text/html;charset=utf-8"); // Content 타입에, text형식을 html로 지정하고, charset은 utf-8로 지정한다 
		PrintWriter out = response.getWriter();						//-> jsp에서 contentType="text/html; charset=UTF-8" 이거 한거임
		out.print("<!DOCTYPE html");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset='utf-8' />");
		out.print("<title>Test Page</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>name : "+name+"</h1>");
		out.print("</body>");
		out.print("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test doPost 호출");
		String name = request.getParameter("name");
		System.out.println("Post param : " + name);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>");
		out.print("name : " + name);
		out.print("</h1>");
		System.out.println("test post 종료");
	}

}
