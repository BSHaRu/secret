package my_test;

import java.util.Scanner;

public class FloorMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean isRun = true;
		
		while(isRun) {
			System.out.println("층수 입력 :");
			int floor = sc.nextInt();
			switch(floor) {
				case 1:
					System.out.println(floor+"층은 정형외과 입니다.");
					break;
				case 2:
					System.out.println(floor+"층은 약국 입니다.");
					break;
				case 3:
					System.out.println(floor+"층은 헬스클럽 입니다.");
					break;
				default :
					System.out.println("없는 층입니다.");
					isRun = false;
			}// switch문 종료
		}
	}

}
