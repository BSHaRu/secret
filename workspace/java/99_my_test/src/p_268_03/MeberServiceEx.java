package p_268_03;

public class MeberServiceEx {

	public static void main(String[] args) {

		MemberService ms = new MemberService();
		
		boolean result = ms.login("hong", "1234");
		
		if(result) {
			System.out.println("로그인이 되었습니다.");
			ms.logout("hong");
		}else {
			System.out.println("id 혹은 pw가 올바르지 않습니다.");
		}
	}

}
