package kr.or.ddit.timeapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TimeApiTest {
	@Test
	void testCalendar() {
//		Locale locale = Locale.forLanguageTag("ko-KR");
		Locale locale = Locale.GERMANY;
		System.out.println(locale);
		YearMonth thisMonth = YearMonth.now();
		LocalDate firstDate = thisMonth.atDay(1);
		
		WeekFields wfs = WeekFields.of(locale);
		DayOfWeek firstWeek = wfs.getFirstDayOfWeek();
		System.out.printf("===> %s \n",firstWeek);
		for(int i=1; i<=7; i++) {
			System.out.println(firstWeek);
			firstWeek = firstWeek.plus(1);
		}
		
		int dayCnt = firstDate.get(wfs.dayOfWeek());
		System.out.println(dayCnt  + "번째 요일");
		int offset = dayCnt - 1;
		LocalDate endDate = thisMonth.atEndOfMonth();
		LocalDate turnDate = firstDate.minusDays(offset);

		for(int row=1; row<=6; row++) {
			for(int col=1; col<=7; col++) {
				System.out.println(turnDate);
				turnDate = turnDate.plusDays(1);
			}
		}
//		while(turnDate.isBefore(endDate) || turnDate.isEqual(endDate)) {
//			System.out.println(turnDate);
//			turnDate = turnDate.plusDays(1);
//		}
	}
	@Test
	@Disabled
	void testAfter8() {
		// UTC(GMT) 표준 방식에 따라 시간 표현
		LocalDateTime now = LocalDateTime.now();
		// 시간의 상태값을 변경 불가, immutable 객체
		// 월의 범위(1~12)
		System.out.println(now.getMonthValue());
		
		LocalDate today = LocalDate.now();
		System.out.println(today);
		System.out.println(LocalDate.from(now));
		System.out.println(YearMonth.now());
		System.out.println(YearMonth.from(today));
		System.out.println(YearMonth.from(now));
		System.out.println(Year.now());
		System.out.println(Year.from(now));
	}
	@Test
	@Disabled
	void testBefore8() {
		
		// java 8 이전의 시간 표기 방식
		// 1970.1.1.0.0.0 이후의 밀리세컨드 경과 값으로 시간을 표현하는 방식을 사용했음 - cpoch time
		
		Date now = new Date();
		System.out.println(now.getTime());
		
		// 시간의 상태값을 변경할 수 있는 mutable 객체
		now.setMonth(4);
		System.out.println(now);
	}

}
