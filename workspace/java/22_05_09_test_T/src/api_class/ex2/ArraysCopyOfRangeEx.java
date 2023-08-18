package api_class.ex2;

import java.util.Arrays;

public class ArraysCopyOfRangeEx {

	public static void main(String[] args) {
		int[] array1 = {10,20,30,40,50};
		int[] array2;
		
		array2 = Arrays.copyOfRange(array1, 1, 3);
		System.out.println(Arrays.toString(array2));
		
		System.out.println("=========================");
		array2 = new int[2];
		// arraycopy : (원본배열, 복사 시작할 인덱스 번호,
		//				복사할 배열, 복사 할 배열에 값이 들어갈 인덱스 번호, 복사할 길이)
		System.arraycopy(array1, 1, array2, 0, 2);
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
	}

}
