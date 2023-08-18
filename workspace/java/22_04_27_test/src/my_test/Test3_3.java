package my_test;

public class Test3_3 {

	public static void main(String[] args) {

		int[] ran = new int[10];

		System.out.print("최초의 리스트 : ");
		for(int i=0; i < ran.length; i++) {
			ran[i] = (int)(Math.random()*100)+1;
			System.out.print(ran[i] + " ");
		} 
		System.out.println();

		int temp = 0;
		System.out.print("정렬된 리스트 : " );
		for(int i = 0; i < ran.length; i++) {
			for(int j=i+1; j<ran.length; j++) {
				if(ran[i] > ran[j]) {
					temp = ran[i];
					ran[i] = ran[j];
					ran[j] = temp;
				} //if 종료
			} // for > for 종료
			System.out.print(ran[i] + " ");
		}
	} // main 종료

}
