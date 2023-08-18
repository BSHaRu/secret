package test;

public class Main {

	public static void main(String[] args) {
		Integer a = 10;
		System.out.println("Before : " + a);
		
		changeInteger(a);
		System.out.println("After : " + a);
		
		System.out.println();
		
		String s = "hello";
		System.out.println("1 : " + s);
		
		changString(s);
		System.out.println("2 : " + s);
	}

	public static void changeInteger(Integer param) {
		param += 5;
		System.out.println("param : " + param);
	}


	public static void changString(String s) {
		s += " world";
		System.out.println("chang : " + s);
	}
	
}
