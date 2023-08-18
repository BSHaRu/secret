package a.object.finalize;

public class Counter {

	private int no;

	public Counter(int no) {
		this.no = no;
	}

	@Override
	protected void finalize() throws Throwable { // Throwable : 오류에 대한 최상위 객체
		System.out.println(no+"번째 객체의 finalize() 호출");
	}
	
	
}
