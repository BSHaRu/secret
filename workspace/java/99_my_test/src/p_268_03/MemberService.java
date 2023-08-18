package p_268_03;

// login() - id값 : "hong", pw : "1234"일 경우에만 true, 그외는  false 반환
// logout() - 로그아웃 되었다고 출력

public class MemberService {

	boolean login(String id, String pw) {
		if(id.equals("hong") && pw.equals("1234")) {
			return true;
		}else {
			return false;
		}
	}

	void logout(String id) {
		System.out.println("로그아웃 되었습니다.");
	}
}
