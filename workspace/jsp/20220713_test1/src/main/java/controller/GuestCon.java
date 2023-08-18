package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.gc")
public class GuestCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String requestPath = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestPath.substring(contextPath.length()+1);
		
		String view = "";
		// 방명록 쓰기
		if(command.equals("write.gc")) {
			view = "/guestbook/guestbook_write.jsp";
		}
		
		// 목록 보기
		if(command.equals("list.gc")) {
			view = "/guestbook/guestbook_list.jsp";
		}
		
		// 삭제하기
		if(command.equals("confirm.gc")) {
			view = "/guestbook/guestbook_confirm.jsp";
		}
		if(command.equals("delete.gc")) {
			view = "/guestbook/guestbook_delete.jsp";
		}
		
		
		if(view != null && !view.equals("")) {
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
