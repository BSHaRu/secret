package d.wrapper;

import java.util.Objects;

import javax.sql.rowset.CachedRowSet;

public class WrapperEx {

	public static void main(String[] args) {
		// boxing
		Integer obj1 = new Integer(100); // new Integer 생략가능 
		obj1 = Integer.valueOf(100);	 // -> Java 9 version이후에서는 new Integer를 쓰는걸 비추천함  
		obj1 = Integer.valueOf("100");	// 문자열 100을 정수형 100으로 바꿔주는 거임
		obj1 = 100;						// 자동 boxing -> new Integer 생략된거임
//		obj1 = "100";					// 이건 안됨 -> 이건 정수타입에 문자열을 대입하는거라서 당연히 안되는거임
		
		// unBoxing
		int i = obj1.intValue();	// .타입Value() : 해당 타입의 value값을 꺼내오는 방법
		System.out.println(i);
		
		// 자동 unBoxing
		int value = obj1;			// 참조타입이 정수타입에 대입이 되는거라 원래 이상한게 맞지만
		System.out.println(value);	// 자동 unBoxing 때문에 이게 가능한거임.
		System.out.println("======================================");
		
		//valueOf 문자열 or 기본타입의 데이터를 해당 타입의 포장 객체로 반환
		Integer obj2 = Integer.valueOf("600");	// 반환하는 타입이 Integer임. 
		int obj3 = Integer.valueOf("700");		// -> 자동 unBoxing 과정을 거침
		int obj4 = Integer.parseInt("800");		// 반환하는 타입이 int(기본타입)임.
												// -> unBoxing 과정없이 기본타입으로 바로 반환.
		// parse타입 은 이친구들을 가장 많이 사용되긴함.
		double valueDouble = Double.parseDouble("3.14");
		boolean valueBoolean = Boolean.parseBoolean("true");
		
		byte b = 127;
		Byte b1 = Byte.valueOf(b);
		b1 = Byte.valueOf((byte)50);		// byte는 1byte인데 기본 리터널이 int 타입(4byte)라서 타입변경을 해야됨 
		System.out.println(b1);
		b1 = Byte.valueOf("50");			// 문자열을 받을때는 상관없음
		System.out.println(b1);
		System.out.println("======================================");
		
		
		// Character는 한개의 문자를 표현하기 때문에 문자열 대입이 불가능!
		char c = '가';
		Character c1 = Character.valueOf(c);
		System.out.println(c1);
		c1 = Character.valueOf((char)65);	// char는 2byte니깐 위의 byte 처럼 강제 타입변환 해줘야됨
		System.out.println(c1);
//		c1 = Character.valueOf("A");		// 문자열 변환 불가
//		c = Character.parseCharacter("A");	// char는 parse 자체가 없음.
		System.out.println("======================================");
		
		System.out.println(Byte.MIN_VALUE);	// 해당 타입의 최소값 &  해당 타입의 최대값은 상수로 다 가지고 있다.
		System.out.println(Byte.MAX_VALUE);	
		System.out.println(Long.MIN_VALUE);
		System.out.println(Long.MAX_VALUE);
		System.out.println("======================================");
		
		// 각 타입에 맞는 값을 비교하는 compare		// compare는 정적 맴버다.
		int a = Integer.compare(10, 20); // return (x < y) ? -1 : ((x == y) ? 0 : 1);
		System.out.println(a);			// -> compare : 앞에 값이 작으면 -1 | 같으면 0 | 크면 양수로 1
		
		Integer i1 = 10;
		int result = i1.compareTo(5);	// compareTo도 동일하게 비교하는 친구임
		System.out.println(result);
		
		int d1 = Double.compare(10.0, 15.0);	// double로 받아도 compare 자체가 int타입이라서 int타입으로 변환
		System.out.println(d1);	
		double d2 = Double.compare(10.0, 15.0);	// 이렇게 해봐야 int 타입으로 된 상황에서 double로 출력하는거라서
		System.out.println(d2);					// 이미 int로 반환된 후 출력만 double로 하는 상황인거임
		System.out.println("======================================");
		
		char cha = 'A';		// 65;
		char zero = '0';	// 48;
		System.out.println("char compare : " + Character.compare(cha, zero)); // 뒤에 있는 값 - 앞에 있는 값 = 절대값으로 표현
											// -> 48 - 65 = -17인데 절대값이라 17이 나옴
		System.out.println("======================================");
		
		// main 밑에 static void로 상위 객채인 Number를 받았기때문에
		// 각 test를 int | long | double타입 순서대로 출력함 
		test(new Integer(1));
		test(Float.valueOf(3.14f));
		test(3.141592);
		test(11111111111L);
		test(null);
	} // main 종료
	
	// method 안에 method가 정의가 안되기 때문에 main안에 넣으면 오류가 생김
	// 이건 문법적으로 그런거라 그러러니 해야됨
	static void test(Number o) {	// Number는 Byte | Integer | Double | Short | Float | Long의 최상위 객체임
		if(Objects.nonNull(o)) {	// -> Number는 저걸 다 포함하고 있음
			System.out.println(o.intValue());
			System.out.println(o.longValue());
			System.out.println(o.doubleValue());
		}else {
			System.out.println("null값을 활용 할 수 없습니다.");
		}
	}

}
