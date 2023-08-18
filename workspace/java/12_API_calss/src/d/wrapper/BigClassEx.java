package d.wrapper;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigClassEx {
	// long 타입의 값을 넘는 수를 계산하는 거
	public static void main(String[] args) {
		BigInteger bi = new BigInteger(		// 수학과 관련되어있기때문에 java.math에 있음
				"1000000000000000000000000000000000000000000000000000000000000000000000000"
		);	//정수보다 큰 값을 다뤄야해서 "문자열"로 표현한다.
		System.out.println(bi.toString());
		System.out.println(bi.intValue());	// Number에 속해있기때문에 해당 value를 가지고는 있음
											// -> 4byte로 짤라내니 0밖에없어서 0이 출력되는거임
		
		BigInteger bi2 = new BigInteger("123456789000");
		System.out.println(bi2);		// toString() 없어도됨
		
		BigInteger result = new BigInteger("0"); 
		
		// A.add(B) = A + B
		result = bi.add(bi2);	
		System.out.println(result);
		
		// A.subtract(B) = A - B
		result = bi.subtract(bi2);
		System.out.println(result);
		
		// A.multiply(B) = A * B
		result = bi.multiply(bi2);
		System.out.println(result);
		
		// A.divide(B) = A / B
		result = bi.divide(bi2);
		System.out.println(result);
		
		double d = 3.1459268418516347894516565456465464464646546546545646545465654654654564654654654646546546546545646546546546546546546546546546545646465465465465465465465465465465465465464646546546544654;
		System.out.println(d); // 쓸때는 이렇게 정해지지만, 출력은 해당 double의 저장 공간만큼 표현됨
		
		BigDecimal bd = new BigDecimal(
				"1.66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666"
		);
		System.out.println(bd);
		
		BigDecimal bd2 = new BigDecimal(
				"2.11111111111111111111111111111111111111111111111111111111111111111"
		);
		System.out.println(bd2);
		System.out.println("============================================================================");
		System.out.println(bd.multiply(bd2));
		
		Number n = bd2;
		System.out.println(bd2.doubleValue());
	}

}
