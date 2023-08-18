package c.string.method;

public class FormatEx {

	public static void main(String[] args) {
		
		String str = "freelife";
		boolean bool = true;
		int i = 127;
											// %S : 대문자, %s : 소문자
		String result1 = String.format("문자열 서식 : %S, %s",str,str);
		System.out.println(result1);
		
		String result2 = String.format("boolean 서식 문자열 : %B %b", bool,bool);
		System.out.println(result2);
											
		String result3 = String.format("숫자 서식 : %d, %o %x, %05d", i,i,i,i);
		System.out.println(result3);
	}

}
