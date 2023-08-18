package g.formatter;

import java.text.DecimalFormat;

public class DecimalFormatEx {

	public static void main(String[] args) {
		double num = 1234567.849;
		DecimalFormat df = new DecimalFormat("0"); // 소숫점 자리 없이 표현 해달라. -> 반올림함
		String str = df.format(num);	// 그걸 문자열로 반환해라
		System.out.println(str);
		
		df = new DecimalFormat("0.0"); 	// 소숫점 첫째 자리까지 표현해달라 
		str = df.format(num);
		System.out.println(str);
		
		df = new DecimalFormat("0000000000.00"); // 정수값을 10자리로 표현하고, 남는자리는 0으로 채우고 소숫점 둘째자리에까지 표현해달라.
		str = df.format(num);					// -> 0말고 기호를 사용할수도 있다곤 함.
		System.out.println(str);
		
		df = new DecimalFormat("#.##"); // 소숫점 둘째짜리 까지 표현 -> 0.00을 #으로 표현한거임
		str = df.format(num);
		System.out.println(str);
		
		df = new DecimalFormat("#,###,###.##"); // 3자리마다 ,를 찍어서 표현하고 둘째짜리까지 표현
		str = df.format(num);
		System.out.println(str);
		
		// \u00A4 : 유니코드 원화 표시
		df = new DecimalFormat("\u00A4 #,###원"); // 원화표시 하고, 3자리마다 ,찍어주고 마지막에 원이라고 적어달라.
		str = df.format(num);
		System.out.println(str);
		
	}

}
