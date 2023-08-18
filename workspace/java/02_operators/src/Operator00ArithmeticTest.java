
public class Operator00ArithmeticTest {

	public static void main(String[] args) {
		
		int num1 = 10;
		int num2 = 20;
		
		System.out.println(+num1);
		System.out.println(-num2);
		System.out.println(num2);
		
		num2 = -num2;
		System.out.println(num2);
		
		num1 = 10;
		num2 = 3;
		int result = num1 / num2;
		System.out.println("result : "+result); // 실제론 3.333333...이지만 int로 계산되기때문에 정수만 표시
		
		double dResult = num1 / num2;		
		System.out.println("dReult : "+dResult); // int로 먼저 계산 한 뒤에 double로 바뀌는거라 이미 소수점은 다 날라감.
												// -> 결과값 3인 상태에서 double로 바껴 3.0으로 표시해주는거.
		double typeReult = num1 / dResult;
		System.out.println("typeReult : "+typeReult);
		
		byte x = 100;
		x = (byte)(x + 100); // byte는 1byte라 x + 100 = 200이니 1byte로 표현을 못함
							// -> 앞에 (byte)를 붙여서 byte로 표시 해도 된다고 강제로 타입설정 하는거임
		System.out.println(x);	// but, byte는 -128~127까지 표현가능하기때문에 200 - 256 해서 -56이 나옴
		
		String s = "홍길동";
		s = num1 + num2 + s;
		System.out.println(s);
		
		s = s + (num1 + num2);
		System.out.println(s);
		
		int a = 1, b = 1;
		int c = a++;
		System.out.println("c : " +c);
		System.out.println("a : " +a);
		c = ++a;
		System.out.println("c : " +c);
		System.out.println("a : " +a);
	}
}
