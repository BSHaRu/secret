package my_test;

public class Test2_3 {

	public static void main(String[] args) {
		String[] str = new String[5];
		System.out.println(str[0].equals(str[1]));
		
		/* 
		 euqals()는 문자열이 같은지 비교하는 함수이다.
		 근데 str[0]과 str[1]은 둘 다 입력값이 없는 null 상태인데
		 null은 문자열이 아니라 해당 값이 비어있다는 의미기 때문에
		 equals()함수가 아닌 == 를 써야 한다. 
		 */
		
		/* 오류의 주최는 str[0]임.
		 null은 참조형 타입의 기본 값이다. 
		 모든 기본형 타입(Primitive type)이 기본(default) 값을 갖는 것처럼 
		 참조형 타입(Reference type)은 기본 값으로 null을 갖는다. 
		 참조 타입의 배열은 생성 시 null값으로 원자 값을 초기화 한다.
		 java에서 null 이란 참조타입의 변수에 참조하는 값이 없다는 걸 표현하는 자료형이다. 
		 참조하는 값이 없는 변수를 참조하여 연산하려고 할 때 발생한다.
		 */
	}

}
