package singleton;

class Printer{
	
	private static Printer printer;		// 3. 그다음 static을 줘서 정적인 놈으로 만들어서 바로 쓸 수 있게 하자.
	
	private Printer() {}				// 2. 외부에서 호출 못하도록 private로 지정 하자.
	
	public static Printer getInstance() {
		if(printer == null) {
			printer = new Printer();
		}								// 5. 그러고 나서 if문으로 검증을 해줘.
		return printer;					// 4. private로 지정했으니 get을 해야되니 return값 부터 일단 줘.
	}
	
	public void println(String document) {
		System.out.println(document);
	}									// 1. 이걸 먼저 만들었어.
}

public class Singleton_test {

	public static void main(String[] args) {
//		Printer printer = new Printer(); // private로 막아져서 이렇게 생성 안됨
//		Printer.printer; 				// 이 또한 private라서 접근이 안됨
		Printer printer = Printer.getInstance();
		printer.println("문서 출력");
		Printer printerOther = Printer.getInstance();
		printerOther.println("두번째 출력");
		System.out.println(printer == printerOther);
	}
}
