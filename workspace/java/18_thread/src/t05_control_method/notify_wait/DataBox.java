package t05_control_method.notify_wait;

public class DataBox {

	private String data;

	public synchronized String getData() {
		if(this.data == null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		} // if 종료
		String value = this.data;
		this.data = null;
		System.out.println("읽은 데이터 : " +value);
		notify();	// notify() : 자고 있는 친구 깨워줌(불침번) - 동기화 내부에서만 사용가능  
		return value;
	}

	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}// if 종료
		this.data = data;
		System.out.println("생성한 데이터 : " + data);
		notify();
	}
	
}
