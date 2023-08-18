package i.arrays;

import java.util.Arrays;

public class Arrays03SearchEx {

	public static void main(String[] args) {
		int[] scores = {1,10,3,5,2};
		Arrays.sort(scores);
		int index = Arrays.binarySearch(scores, 10); // binarySearch(배열, 찾고 싶은 값);
		// binarySearch : (처음 인덱스 번호 + 마지막 인덱스번호) / 2 = 해당인덱스번호의 값이랑 찾는 값 비교함
		// -> 비교하는 값과 해당 인덱스번호의 값이 다를 경우 
		// 클 경우 : (해당 인덱스번호 + 마지막 인덱스 번호 ) / 2 | 작을경우 : (해당 인덱스 번호 + 처음 인덱스 번호) / 2 를 계속 진행.
		// -> 음수가 나오면 해당값을 못찾았고, 어디까지 검색해봤는지 위치를 알려줌 (처음 인덱스위치를 -1로 함)
		// => 그래서 무조건 정렬이 되어있어야된다.
		System.out.println("찾은 인덱스 : " + index);
		
	}

}
