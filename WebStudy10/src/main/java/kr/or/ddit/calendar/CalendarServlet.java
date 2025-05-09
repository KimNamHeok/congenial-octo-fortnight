package kr.or.ddit.calendar;

import java.io.IOException;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calendar")
public class CalendarServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Locale locale = Optional.ofNullable(req.getParameter("locale"))
//								.map(Locale::new)
//								.orElse(req.getLocale());
//				
//		YearMonth nowMonth = YearMonth.now();
		
//		int year = Optional.ofNullable(req.getParameter("year"))
//				.map(Integer::parseInt)
//				.orElse(nowMonth.getYear());
//		int month = Optional.ofNullable(req.getParameter("month"))
//				.map(Integer::parseInt)
//				.orElse(nowMonth.getMonthValue());
//		YearMonth targetMonth = YearMonth.of(year, month);

		int year = Optional.ofNullable(req.getParameter("year"))
							.filter(mp -> mp.matches("\\d{1,2}"))
							.map(Integer::parseInt)
							.filter(m -> m>=1 && m<=12)
							.orElse(Year.now().getValue());
		
		 YearMonth month = Optional.ofNullable(req.getParameter("month"))
							.filter(yp -> yp.matches("[0-9]+"))
							.map(Integer::parseInt)
							.map(m->YearMonth.of(year, m))
							.orElse(YearMonth.now());
			
		 Locale locale = Optional.ofNullable(req.getParameter("locale"))
		 							.map(Locale::forLanguageTag)
		 							.orElse(req.getLocale());
		 ZoneId zone = Optional.ofNullable(req.getParameter("zone"))
				 				.map(ZoneId::of)
				 				.orElse(ZoneId.systemDefault());
		CalendarData cal = new CalendarData(locale, month,zone);
		req.setAttribute("cal", cal);
		req.getRequestDispatcher("/WEB-INF/views/calendar/viewLayer.jsp").forward(req, resp);
	}
	
}
