package a5;
// a5.A
public class A {
	
	public int field1;
	int field2;
	private int field3;
	
	public A() {
		field1 = 10;
		field2 = 100;
		field3 = 100;
		
		method1();
		method2();
		method3();
	}
	
	public int method1() {
		return field3;
	}
	
	int method2() {
		return field2;
	}
	
	private int method3() {
		return field1;
	}
}
