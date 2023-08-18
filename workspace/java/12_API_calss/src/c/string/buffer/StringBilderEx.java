package c.string.buffer;

public class StringBilderEx {

	public static void main(String[] args) {
		String a = "홍길동";
		String b = "홍길동";
		System.out.println("a의 hashCode : " + a.hashCode());
		System.out.println("b의 hashCode : " + b.hashCode());
		System.out.println("=========================================");
		
		a += "천재";
		System.out.println("추가 a의 hashCode : " + a.hashCode()); // a 값의 주소값이 바뀐게 아니라 +@가 되는거임
		System.out.println("기존 b의 hashCode : " + b.hashCode());
		a += "다";
		System.out.println("추가2 a의 hashCode : " + a.hashCode());
		System.out.println("=========================================");
		
		StringBuilder sb = new StringBuilder("초기값 : ");
		// append : 기존 값 뒤에 매개변수로 넘겨 받은 값을 추가
		sb.append("자바 ");
		sb.append("Programming Study!!");
		String result = sb.toString();
		System.out.println(result);
		System.out.println(sb.hashCode());
		System.out.println("=========================================");
		
		// 문자열에서 4번째 인덱스에 두번째 매개변수값을 삽입
		sb.insert(4, 2);
		System.out.println(sb.toString());
		System.out.println(sb.hashCode());
		System.out.println("=========================================");
		
		// 해당 인덱스에 있는 문자를 두번째 매개변수 문자로 변경
		sb.setCharAt(4, '8');
		System.out.println(sb.toString());
		System.out.println(sb.hashCode());
		System.out.println("=========================================");
		
		// delete(시작인덱스, 해당자리까지) 문자열 삭제
		sb.delete(4, 6);
		System.out.println(sb.toString());
		System.out.println(sb.hashCode());
	}

}
