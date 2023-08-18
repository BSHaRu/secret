package t05_control_method.stop;

class PrintThread extends Thread{
	
	private boolean isRun = true;
	
	public void setIsRun(boolean isRun) {
		this.isRun = isRun;
	}

	@Override
	public void run() {
		while(isRun) {
			System.out.println("실행중");
		}
		System.out.println("자원정리");
		System.out.println("실행종료");
	}
	
}

public class StopFlageEX {

	public static void main(String[] args) {
		PrintThread t = new PrintThread();
		t.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		
//		t.stop();	// 사용해도 되지만 stop()은 강제종료라서 앵간하면 사용하지 말라고 하는거임
		t.setIsRun(false);
		
	}// main 종료

}
