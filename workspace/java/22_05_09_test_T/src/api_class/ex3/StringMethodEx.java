package api_class.ex3;

import java.util.Scanner;

public class StringMethodEx {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("문자열을 입력하세요 >");
			String s = sc.next();
			
//			if(s.indexOf("java") != -1) {
			if(s.contains("java")) {	// 이것도 가능함
				System.out.println("java가 존재합니다.");
				System.err.println("시스템을 종료합니다.");
				break;
			}
			System.out.println("java가 존재하지 않습니다");
		}
	}

}
