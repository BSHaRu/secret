package test2.throws_ex;

public class ThrowsEx {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		
		try {
			findClass("test2.throws_ex.ThrowsEx");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main 종료");
	}
											// throws는 지금 하는게 아니라 떠 넘기는거임. -> 예외처리할꺼면 try catch가 "무조건" 있어야됨
	public static void findClass(String path) throws ClassNotFoundException {
		Class clazz = Class.forName(path); // 컴파일로 "무조건" 오류처리 해야됨
		System.out.println(clazz);
	}
}
