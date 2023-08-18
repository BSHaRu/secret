package t05_control_method.notify_wait;

public class ConsumerThread extends Thread {

	private DataBox dataBox;

	public ConsumerThread(DataBox dataBox) {
		this.dataBox = dataBox;
	}
	
	public void run() {
		for(int i=0; i<=3; i++) {
			String data = dataBox.getData();
			System.out.println("consumerThread : "+data);
			System.out.println("===========================");
		}
	}
	
}
