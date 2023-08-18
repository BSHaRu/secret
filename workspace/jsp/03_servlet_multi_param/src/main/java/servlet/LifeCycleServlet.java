package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lifeCycle")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifeCycleServlet() {
        System.out.println("LifeCycleServlet 생성자 호출");
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet 설정 정보(init 호출)");
		String contextPath = config.getServletContext().getContextPath();
		System.out.println("contextPath : " + contextPath);
	}

	// class 변경 전에 자원 해제 할때 호출
	public void destroy() {
		System.out.println("destroy");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Service 호출");
		String method = request.getMethod();
		System.out.println("전송 방식 : " + method);
		if(method.equalsIgnoreCase("GET")) { // equalsIgnoreCase : 대소문자 구분없이 일치하면
			doGet(request, response);
		}else {
			doPost(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 호출");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 호출");
	}

}
