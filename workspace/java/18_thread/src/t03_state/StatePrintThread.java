package t03_state;

public class StatePrintThread extends Thread {

	Thread targetThread;
	
	StatePrintThread(Thread targetThread){
		this.targetThread=targetThread;
	}
	
	public void run() {
		while(true) {
			Thread.State state = targetThread.getState();
			System.out.println("target state : " + state);
			if(state == Thread.State.NEW) {
				targetThread.start();
			}// if 종료
			if(state == Thread.State.TERMINATED) {
				break;
			}// if 종료
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
			
		}// while 종료
	}// run 종료
}
