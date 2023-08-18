package c.extend;

public class MyClass implements C {

	public static void main(String[] args) {
		MyClass myClass = new MyClass();
		myClass.methodB();	// 여기서는 methodC에 있는 default methodB가 실행되는거임
	}

	@Override
	public void methodA() {
		
	}

	/*
	@Override
	public void methodB() {
		
	}
	*/
	@Override
	public void methodC() {
		
	}

}
