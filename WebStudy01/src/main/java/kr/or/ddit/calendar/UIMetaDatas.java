package kr.or.ddit.calendar;

import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class UIMetaDatas {
	
	public UIMetaDatas() {
		months = Arrays.stream(Month.values())
			.map(m->
				m.getDisplayName(TextStyle.FULL, Locale.KOREAN)
			).toArray(String[]::new);
		locales = Arrays.stream(Locale.getAvailableLocales())
					.collect(
						Collectors.toMap(
								l->l.toLanguageTag(),
								l->l.getDisplayName(l),
								(v1, v2)->v1
							)
					);
		zoneIds = ZoneId.getAvailableZoneIds().stream()
			.map(TimeZone::getTimeZone)
			.collect(
				Collectors.toMap(TimeZone::getID, TimeZone::getDisplayName)
			);
	
	}

	private String[] months;
//  ko-KR 한국어
	private Map<String, String> locales;
//	Asia/Seoul 한국표준시간
	private Map<String, String> zoneIds; 
}









