package explain;

import java.util.*;

public class Test04_Explain04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TreeSet<Integer> set = new TreeSet<>(); // 오름차순으로 자동정렬
		System.out.println("정수(-1이 될때 까지 입력)");
		
		while(true) {
			int n = sc.nextInt();
			if (n == -1) break;
			set.add(n);
		}

		if(set.isEmpty()) {
			System.out.println("수가 하나도 없음");
			System.out.println("시스템 종료");
			return;
		}
		
		int max = set.last();
		int min = set.first();
		System.out.println(set);
		System.out.println("최소값 : " + min);
		System.out.println("최대값 : " + max);
		System.out.println("시스템 종료");
		
	} // main 종료
}
