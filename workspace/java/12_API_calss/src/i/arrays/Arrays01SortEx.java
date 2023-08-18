package i.arrays;

import java.util.Arrays;

public class Arrays01SortEx {

	public static void main(String[] args) {
		int[] scores = {100,99,98,97,96};
		System.out.println(scores);
		String result = Arrays.toString(scores);
		System.out.println(result);
		
		// sort : 오름차순 정렬 (배열, 시작인덱스, 마지막인덱스-1);
		Arrays.sort(scores,2, 4); // -> 2~3번째 인덱스만 오름차순으로 정렬
		System.out.println(Arrays.toString(scores));
		Arrays.sort(scores);
		System.out.println(Arrays.toString(scores));
		
		String[] names = {"홍길동","심청이","김유신","페이커"};
		Arrays.sort(names);
		System.out.println(Arrays.toString(names));
		
		// fill : 배열의 값을 매개변수로 전달한 값으로 채운다.
		Arrays.fill(names, 1,3, "고길동");	// 1~2번째 인덱스가 "고길동"으로 바뀜
		System.out.println(Arrays.toString(names));
		Arrays.fill(names, null);		// 전체 null로 변환
		System.out.println(Arrays.toString(names));
		
	}

}
