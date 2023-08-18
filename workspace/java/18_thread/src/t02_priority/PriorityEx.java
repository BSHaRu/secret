package t02_priority;
			// 우선순위
public class PriorityEx {

	public static void main(String[] args) {
		System.out.println(Thread.MAX_PRIORITY);
		System.out.println(Thread.NORM_PRIORITY);
		System.out.println(Thread.MIN_PRIORITY);

		for(int i=1; i<=10; i++) {
			Thread calc = new CalcThread("THREAD-"+i);
			if(i == 10) {
				calc.setPriority(Thread.MAX_PRIORITY); // 10번째 thread한테 우선순위 젤 빠르게 함
											// -> 우선순위가 젤 빠르다고해서 무조건 빨리 하는게 아니라 "빨리 해주세요" 라고 "부탁"하는거임
			} // if 종료						// => 모든 thread는 왔다갔다 하면서 실행되기 때문에 우선 순위가 높으면 빨리 처리될 "수"가 있다.
			calc.start();
		} // for 종료
	} // main 종료

}
