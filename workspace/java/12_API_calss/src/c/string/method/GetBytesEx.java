package c.string.method;

import java.io.UnsupportedEncodingException;

public class GetBytesEx {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "한글표현";
		System.out.println(str.length());
		byte[] bytes1 = str.getBytes("UTF-8"); // 무조건 사용자가 예외 처리 해라고 빨간줄 뜸
		System.out.println(bytes1.length);		// -> UTF-8은 한글/한자를 3byte로 표현함
		
		byte[] bytes2 = str.getBytes("EUC-KR"); // EUC-KR은 한글/한자를 2byte로 표현함	 
		System.out.println(bytes2.length);		//-> 이제는 거의 안쓰고 다 UTF-8을 씀
		
		String result1 = new String(bytes1);
		System.out.println(result1);
		String result2 = new String(bytes2,"EUC-KR"); 	// 그냥 출력해버리면 한글이 깨짐 
		System.out.println(result2);				// -> 자바는 기본으로 UTF-8이라서 3byte를 2byte로 합칠려고 하기때문에 깨지는거임
	}

}
