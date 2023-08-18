package iteration_statemnet;

import java.util.Scanner;

public class DoWhileEx {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		
		Scanner sc = new Scanner(System.in);
		boolean isLogin = false;
		
		while(isLogin) {
			System.out.println("while 실행");
		}
		
		do {
			System.out.println("do while 실행");
			System.out.println("아이디 입력 > ");
			String id = sc.next();
			System.out.println("비밀번호 입력 > ");
			String pw = sc.next();
			
			if(id.equals("id001") && pw.equals("pw001")) {
				System.out.println("로그인 성공");
				isLogin = false;
			}else {
				isLogin = true;
				System.out.println("로그인 실패!");
			}
		}while(isLogin);
		
		System.out.println("Main 종료");
	
	}
}
