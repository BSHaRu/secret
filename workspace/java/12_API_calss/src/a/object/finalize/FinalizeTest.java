package a.object.finalize;

public class FinalizeTest {

	public static void main(String[] args) {

		for(int i=0; i<50; i++) {
			Counter counter = new Counter(i);
			counter = null;
			System.gc();
		}
		
	} // main 종료

}
