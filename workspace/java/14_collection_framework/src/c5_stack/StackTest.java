package c5_stack;

public class StackTest {

	public static void methodA() {
		System.out.println("methodA 호출");
		int result = methodB(5);			// b. 이 코드르 작성한거임 
		System.out.println("result : " + result);
		System.out.println("methodA 종료");
	}
	
	public static int methodB(int b) {		// a. methodB를 다 작성하고
		System.out.println("methodB 호출");
		int result = b * b; 
		System.out.println("methodB 반환 종료");
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("Main 시작");
		methodA();
		System.out.println("Main 종료");
		
		/* 순서 설명
		 1. main 메소드 생성 - arges 스택에 저장
		 2. methodA 생성 - result 스택에 저장
		 3. methodB 생성 - b가 먼저 스택에 저장 후 result 저장 -> b = 5; result = 5 * 5;
		 4. methodA의 result에 25저장
		 5.
		 
		 */
		
	} // main 종료
}
