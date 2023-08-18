package a5;

public class B {

	public B() {
		A a = new A();
		a.field1 = 100;		// public
		a.field2 = 1000;	// default
//		a.field3 = 10000;	// private
		
		a.method1();
		a.method2();
//		a.method3();
	}
	
}
