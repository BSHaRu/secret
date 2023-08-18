package i.arrays;

import java.util.Arrays;

public class Arrays04EqualsEx {

	public static void main(String[] args) {
		int[][] original = {
				{1,2},
				{3,4}
		}; // 2차원 배열 선언
		int[][] copy = Arrays.copyOf(original, original.length);
		System.out.println(original.equals(copy));		// original의 배열 위치랑, copy의 배열 위치가 달라서 false가 나온다네..
		System.out.println(Arrays.equals(original, copy)); // 이건 original[1]값과 copy[1]값의 위치를 확인하기때문에 true...
		System.out.println(Arrays.toString(original));
		System.out.println(Arrays.toString(copy));
		
		System.out.println(Arrays.deepEquals(original, copy)); // 이거는 original[0][0] == copy[0][0] 여기의 실제 값들을 비교하는거래
																// -> 2차원 배열안에 있는 1차원 배열의 실제 값들을 비교.
		System.out.println("===== 깊은 복제 ======");
		int[][] copy2 = Arrays.copyOf(original, original.length);
		copy2[0] = Arrays.copyOf(original[0], original[0].length);
		copy2[1] = Arrays.copyOf(original[1], original[1].length);
		System.out.println(original.equals(copy2));
		System.out.println(Arrays.equals(original, copy2));
		copy2[1][0] = 100; // 이게 존재 함으로써 마지막이 false가 나오는거임. 
		System.out.println(Arrays.deepEquals(original, copy2));
		
		System.out.println(Arrays.toString(original));
		System.out.println(Arrays.toString(copy2));
		System.out.println(Arrays.deepToString(original));
		System.out.println(Arrays.deepToString(copy2));
	}

}
