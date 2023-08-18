package h.time;

import java.util.Calendar;

public class CalendarEx {

	public static void main(String[] args) {
		Calendar now = Calendar.getInstance(); // new로 호출안하고 바로 사용할 수 있는 이유 => 싱글톤패턴이라서 
		
		// 년도
		int year = now.get(Calendar.YEAR); 	// Calendar.YEAR의 YEAR는 상수!
		
		// 월 0~11; 0이 1월 / 11이 12월 => +1해주는거
		int month = now.get(Calendar.MONTH)+1;
		
		//월 중 날짜
		int day = now.get(Calendar.DAY_OF_MONTH);
		
		//주중 날짜
		int week = now.get(Calendar.DAY_OF_WEEK);
		System.out.println(Calendar.SUNDAY); 	// 1 -> 첫째날
		System.out.println(Calendar.SATURDAY); 	// 7 -> 마지막 날
		
		String strWeek = "";
		switch(week) {
			case Calendar.MONDAY :
				strWeek = "월";
				break;
			case Calendar.TUESDAY :
				strWeek = "화";
				break;
			case Calendar.WEDNESDAY :
				strWeek = "수";
				break;
			case Calendar.THURSDAY :
				strWeek = "목";
				break;
			case Calendar.FRIDAY :
				strWeek = "금";
				break;
			case Calendar.SATURDAY :
				strWeek = "토";
				break;
			case Calendar.SUNDAY :
				strWeek = "일";
				break;
		}
		
		int hour = now.get(Calendar.HOUR);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		
		System.out.println(year+"년"+month+"월"+day+"일"+hour+"시"+minute+"분"+second+"초 ("+strWeek+")요일");
		System.out.printf("%d년 %d월 %d일 %d시 %d분 %d초 %s요일",year,month,day,hour,minute,second,strWeek);
		
		
	}

}
