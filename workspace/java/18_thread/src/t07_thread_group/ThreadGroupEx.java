package t07_thread_group;

public class ThreadGroupEx {

	public static void main(String[] args) {
		ThreadGroup group = new ThreadGroup("MyGroup");
		Thread threadA = new WorkThread(group,"WorkThreadA");
		Thread threadB = new WorkThread(group,"WorkThreadB");
		
		threadA.start();
		threadB.start();
		
		System.out.println("[MyGroup List 정보]");
		group.list();		// 스레드 그룹에 포함된 스레드 정보 출력
		System.out.println();
		
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		mainGroup.list(); // mainGroup > MyGroup이기 때문에 main이 종료되면 모든 스레드 종료됨
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		group.interrupt(); 	// 그룹안에 있는 스레드 모두 종료
		
		
	}// main 종료

}
