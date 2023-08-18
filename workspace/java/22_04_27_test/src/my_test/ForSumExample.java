package my_test;

import java.util.Scanner;

public class ForSumExample {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean isRun = true;
		int sum = 0;
		int sum2 = 0;
		
		while(isRun) {
			System.out.println("숫자를 입력하세요 >");
			int num = sc.nextInt();
			
			for(int i=0; i <= num; i++) {
				if(i % 3 == 0) sum2 += i;
				sum += i;
				isRun = false;
			} // for문 종료
			System.out.printf("1부터 %d까지의 총합계 : %d \n",num,sum);
			System.out.printf("1부터 %d까지의 3의 배수의 총합계 : %d \n",num,sum2);
		}
	}
}
