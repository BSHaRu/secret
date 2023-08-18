package conditional_statement;

import java.util.Scanner; // import : 외부에 있는 폴더를 사용 할 때 쓰는 친구

public class SwitchEx {

	public static void main(String[] aregs) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력해주세요 > "); // print는 출력하고 줄바꿈을 안함 / println은 출력하고 줄바꿈을 해줌
		int score = sc.nextInt(); // 입력대기
		System.out.println(score);
		
		switch(score / 10) {
			case 10 : case 9 :
				System.out.println("A");
				break;
			case 8 :
				System.out.println("B");
				break;
			case 7 :
				System.out.println("C");
				break;
			case 6 :
				System.out.println("D");
				break;
			default :
				System.out.println("F");
				break;
		}
		
	}
}
