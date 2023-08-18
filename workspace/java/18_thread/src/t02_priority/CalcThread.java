package t02_priority;

public class CalcThread extends Thread {

	public CalcThread(String name) {
		super.setName(name);
	}
	
	@Override
	public void run() {
		for(int i=0; i<2000000000; i++);
		System.out.println(super.getName()+" : "+super.getPriority());
	}
	
}
