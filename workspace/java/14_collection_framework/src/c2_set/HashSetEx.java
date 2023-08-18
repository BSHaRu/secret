package c2_set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetEx {

	public static void main(String[] args) {
		Set<String> s1 = new HashSet<>();
		s1.add("A");
//		s1.add(0,"B"); // index 번호가 없기때문에 이거 안됨
		s1.add("D");
		s1.add("B");
		s1.add("C");
		System.out.println(s1);
		
		Iterator<String> iterator = s1.iterator();	// 이걸 써야된다네??
		while(iterator.hasNext()) {					// hasNext() : 꺼내올 값이 존재하면 true 반환 없음 false 반환
			String s = iterator.next();
			System.out.print(s + " ");
		}
		System.out.println();
		System.out.println(s1.contains("B"));
		s1.add("C");				// set안엔 중복저장 안됨. 유일한 1개의 값만 가짐
		System.out.println(s1);
		
		System.out.println("========================================");
		Set<String> s2 = new HashSet<>();
		s2.add("A");
		s2.add("D");
		s2.add("E");
		System.out.println(s2);
		
		Set<String> s3 = new HashSet<>(s1); // s3에 s1이 생성됨
		// 합집합
		s3.addAll(s2);
		System.out.println(s3);				// s3 = s1 + s2 가 된거임 (중복은 제외하고 ㅇㅇ)
		
		System.out.println("========================================");
		Set<String> s4 = new HashSet<>(s1);
		System.out.println("s4 : " + s4);
		System.out.println("s2 : " + s2);
		
		boolean isOK = s4.retainAll(s2); // retain : 교집합 -> s4랑 s2 중복된 값만 남겨라 
		System.out.println("isOK : " + isOK);
		System.out.println("s4 : " + s4);
		
		System.out.println("========================================");
		/*
		for(String s : s1) {				// index 번호가 없으니깐 향상된 for문으로 접근
			System.out.print(s + " ");
			if(s.equals("A")) {
				s1.remove(s);				// 이렇게 접근은 읽을 수는 있지만, 내용 수정은 못함
			}								// -> 1회 반복 하는 순간 s1의 크기가 변경되기때문에 2회부터는 크기가 달라져서 오류발생함
		} 									// => 대신 s1.remove(s); 이후 break;를 넣으면 1회만 실행하기때문에 오류 발생안함 
		System.out.println();		*/		// ==> 그래도 이것보단 iterator로 접근하는게 좋으니 아래와 같은 코드를 쓰는게 좋다.
		
		Iterator<String> itr2 = s1.iterator();
		while(itr2.hasNext()) {
			String s = itr2.next();	 		// 이건 s1값에 접근하는게 아니라 iterator로 접근하는거다 
//			s1.remove(s);					// 바로 위와 같은 의미이지만, 이는 s1에 직접접근하는거라서 오류 발생함
			System.out.print(s+ " ");		// -> s1을 반복하는게 아니라 iterator를 반복하는거다
			if(s != null) itr2.remove();	// => 크기가 변경되어도 아무런 문제가 없는거임.
		} // while문 종료
		System.out.println(s1.size());
		
		System.out.println("========================================");
		s1.add(null);
		s1.add("hi");
		s1.add(null);
		System.out.println(s1);	// null도 저장은 되지만, 중복저장은 안됨
		
	} // main 종료

}
