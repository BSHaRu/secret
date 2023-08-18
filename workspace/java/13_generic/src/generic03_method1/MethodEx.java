package generic03_method1;

class Util{
//	Box<String> box1 = Util.<String>boxing("사과"); // String 타입으로 호출했기때문에 매개변수는 문자열로 받아야됨
	public static <T> Box<T> boxing(T t) {
		Box<T> box = new Box<>();
		box.setType(t);
		return box;
	}
}

public class MethodEx {

	public static void main(String[] args) {
		Box<String> box1 = Util.<String>boxing("사과");
		System.out.println(box1.getType());
		
		Box<String> box2 = Util.boxing("오렌지"); // 매개변수 타입을 보고 generic타입을 알아서 유추하기때문에 생략가능
		System.out.println(box2.getType());
		
//		Box<String> errorBox = Util.boxing(1); // Sting으로 지정했는데 매개변수 타입이 다르기때문에 오류가 남
		Box<Integer> box3 = Util.boxing(1);
		System.out.println(box3.getType());
	}

}
