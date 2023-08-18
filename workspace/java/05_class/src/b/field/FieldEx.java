package b.field;

public class FieldEx {
	// main()이 포함 되어있는 실행 가능한 클래스
	public static void main(String[] args) {
		
		Field f = new Field();
		
		System.out.println(f.i);
		System.out.println(f.b);
		System.out.println(f.d);
		System.out.println(f.s);
		
		Field f2 = new Field();
		
		f2.i = 100;
		f2.d = 3.14;
		f2.b = true;
		f2.s = "홍길동";
				
	}

}
