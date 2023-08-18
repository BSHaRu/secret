package t01_create;

public class BeepThreadEx {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		Thread t = new BeepThread();
		t.start(); // 새로운 작업 스레드 생성
//		t.run(); // 이건 run()의 함수 호출이지 새로운 스레드를 생성하는게 아님.
		
		Runnable run = new PrintTask();
		Thread t1 = new Thread(run);
		t1.start();
		
		for(int i=0; i<5; i++) {
			System.out.println("띵");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		} // for 종료
		
		// Runnable은 어짜피 run()밖에 안들고있기때문에 굳이 class를 만들고 참조할필요가 없음
		// -> 이렇게 그냥 내부에 정의해도 크게 문제 될게 없다.
		Runnable workTask = new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<5; i++) {
					System.out.println("t2");
				}
			}
		};
		
		System.out.println("Main 종료");
	}// main 종료
}
