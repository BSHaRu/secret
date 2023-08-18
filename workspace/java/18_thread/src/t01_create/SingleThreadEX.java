package t01_create;

import java.awt.Toolkit;

public class SingleThreadEX {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		// 현재 명령문이 수행되고 있는 thread객체를 반환
		System.out.println(Thread.currentThread()); // [thread 그룹, 우선순위, thread 이름]반환
		for(int i=0; i<5; i++) {
			System.out.println("띵!");
			try {
				Thread.sleep(500);	// 해당 thread를 해당 시간만큼 기능을 정지시킨다.(ms으로 받음)
			} catch (InterruptedException e) { }
		}
		
		// 운영체제 하드웨어 제어
		Toolkit tool = Toolkit.getDefaultToolkit(); // 소리를 제어 할 수 있음
		for(int i=0; i<5; i++) {
			tool.beep();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
		
		System.out.println("Main 종료");
	}// main 종료
}
