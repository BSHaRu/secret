package t07_thread_group;

public class WorkThread extends Thread {

	public WorkThread(ThreadGroup group, String threadName) {
		// 생성자를 통해서 스레드 그룹과 스레드 이름을 초기화
		super(group,threadName);
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(getName()+"-interrupted!");
				break;
			} 
		} // while 종료
		System.out.println(getName()+" 종료");
		System.out.println();
	} // run 종료
}
