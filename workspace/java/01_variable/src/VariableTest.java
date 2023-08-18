
public class VariableTest {
	
	public static void main(String[] args) {
		
		int num = 10;
		// num = "문자열"; // int는 정수값만 저장가능
		
		byte bNum = 0B00001010; // 0B 바이너리 - 뒤에 오는 값은 이진수
		System.out.println("bNum : "+bNum);
		
		//           8^1 * 1 + 2 = 10
		byte oNum = 012;	// 0이 붙어 있으면 8진수
		System.out.println("oNum : "+oNum);
		
		//			16^1 *3 + 10 = 58
		byte xNum = 0X3A; // 0X는 16진수로 표현
		System.out.println("xNum : "+xNum);
		
		byte b1 = -128;
		b1 = 127;
		// b1 = -129; // byte는 -128~127까지 저장 가능하기 때문에 컴파일 오류가 남
		
		char c = '가';
		// c = 'AB' // ''(작은따옴표)는 하나의 문자만 표시 할 수 있다.
		c = 65;
		System.out.println("c : "+c); // 아스키코드의 65는 A기 때문에 A가 출력
		c = (char)(c + 1);			// 연산을 할 때 자동으로 int로 인식하기때문에, 앞에 char를 쓰면서 강제로 char타입으로 바꿔줌
									// * int가 4byte고, char는 2byte라서 작은 값에 큰 값을 대입 할 수 가 없음
		System.out.println("c : "+c);
		
		short s = -30000; // short는 2byte의 정수를 저장하고, + - 둘 다 표현 가능
		int i = 1000000000; // int는 4byte의 정수를 저장, 약 +-21억까지 저장가능
		long l = 10000000000L; // 기본적으로 변수 선언할 때 기본 리터럴 값이 int라서 4byte까지 저장 못함
							   // 그래서 8byte를 쓸꺼면 뒤에 L을 붙여서 long 타입이라는걸 알려줘야됨
		
		// 십진수 수 : decimal
		// float - 4byte : 소수점 7자리
		float f = 3.145f; // f = float(3.145); 랑 같은의미 
		
		// double - 8byte : 소수점 15자리 - 실수형 기본 리터널
		double d = 3.141592;
		double sum = f + d; 
		
		sum = i + d;
		System.out.println(sum); // int는 4byte double은 8byte라서 double이 크기때문에 double로 출력
		
		// 지수 표기법
		System.out.println(3e+5 == 300000); // 노란줄 뜨는 의미는 컴퓨터가 인식하기엔 둘다 같은값인데 굳이 비교할 필요가 있냐? 하고 알려주는거
		System.out.println(3e-5 == 0.00003); 
		
		boolean isTrue = 3e-5 == 0.00003;
		System.out.println(isTrue);
		
		String str = "문자열을 저장하는 녀석";
		System.out.println(str);
		
	}
}
