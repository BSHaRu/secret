package a_basic;

// 함수형 인터페이스 : 메소드가 하나인 인터페이스 -> 람다식으로 구현 가능
@FunctionalInterface
public interface MyInterface {
	void method();
//	void method2(); // 기능이 2개가 있으면 함수형 인터페이스가 안됨
}
