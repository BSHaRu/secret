package iteration_statemnet;

public class ForStatementEx {

	public static void main(String[] args) {
		int sum = 0;
//		int i;
		
		for(int i=1; i<=100; i++) {
			sum += i;
		}
//		System.out.println(i);
		System.out.println("1~100까지의 합은 : "+sum);
		
//		for(;;) { // 조건식(탈출 방법)이 없기 때문에 무한 반복함 -> 오류는 아님 
//			
//		}
		
		int i = 1;
		sum = 0;
		for(;; i++) {
			if(i>10) {
				break;
			}
			sum += i;
		} // 이렇게도 for문을 작성할 수 있다.
		
		for(i = 2; i<=9; i++) {
			System.out.print(i+"단\t|");
			for(int j=1; j<=9; j++) {
				System.out.print(i+" * "+j+" = "+(i*j)+"\t");
			}
			System.out.println();
		}
		
	}
}
