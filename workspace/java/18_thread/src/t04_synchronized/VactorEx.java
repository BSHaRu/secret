package t04_synchronized;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VactorEx {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		
//		List<String> array = new ArrayList<>(); // ArrayList로 사용하면 동기화가 안되서 중간에 오류가 발생할 수 있음
		List<String>array = new Vector<>();
		
		Thread t1 = new Thread() {
			public void run() {
				for(int i=0; i<10000; i++) {
					array.add("홍길동");
				}//for 종료
				System.out.println("t1 종료");
			} // run 종료
		}; 
		t1.start();
		
		Thread t2 = new Thread() {

			@Override
			public void run() {
				for(int i=0; i<10000; i++) {
					array.add("천재");
				}// for 종료
				System.out.println("t2 종료");
			} // run 종료
		}; // t2 종료
		t2.start();
		
		while(true) {
			if(t1.getState() == Thread.State.TERMINATED 
					&& t2.getState() == Thread.State.TERMINATED) {
				System.out.println("t1, t2 작업 종료");
				System.out.println("array : " + array.size());
				break;
			}
		}
		
		System.out.println("Main 종료");
	} // main 종료

}
