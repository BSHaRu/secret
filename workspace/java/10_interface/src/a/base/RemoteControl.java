package a.base;

public interface RemoteControl { // 상수 && 추상 method로 구성되어있음

	public static final int MAX_VALUE = 10; 
	int MIN_VALUE = 0;		// public static final 생략 가능함
	
	public abstract void turnOff();
	void turnOn(); 			// public abstract 생략 가능함
	
//	void setValue(int value) { 	// 일반 method는 불가능 | 추상 method만 가능
//	}
	void setValue(int value);
	
	
}
