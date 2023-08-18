package t04_synchronized;
					// 동기화
class UserThread extends Thread{
	private Calculator calc;
	private int value;
	
	public void setClaculator(Calculator calc, int value) {
		this.calc = calc;
		this.value = value;
	}
	
	@Override
	public void run() {
		calc.setMemory(value);
	}
	
}

public class SynchronizedEx {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		UserThread u1 = new UserThread();
		u1.setName("USER-1");
		u1.setClaculator(calc, 100);
		u1.start();
		
		UserThread u2 = new UserThread();
		u2.setName("USER-2");
		u2.setClaculator(calc, 50);
		u2.start();
		
	} // main 종료

}
