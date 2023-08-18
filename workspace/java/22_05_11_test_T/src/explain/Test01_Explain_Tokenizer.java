package explain;

import java.util.*;

public class Test01_Explain_Tokenizer {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("6개의 학점을 빈 칸으로 분리 입력(A/B/C/D/F) >>");
		String value = sc.nextLine();
		System.out.println("입력 완료");
		
		StringTokenizer st = new StringTokenizer(value, " "); // StringTokenizer(문자열, 잘라낼 구분자, true || false);
		System.out.println(st.countTokens());				// ->true면 구분자 포함 | false는 구분자 포함x
		List<Character> list = new ArrayList<>();			// =>기본값은 false로 되어있음
		
		while(st.hasMoreTokens()) {
			char c = st.nextToken().charAt(0); // st.nextToken() : 이게 문자열임
			list.add(c);
		}
		System.out.println(list);
		
		// 이후는 이전과 동일
		double total = 0;
		for(int i=0; i<list.size(); i++) {
			char c = list.get(i);	
			switch(c) {
				case 'A' :
					total += 4;
					break;
				case 'B' :
					total += 3;
					break;
				case 'C' :
					total += 2;
					break;
				case 'D' :
					total += 1;
					break;
				default :
					total += 0;
					break;
			} // switch문 종료
		} // for문 종료
		double avg = total / list.size();
		avg = total / list.size();
		System.out.printf("평균은 : %.2f \n",avg); 
		
	} // main 종료
}
