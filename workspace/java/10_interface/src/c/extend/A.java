package c.extend;

public interface A {
	void methodA();
	
	default void methodB() {
		System.out.println("A interface methodB");
	}
}

interface B{
	void methodB();
}

interface C extends A, B{
	void methodC();

	@Override
	default void methodB() {
		
	}


}