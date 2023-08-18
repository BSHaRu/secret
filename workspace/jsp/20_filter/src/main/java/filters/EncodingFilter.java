package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {
	// web.xml에 설정한 init-param을 쓰기 위해서 필드 선언
	String encodingName;
	String requestMethod;
	
	// 브라우저 실행전에 실행됨
	// init은 최초에 1번만 실행됨
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("EncodingFilter init start");
		
		// parameter에는 web.xml에 설정한 init-param의 name 값을 입력하면됨
		encodingName = fConfig.getInitParameter("encoding");
		requestMethod = fConfig.getInitParameter("method");
		
		System.out.println("EncodingFilter init end");
	}
	
	// doFilter는 호출 될 때마다 계속 실행됨
	@Override			
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("encoding doFilter start =======");
		
		HttpServletRequest req = (HttpServletRequest)request;	// Http : 전송 방식을 알려줌
		// 전송방식 -> get or post								// -> 그래서 그냥 Servlet만 있으면 그걸 모르기때문에
		String method = req.getMethod();					// Http로 타입변환해주는거임
		System.out.println("전송방식 : " +method);				// => Servlet이 최상위객체 ==> Servlet > HttpServlet
		if(method.equals(requestMethod)) { // requestMethod가 post 방식으로 저장해놨음
			request.setCharacterEncoding(encodingName); // encodingName에 UTF-8이 저장되어있음
			System.out.println(encodingName + " 인코딩 완료");
		}
		chain.doFilter(request, response); // 요청이 처리되기전에 먼저 인코딩이 진행되어야함.
		
		System.out.println("encoding doFilter end =======");
	}

}
