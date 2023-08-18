package c.methods;

public class MethodOverloading {

	void methodA() {
		System.out.println("method A 호출");
	}
	/* 		식별자 중복으로 호출 불가능
	int methodA() {
		System.out.println("정수값을 반환하는 method A 호출");
	}							*/
	
	// 매개변수 개수 상이
	void methodA(int a) {}
	// 매개변수 타입 상이
	void methodA(double a) {}
	// 매개변수의 개수와 타입이 상이
	void methodA(int a, int b) {}
	void methodA(int a, double d) {}
	void methodA(double d, int a) {}
	
}
