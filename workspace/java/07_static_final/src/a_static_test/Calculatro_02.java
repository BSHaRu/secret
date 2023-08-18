package a_static_test;

public class Calculatro_02 {
	
	int a, b;
	
	static int result; // ③ 이렇게 지정하면 ① ②상관없이 사용가능하다. 
	
	int sum() {
		return a + b;	// ② 그렇기 때문에 a, b는 인스턴트 메소드를 만들어서 사용하면 된다.
	}
	
	static int plus(int x, int y) {  
//		a = x + y; // ① int a, b는 공간이 아직 확보가 안되어있기때문에 a와 b는 static 안에서는 사용이 안된다.
		return x + y;
	}

	public static void main(String[] args) {

		int sum = Calculatro_02.plus(10,20); 	// ①조건으로 사용가능
//		Calculatro_02.sum(); 					// 이건 ②조건으로 오류가 남
		Calculatro_02 calc = new Calculatro_02();	
		sum = calc.sum(); 						// ②조건으로 인스턴트 만들어서 사용 가능
		result = plus(100, 200); 				// ③조건으로 사용 가능하다
	}

}
