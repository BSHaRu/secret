package t04_synchronized;

public class Calculator {

	private int memory;

	public int getMemory() {
		return memory;
	}

//	public synchronized void setMemory(int memory) // 여기에 synchronized를 넣고 사용해도 되고
	public void setMemory(int memory) {			// 해당 블록에다가 synchronized()을 넣어서 사용해도 된다.
		synchronized(this) { // this == Calculator
			this.memory = memory;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			//현재 setMemory를 사용중인 스레드 정보를 출력
			String name = Thread.currentThread().getName();
			System.out.println(name + " : " + this.memory);
		}
		
	}// set 종료
	
}
