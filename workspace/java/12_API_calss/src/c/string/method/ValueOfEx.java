package c.string.method;

public class ValueOfEx {

	public static void main(String[] args) {
		String str1 = String.valueOf(10);		// int 타입
		String str2 = String.valueOf(true);		// boolean 타입
		String str3 = String.valueOf(3.14);		// double 타입
		System.out.println(str1);				// -> 오버로딩 때문에 가능한거
		System.out.println(str2);
		System.out.println(str3);
											
		String str4 = 10+"";
		System.out.println(str4);
		System.out.println(str4.equals("10"));
		System.out.println(str4.equals(10));
	}

}
