package a3;
// a3.B
public class B {
	A a = new A(); 			// public
	A a1 = new A(10);		// default
//	A a2 = new A("누구세요");	// private - A class는 알지만 문자열을 받는 A는 private로 
							// 지정했기때문에 다른 class에서는 누군지 모른다
}
