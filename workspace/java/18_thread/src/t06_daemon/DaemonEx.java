package t06_daemon;

import java.util.Map;
import java.util.Set;

class AutoSaveThread extends Thread{
	
	public void save() {
		System.out.println("작업 내용을 저장함.");
	}

	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			save();
		}
	}
}

public class DaemonEx {

	public static void main(String[] args) {
		AutoSaveThread t = new AutoSaveThread();
		t.setDaemon(true);
		t.start();
		
		System.out.println(t.isDaemon());
		
		ParentThread p = new ParentThread(new ChildThread());
		p.start();
		
		Map<Thread,StackTraceElement[]> map = Thread.getAllStackTraces();
		Set<Thread> threads = map.keySet();
		for(Thread thread : threads) {
			String isDaemon = (thread.isDaemon()) ? "(DAEMON Thread)" : "(주Thread)";
			System.out.println("Name : "+thread.getName()+isDaemon+" : "+ thread.getPriority());
			System.out.println("\t 소속그룹 : "+thread.getThreadGroup().getName());
			System.out.println();
		}
		
		System.out.println("Main Thread 종료");
	}

}
