package c.string.split_tokenizer;

import java.util.StringTokenizer;

public class TokenizerEx {

	public static void main(String[] args) {
		String text = "이나영/김희선/박보영/원빈/장동건";
		StringTokenizer st = new StringTokenizer(text,"/");
		int countTokens = st.countTokens();
		System.out.println(countTokens);
		
		for(int i=0; i<countTokens; i++) {
			String token = st.nextToken();
			System.out.print(token + " ");
		}
		
		System.out.println();
		System.out.println("======================");
		
		text = "홍길동|페이커,이제동&박명수\\김택용\"이순신";
		st = new StringTokenizer(text,"|,&\\\"");
		while(st.hasMoreTokens()) {			// token이 있으면 true | 없으면 false
			String token = st.nextToken();
			System.out.print(token + " ");
		} // while문 종료
	}

}
