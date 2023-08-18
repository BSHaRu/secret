package t05_control_method;

public class JoinEx {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		Thread t1 = new Thread(new Runnable() {
			
			private int sum;
			
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				for(int i=0; i<=10; i++) {
					sum += 1;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					System.out.println(name+" sum : "+ sum);
				} // for 종료
				System.out.println(name + " 종료");
			} // run 종료
		});
		t1.setName("SumThread");
		t1.start();
		
		try {
			t1.join();
		} catch (InterruptedException e) {}
		
		System.out.println("Main 종료");
	}

}
