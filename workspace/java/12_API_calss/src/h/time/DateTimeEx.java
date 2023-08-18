package h.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeEx {

	public static void main(String[] args) {
		LocalDate toDay = LocalDate.now();
		LocalTime present = LocalTime.now();
		System.out.println(toDay);
		System.out.println(present);
		
		LocalDate birthDay = LocalDate.of(1982, 06, 07);
		LocalTime birthTim = LocalTime.of(02, 02, 01); 	// 시 | 분 | 초
		System.out.println(birthDay);
		System.out.println(birthTim);
		// -> .now() : 현재시간 | .of() : 해당 날짜 입력가능
		
		System.out.println(toDay.getYear());
		System.out.println(toDay.getMonthValue());
		System.out.println(toDay.getDayOfMonth());
		System.out.println(toDay.getDayOfWeek());
		System.out.println(toDay.getDayOfYear());
		System.out.println(present.getHour()+":"+present.getMinute()+":"+present.getSecond());
		
		System.out.println();
		LocalDate target = toDay.minusYears(1); // 1년전
		System.out.println(target);
		
		target = toDay.plusYears(10); 	// 10년 후
		System.out.println(target);
		
		target = toDay.minusMonths(5);
		System.out.println(target);
		
		target = toDay.minusDays(10);
		System.out.println(target);
		
		LocalTime targetTime = present.minusHours(10);
		System.out.println(targetTime);
		
		targetTime = present.minusMinutes(10);
		System.out.println(targetTime);
		
		targetTime = present.minusSeconds(10);
		System.out.println(targetTime);
		
		System.out.println();
		// 1.8 버전 이후는 이거 추가됨
		LocalDateTime targetDateTime = LocalDateTime.now();
		System.out.println(targetDateTime);
		
		targetDateTime = targetDateTime.withYear(1993).withMonth(5).withDayOfMonth(16)
										.withHour(13).withMinute(30).withSecond(20);
		System.out.println(targetDateTime);

		System.out.println();
		
	}

}
