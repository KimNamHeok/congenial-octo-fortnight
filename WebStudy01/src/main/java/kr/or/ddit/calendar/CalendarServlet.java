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
public class CalendarServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int year = Optional.ofNullable(req.getParameter("year"))
				.filter((yp)->yp.matches("[0-9]+"))
				.map(Integer::parseInt)
				.orElse(Year.now().getValue());
		YearMonth targetMonth = Optional.ofNullable(req.getParameter("month"))
				.filter(mp->mp.matches("\\d{1,2}"))
				.map(Integer::parseInt)
				.filter(m->m>=1&&m<=12)
				.map(m->YearMonth.of(year, m))
				.orElse(YearMonth.now());
		Locale locale = Optional.ofNullable(req.getParameter("locale"))
				.map(Locale::forLanguageTag)
				.orElse(req.getLocale());

		ZoneId zone = Optional.ofNullable(req.getParameter("zone"))
				.map(zp->ZoneId.of(zp))
				.orElse(ZoneId.systemDefault());
		
		CalendarData cal = new CalendarData(locale, targetMonth, zone);
		req.setAttribute("cal", cal);
		req.getRequestDispatcher("/WEB-INF/views/calendar/viewLayer.jsp").forward(req, resp);
	}
}
