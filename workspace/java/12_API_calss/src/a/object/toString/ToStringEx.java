package a.object.toString;

public class ToStringEx {

	public static void main(String[] args) {
		Object obj = new Object(); // java.lang.Object이라서 java.lang이거는 생략가능
		System.out.println(obj.toString());
		
		obj = new Person("홍길동",184,75);
		System.out.println(obj);
		
	} // main 종료

}
