package f.random;

import java.util.Random;

public class RandomEx {

	public static void main(String[] args) {
		int [] selectNumber = new int[6];
		// Random class
		// 랜덤 클래스가 생성되서 실행 될 때 시드값이 동일하면 동일한 난수를 발생.
		long time = System.nanoTime(); 			// 컴퓨터는 1970년 1월 1일 09:00 기준을 가짐 -> 0
		long mTime = System.currentTimeMillis(); // 현재시간을 long 타입으로 전환해서 알려줌
		System.out.println(mTime);				// 뒤에 마지막 3자리는 없다고 생각하면(nano니깐) 
												// 0기준으로 지금 얼마나 흘렀는지 알수있음
		Random random = new Random(5);	// 여기에 시드값에 따라서 랜덤이지만 조작이 가능하다.
		for(int i=0; i<6; i++) {
			System.out.print(random.nextInt(45)+1+" ");
		}
		System.out.println();
		System.out.println("==========================================");
		
		random = new Random(mTime);
		for(int i=0; i<6; i++) {
			System.out.println(random.nextBoolean());
			System.out.println(random.nextInt());
			System.out.println(random.nextDouble());
			System.out.println(random.nextInt(100));
		}
		
	}

}
