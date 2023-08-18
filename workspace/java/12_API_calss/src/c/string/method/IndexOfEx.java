package c.string.method;

public class IndexOfEx {

	public static void main(String[] args) {

		String str = "자바 프로그래밍을 자바!";
		// indexOf, 						lastIndexOf
		// 처음부터 글자를 찾고 인덱스 값 반환		뒤에서부터 글자를 찾고 인덱스 값 반환
		// 매개변수로 넘겨 받은 값이 문자열에 존재하는지 확인하고 시작인덱스 값을 반환
		// 해당되는 값이 없으면 -1을 반환
		
		int location = str.indexOf("프로그래밍");
		System.out.println(location);
		
		if(str.indexOf("자바") != -1) {
			System.out.println("자바가 존재합니다.");
		}else {
			System.out.println("자바가 존재하지 않습니다.");
		}
		
		System.out.println(str.indexOf("자바"));
		System.out.println(str.lastIndexOf("자바"));
		
		String fileName = "cat.png";			// 주로 lastIndexOf는 확장자 명을 확인하기위해 사용됨
		int index = fileName.lastIndexOf(".");
		String ext = fileName.substring(index+1); // .기준으로 잘라냈고 거기서부터 인덱스번호 +1만큼 만 문자열을 저장해라. 
		System.out.println(ext);				// -> 원본은 유지되고 새로 추가되는거임
		System.out.println(fileName);			// => cat.png는 원본으로 남아있고, png가 추가되는거.
	}

}
