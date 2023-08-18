package my_test;

public class Gugudan {

	public static void main(String[] args) {

		
		for(int i=2; i < 10; i++) {
			for(int j=2; j < 10; j++) {
				int result = j * i;
				System.out.printf("%d * %d = %s, \t",j,i,result);
			}
			System.out.println();
		}
	}

}
