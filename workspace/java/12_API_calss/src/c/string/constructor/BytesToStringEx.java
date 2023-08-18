package c.string.constructor;

import java.io.UnsupportedEncodingException;

public class BytesToStringEx {

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] bytes = new byte[] {
			//  H   e   l   l   o  공백 J  a  v  a
				72,101,108,108,111,32,74,97,118,97
		};
		String str = new String(bytes);
		System.out.println(str);
		
		
		String str2 = new String(bytes,6,4,"UTF-8"); // 6번째 인덱스부터 4개를 가져온다. | UTF-8로 인코딩도 설정가능
		System.out.println(str2);					// -> 인코딩을 추가하면 예외처리 해줘야됨
		
		char[] strs = new char[] {'안','녕','하','세','요'};
		str = new String(strs);
		System.out.println(str);
		
		str = new String(strs, 0, 4);
		System.out.println(str);
		
		char[] charArray = str.toCharArray();
		for(char c : charArray) {
			System.out.print(c+" ");
		}
		System.out.println();
	}

}
