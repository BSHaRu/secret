package t08_thread_pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultCallableEx {

	public static void main(String[] args) {
		
		int core = Runtime.getRuntime().availableProcessors();
		System.out.println("컴퓨터 논리 코어 개수 : " + core);
		
		ExecutorService ex = Executors.newFixedThreadPool(core);
		System.out.println("[ 작업 처리 요청 ]");
		Callable<Integer> call = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				int sum = 0;
				for(int i=1; i<=10; i++) {
					sum += i;
				}
				Thread.sleep(2000);
				return sum;
			} // call 종료
		}; // Callable 종료
		
		Future<Integer> future = ex.submit(call);
		
		try {
			System.out.println("blocking");
			int sum = future.get();
			System.out.println("[ 작업 처리 결과 ] : " + sum);
			System.out.println("작업 처리 완료");
		} catch (InterruptedException e) {
			System.out.println("스레드 실행 종료 예외 : " + e.getMessage());
		} catch (ExecutionException e) {
			System.out.println("스레드 실행 시 발생 오류 예외 : " + e.getMessage());
		}
		System.out.println("call back 완료");
		System.out.println("종료");
		ex.shutdownNow();
		
	} // main 종료

}
