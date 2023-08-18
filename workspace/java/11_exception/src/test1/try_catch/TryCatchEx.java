package test1.try_catch;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchEx {

	public static void main(String[] args) {
		System.out.println("Main Strat");
		
		String str = null;
		int[] scores = new int[4];
		Scanner sc = new Scanner(System.in);
		
		try { // try catch는 한몸이다 -> try 블록안에 정상적으로 실행되면 catch는 실행안됨
			System.out.println("배열에 삽입하려는 인덱스 번호를 입력 하세요");
			int index = sc.nextInt();
			System.out.println("입력하려는 값을 작성하시오 >");
			scores[index] = sc.nextInt();
			System.out.println(str.equals("null"));
		}catch(InputMismatchException e) {
			System.out.println("정수가 입력되지 않음");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("e message : " + e.getMessage());
		}catch(Exception e) { 	//Exception은 최상위 예외라서 무조건 맨 마지막에 있어야됨
			e.printStackTrace(); // 예외가 발생하면 어떤 예외가 뜨는지 오류 이름을 알려주고, 해당문장 진행됨
		}finally {
			System.out.println("항상 실행");
		}
		
		
		System.out.println("Main End");
	}

}
