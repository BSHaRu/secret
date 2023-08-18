package b_final_test;

public class FinalEx {

	int a = 10;
	final double pi = 3.14;
	final int b;	// b가 초기화가 안되어있기때문에 오류가 생김
					// -> 생성자를 생성해서 b값을 초기화 시켜줌
	final static int MAX_VALUE = 30;	// final static : 고정된 값을 가지고 있고, 클래스 자원으로 어디서든 접근이 가능함
										// -> 상수가 된다. 즉, 변수값도 대문자로 사용함 (static final로 쓰던 final static이건 순서바껴도 상관x)
	
	FinalEx(){
		b = 100;
	}
	
	public static void main(String[] args) {

		FinalEx finalEx = new FinalEx();
		finalEx.a = 30;
//		finalEx.b = 30; // final로 b값을 초기화 시켜놨기때문에 해당 b에는 값을 대입할 수 없다.
		final int v;
		v = 100;
//		v = 10000;
//		static int ac = 10; // static은 여기서 못쓴다고 함. ???????
		
		System.out.println(Integer.MAX_VALUE);	// Integer : Int 타입의 최대값 & 최소값을 알려줌
		System.out.println(Integer.MIN_VALUE);	// -> Int는 4byte니깐 최대값과 최소값은 고정 되음 
												// => Integer 이것도 static final(상수)이다.
	}

}
