package c8_comparator;

import java.util.Comparator;

public class DecendingComparator implements Comparator<Fruit> {	// 제네릭엔 뭘 비교할껀지 넣어주면됨

	@Override
	public int compare(Fruit o1, Fruit o2) {
		// o1 == 새로 추가 되는 값
		// o2 == 기존 값
		// o2 - o1 = 값이 음수일 때 이동 | 양수이거나 0일때 기존 유지
		System.out.println("compare : "+o2+"-"+o1);
		return o2.getPrice() - o1.getPrice();	// 내림차순으로 정렬됨
		/* ComparatorEx 위치
		 1. 3000 - 3000 = 0 -> 유지
		 2. 3000 - 1500 = 1500 ->유지 [3000, 1500]
		 3. [3000, 1500, 10000] | (0+1)/2 = 0
		  3000 - 10000 = -7000 ->이동 [10000,3000, 1500]
		 */
	}	

}
