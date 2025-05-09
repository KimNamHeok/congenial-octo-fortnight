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
            .map(m -> m.getDisplayName(TextStyle.FULL, Locale.KOREAN)) // ✅ 올바른 Lambda 표현식
            .toArray(String[]::new);
        
       locales =  Arrays.stream( Locale.getAvailableLocales())
       		.collect(Collectors.toMap(
	       				l->l.toLanguageTag(),
	       				l-> l.getDisplayName(l),
	       				(v1, v2)->v1
	       				)
       				);
       
       /* 
        * zones =  Arrays.stream(TimeZone.getAvailableIDs())
    		   .map(tz->TimeZone.getTimeZone(tz))
    		   .collect(Collectors.toMap(
    				   tz -> tz.getID(),
    				   tz-> tz.getDisplayName(false, TimeZone.LONG, Locale.KOREAN),
    				   (v1, v2)->v1
    				   )
    				);
    				
    	컬렉션을 안쓸때엔 stream을 쓸수 없다!
       */
       zones = ZoneId.getAvailableZoneIds().stream()
       		.map(TimeZone::getTimeZone)
       		.collect(
    		   Collectors.toMap(
    				   TimeZone::getID,
    				   TimeZone::getDisplayName
    				   )
    		);
       // 메소드 레퍼런스 쓸때
       // 그 인자가 가지고 있는 메서드 호출할때
       // 인자를 받아서 한 객체의 인자값으로 넘겨줄때
       
    }
    
    private String[] months;
    private Map<String,String> locales;
    private Map<String, String> zones;
}