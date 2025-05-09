package kr.or.ddit.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = {"locale","targetMonth"})
public class CalendarData {
	
	
	public CalendarData(Locale locale, YearMonth targetMonth, ZoneId zone) {
		super();
		this.locale = locale;
		this.targetMonth = targetMonth;
		
		WeekFields wfs = WeekFields.of(locale);
		fdow = wfs.getFirstDayOfWeek();
		
		firstDate = targetMonth.atDay(1);
		endDate = targetMonth.atEndOfMonth();
		
		int offset = firstDate.get(wfs.dayOfWeek())-1;
		turnDate = firstDate.minusDays(offset);
		
		textStyle = TextStyle.FULL;
		
		today = LocalDate.now(zone);
	}
	
	private Locale locale;	// 달력을 표현할 언어
	private YearMonth targetMonth;	// 달력의 년도와 월
	@ToString.Exclude
	private DayOfWeek fdow;	// 해당 언어에서 첫번째 요일 (ex 5월달 달력인 경우 일요일(4.27.) )
	private LocalDate firstDate; // 해당 월의 1일
	private LocalDate endDate; // 해당 월의 마지막날
	private LocalDate turnDate; // 달력에 출력할 첫번째 날
	private TextStyle textStyle; // 출력 형태 ex) 월요일 혹인 월
	
	private LocalDate today;  // 오늘 날짜
}
