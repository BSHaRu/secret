package api_class.ex4;

import java.util.Scanner;

public class StringCaseEx {

	public static void main(String[] args) {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		String num = "1234567890";
		
		Scanner sc = new Scanner(System.in);
		System.out.print("문자나 숫자를 입력하세요 : ");
		String str = sc.next();
		System.out.println("입력받은 문자 : " +str);
		str = str.toLowerCase();
		
		boolean isChecked = (chars.indexOf(str) != -1) ? true : false;
		System.out.println("영어 문자 : " + isChecked);
		isChecked = (num.contains(str));
		System.out.println("숫자 : " + isChecked);
		
		
	}

}
