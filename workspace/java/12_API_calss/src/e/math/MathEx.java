package e.math;

public class MathEx {

	public static void main(String[] args) {
		// Math (수학관련 처리 class)
		
		// abs : 절대값을 표현 해주는 정적 메소드
		int v1 = Math.abs(-5);
		double v2 = Math.abs(-3.14);
		System.out.println("v1 : " + v1);
		System.out.println("v2 : " + v2);
		
		// floor : 소수점 자리를 내림처리 해줌 ->반환타입은 무조건 double임
		double v3 = Math.floor(5.3);
		double v4 = Math.floor(-5.3);
		System.out.println("내림 처리 > ");
		System.out.println("v3 : " + v3);
		System.out.println("v4 : " + v4);
		
		// ceil : 소수점 자리를 올림 처리 해줌 
		double v5 = Math.ceil(5.3);
		double v6 = Math.ceil(-5.3);
		System.out.println("올림 처리 > ");
		System.out.println("v5 : " + v5);
		System.out.println("v6 : " + v6);
		
		// max : 두개의 값을 매개변수로 넘겨받아 둘 중 큰 수를 반환 -> 해당 타입으로 반환
		int v7 = Math.max(10, 12);
		double v8 = Math.max(10.1, 10.4);
		System.out.println("큰 값을 반환 > ");
		System.out.println("v7 : " + v7);
		System.out.println("v8 : " + v8);
		
		// min : 두개의 값을 매개변수로 넘겨받아 둘 중 작은 수를 반환
		int v9 = Math.min(10, 7);
		double v10 = Math.min(10.5, 10.3);
		System.out.println("작은 값을 반환 > ");
		System.out.println("v9 : " + v9);
		System.out.println("v10 : " + v10);
		
		// 반올림
		// rint : 소수점 첫째자리에서 반올림하고 값을 double로 반환 
		double v11 = Math.rint(3.141592);
		double v12 = Math.rint(3.9512);
		System.out.println("소수점 첫째자리에서 반올림 > ");
		System.out.println("v11 : " + v11);
		System.out.println("v12 : " + v12);
		
		// round : 반올림 하고나서 long 타입으로 반환
		long v13 = Math.round(3.141592);
		System.out.println("반올림하고 정수처리 > ");
		System.out.println("v13 : " + v13);
		
		// 0 <= random < 1.0
		double random = Math.random();
		System.out.println(random);
		
		// 거듭 제곱
		// .pow(a,b) : a와 b에 대한 제곱 연산 -> a^b
		double v14 = Math.pow(2, 10);
		System.out.println("제곱 처리 > ");
		System.out.println("v14 : " + v14);
		
		// sqrt : 넘겨받은 매개변수를 제곱근을 구함 ex) 16의 제곱근 = 4
		double v15 = Math.sqrt(9);
		System.out.println("제곱근은 > ");
		System.out.println("v15 : " + v15);
		
		// subtractExact : 두 수의 차이를 반환
		System.out.println(Math.subtractExact(11, -5));
	}

}
