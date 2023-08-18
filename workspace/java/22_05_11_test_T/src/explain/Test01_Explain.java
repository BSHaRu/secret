package explain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test01_Explain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("6개의 학점을 빈 칸으로 분리 입력(A/B/C/D/F) >>");
		String value = sc.nextLine();
		System.out.println("입력 완료");
		
		// 분리 방법 : String.split(구분자) | StringTokenizer(문자열,구분자)
		value = value.replace(" ", ""); // replace(A,B) : A를 B로 바꿔지는 친구
									// -> A B C D D F   => ABCDDF
		char[] chars = value.toCharArray();
		System.out.println(Arrays.toString(chars));
		
		List<Character> charList = new ArrayList<>();
		for(char c : chars) {
			charList.add(c);
		}
		System.out.println(charList);
		
		double total = 0;
		for(char c : charList) {
			if(c == 'A') {
				total += 4;
			}else if(c == 'B') {
				total += 3;
			}else if(c == 'C') {
				total += 2;
			}else if(c == 'D') {
				total += 1;
			}else {
				total += 0;
			}
		} // 향상된 for문 종료
		
		double avg = total / charList.size();
		System.out.printf("평균은 : %.2f \n",avg);
		System.out.println("시스템 종료");
		
		// split 활용
		System.out.println("split으로 할꺼라 다시 입력 해주세요 >");
		value = sc.nextLine();	// 위에서 replace로 공백을 다 제거했기때문에 다시 입력받는거임
		String[] strs = value.split(" ");
		charList.clear();		// 위에서 charList의 값들이 들어있으니깐 값을 비워주는거임
		
		// char는 Character.valueOf("문자열"); | (char)"문자열";을 사용 못하기때문에 charAt();을 써야됨
		for(int i=0; i<strs.length; i++) {
			char c = strs[i].charAt(0); 
			charList.add(c);
		}
		
		total = 0;
		for(int i=0; i<charList.size(); i++) {
			char c = charList.get(i);	// get() : 특정 index값을 꺼내올때 사용하는 친구
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
		avg = total / charList.size();
//		System.out.printf("평균은 : %.2f \n",avg); // 이거 써도 되는데 위에썼으니깐 DecimalFormat쓸려고 하는거임
		DecimalFormat df = new DecimalFormat("#.##");
		String result = df.format(avg);
		System.out.println("평균은 : " + result);
		
	}// main 종료
}
