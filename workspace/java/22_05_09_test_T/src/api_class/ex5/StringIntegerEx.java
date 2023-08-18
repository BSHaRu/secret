package api_class.ex5;

import java.util.Arrays;

public class StringIntegerEx {

	public static void main(String[] args) {
		String scores = "100,11,35,41";
		int total = 0;
		double avg = 0.0;
		
		String[] score = scores.split(",");
		System.out.println(Arrays.toString(score));
		
		for(String s : score) {
			System.out.print(s+" ");
			total += Integer.parseInt(s);
		}
		avg = (double)total / score.length;
		System.out.println();
		
		System.out.println(scores);
		System.out.println("총점 : " + total+ "점");
		System.out.printf("평균 : %.1f점",avg);
		
	}

}
