package i.arrays;

import java.util.Arrays;

public class Arrays02CopyEx {

	public static void main(String[] args) {
		int[] scores = {100,60,70,80,90};
		System.out.println(Arrays.toString(scores));
		
		int[] scores2 = new int[scores.length+1]; // 기존 배열보다 +1 증가 시켜라
		for(int i=0; i<scores.length; i++) {
			scores2[i] = scores[i];
		}
		System.out.println(Arrays.toString(scores2));
		
		//위에 처럼 반복하면 너무 귀찮은 작업이니깐 Arrays를 활용하자.
		System.out.println();
		char[] arr1 = {'J','A','V','A'};
		System.out.println(Arrays.toString(arr1));
		
		char[] arr2 = Arrays.copyOf(arr1, 3);	// .copyOf(원본배열, 복사할 길이) : 길이가 원본보다 크거나 작아도 상관없음(음수만 아니면...)
		System.out.println(Arrays.toString(arr2));	// -> 원래 배열의 길이가 줄어들거나 커지는게 아니라 새로 배열을 추가해서 생성되는거임
		
		char[] arr3 = Arrays.copyOf(arr1, arr1.length+1);
		System.out.println(arr1.length);
		System.out.println(arr3.length);
		System.out.println(Arrays.toString(arr3));
		
		char[] arr4 = Arrays.copyOfRange(arr1, 1, 3); // 1~2번 인덱스만 복사한다.
		System.out.println(Arrays.toString(arr4));
		
		System.out.println();
		
	}

}
