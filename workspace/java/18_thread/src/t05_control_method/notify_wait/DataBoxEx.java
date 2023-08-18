package t05_control_method.notify_wait;

public class DataBoxEx {

	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		Thread pThread = new ProduceThread(dataBox);
		Thread cThread = new ConsumerThread(dataBox);
		pThread.start();
		cThread.start();
		
	}

}
