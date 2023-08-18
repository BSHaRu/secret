package c.string.method;

public class ReplaceEx {

	public static void main(String[] args) {
		String oldStr = "자바는 어렵다! 자바는 그래도 해야한다! 자바!";
		String newStr = oldStr.replace("자바", "JAVA");
		System.out.println(oldStr);
		System.out.println(newStr);
	}

}
