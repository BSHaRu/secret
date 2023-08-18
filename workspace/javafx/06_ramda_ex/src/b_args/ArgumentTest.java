package b_args;

public class ArgumentTest {

	public static void main(String[] args) {
		MyInterface i = new MyInterface() {
			@Override
			public void method(int x, int y) {
				int sum = x+y;
				System.out.println("sum : " + sum);
			}
		};
		i.method(10,20);
		
		i = (a, b) ->{		// 어짜피 타입도 알고있기때문에 생략가능
			int result = a*b;
			System.out.println("result : " + result);
		};
		i.method(10, 20);
		
		SecondInterface si = s->{ 	// 매개 변수가 1개라면 ()를 생략해도 된다.
			System.out.println(s);
			
		};
		si.method("참 쉽조잉?");
		
		
	} // main end
}
