package c.string.method;

public class SubstringEx {

	public static void main(String[] args) {
		String ssn = "930516-2345678";
		int index = ssn.indexOf("-");
		String firstNum = ssn.substring(0, index);	// 0번째 인덱스 ~ index가 짤라낸곳 까지(-에서 짤랐으니 ~6까지 보임)
		System.out.println(firstNum);
		
		String last = ssn.substring(index+1);	// 하나만 존재하니깐 해당 인덱스번호까지 짤라내고 +1부터 저장해라
		System.out.println(last);
		
		char gender = ssn.charAt(7);
		System.out.println(gender);
	}

}
