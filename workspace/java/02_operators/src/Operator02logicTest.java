
public class Operator02logicTest {

	public static void main(String[] args) {
		
		int num1 = 5;
		int num2 = 10;
		
		boolean result = (num1 < 0) && (num2++ > 0);
		System.out.println(result);
		System.out.println(num1+" : " +num2); // num1 < 0 이 거짓이기때문에 num2++은 계산을 안함
											// -> num2 가 10 그대로 출력되는거임
		num1 = 0;
		num2 = 0;
		result = (++num1 > 0) || (num2++ > 0);
		System.out.println("result : " + result);
		System.out.println(num1+" : "+num2); // ++num1 > 0 은 참이기 때문에 뒤에 num2++은 계산을 안함
		
		System.out.println("result : " + !result);
		
	}
}
