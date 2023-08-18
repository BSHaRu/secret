package t03_state;

public class StateEx {

	public static void main(String[] args) {
		
		// NEW
		TargetThread targetThread = new TargetThread();
		
		Thread print = new StatePrintThread(targetThread);
		print.start();
		System.out.println("Main 종료");
		
	} // main 종료

}
