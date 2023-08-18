package my_test;

public class RandomTest {

	public static void main(String[] args) {

		int sum = 0;
		
		for(int i=1; i < 10; i++) {
			int num = (int)(Math.random()*100)+1;
			System.out.print(num + " ");
			sum += num;
		}
		System.out.print("난수 총합계 : " +sum);
	}

}
