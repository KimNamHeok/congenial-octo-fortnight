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
	private Locale locale; // 해당 월의 1일
	private YearMonth targetMonth; // 해당 월의 마지막 날
	@ToString.Exclude
	private DayOfWeek fdow; // 달력을 표현할 언어
	private LocalDate firstDate; // 달력의 년도와 월
	private LocalDate endDate; // 해당 언어에서 첫번째 요일(일? 월?)
	private LocalDate turnDate; // 달력의 출력할 첫번째 날짜
	private TextStyle textStyle; // 출력형태 ex) 월요일 혹은 월
	private LocalDate today;
	
	public CalendarData(Locale locale, YearMonth targetMonth, ZoneId zone) {
		super();
		this.locale = locale;
		this.targetMonth = targetMonth;
		
		WeekFields wfs = WeekFields.of(locale);
		fdow = wfs.getFirstDayOfWeek();
		
		firstDate = targetMonth.atDay(1);
		
		
		endDate = targetMonth.atEndOfMonth();
		
		int offset = firstDate.get(wfs.dayOfWeek()) -1;
		turnDate = firstDate.minusDays(offset);
		
		textStyle = TextStyle.FULL;
		
		today = LocalDate.now(zone);
	}
	

}
