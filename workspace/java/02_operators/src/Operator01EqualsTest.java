
public class Operator01EqualsTest {

	public static void main(String[] args) {
		
		int num1 = 10, num2 = 20;
		boolean result = num1 > num2;
		System.out.println(result);
		result = num1 == num2;
		System.out.println(result);
		
		String s = "홍길동";
		String s1 = "홍길동";
		String s2 = "잠자는 숲속의 공주";
		System.out.println(s == s1);
		System.out.println(s == s2);
		
		String s3 = new String("홍길동");
		System.out.println(s);
		System.out.println(s3);
		System.out.println(s == s3); // s3의 홍길동은 처음부터 홍길동이 아니라 new로 새로 선언한거라 
									// 애초에 저장 공간 위치가 다르기 때문에 false이 뜨는거
		/* java는 메모리 공간이 크게 3가지로 나눠 져있음 - 나중에 배우면 더 자세히 알려준다고 함 => "대충"정리한거
		 * Method 영역 - class, 전역변수, static 변수 정보가 저장되는 공간 
		 * 				-> "홍길동", "잠자는 숲속의 공주" ... 등
		 * Heap 영역  - new로 선언하면 여기로 저장됨 / 참조타입 저장됨 ; 동적할당 공간
		 * stack 영역 - 기본타입이 저장되는 곳 / 함수가 저장됨 ; 지역변수, 매개변수가 저장되는 공간(잠시 사용되고 사라지는곳)
		 */
		result = s.equals(s3); // equals은 문자열을 비교함 => 이건 나중에 equal 배우면 쌤이 자세히 설명해준다고함
		System.out.println(result); // 그래서 true가 나옴
	}

}
