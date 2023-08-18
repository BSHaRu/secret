package iteration_statemnet;

import java.util.Scanner;

public class WhileEx {

	public static void main(String[] args) {
		// ctrl + shift + o : 자동으로 import 잡아줌
		Scanner sc = new Scanner(System.in);
		boolean isRun = true;
		
		while(isRun) {
			System.out.println("구구단 단수를 입력 > ");
			// sc.hasNextInt() : 사용자에게 값을 입력받아 값이 정수면 true, 정수가 아니면 false 반환
			isRun = sc.hasNextInt(); // true , false만 반환하는 친구임
			if(isRun) {
				int i = sc.nextInt();
				System.out.print(i+"단 ");
				for(int j=1; j<10; j++) {
					System.out.print(i+" * "+j+" = "+(i*j)+"\t");
				}
				System.out.println();
			}
		}
	}
}
