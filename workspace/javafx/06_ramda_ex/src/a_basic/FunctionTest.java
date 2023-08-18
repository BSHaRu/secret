package a_basic;

public class FunctionTest {

	public static void main(String[] args) {
		MyInterface myInterface = new MyInterface() {
			@Override
			public void method() {
				System.out.println("myInterface method");
			}
		};
		myInterface.method();
		
		myInterface = ()-> System.out.println("Ramda method");
		myInterface.method();
		
		myInterface = () -> {
			System.out.println("실행문이 여러개");
			System.out.println("일때는 이렇게 블록으로 감싸면됨");
		};
		myInterface.method();

		System.out.println("=====================================================================");
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				
			}
		});
		t.start();

		t = new Thread(()->{
			
		});
		t.start();
		
	} // main end
}
