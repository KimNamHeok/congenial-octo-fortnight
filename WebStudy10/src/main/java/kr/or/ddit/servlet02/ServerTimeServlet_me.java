package kr.or.ddit.servlet02;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value="/server-time_me")
//@WebServlet(loadOnStartup = 1, value="/server-time_me")
public class ServerTimeServlet_me extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		// 위에 것을 살려놔야지 servlet에 있는걸 온전하게 사용할 수 있음.
		System.out.printf("%s 객체 초기화", this.getClass().getSimpleName());
	}
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Date : epoch time 방식 (특정시점(1970.1.1 00:00:00)을 정해놓고 얼마나 시간이 지났느냐를 측정하는 것임(밀리세컨드로 환산한 시간.)
//		java.time Time Api는 UTC(세꼐 표준시) 방식으로 시간 환산
//		TimeApi 년도만 Year(년) YearMonth(월) LocalDate(일) LocalDateTime(시간) ZonedDateTime(타임존)으로 다 시간단위당 표현하는게 다름. 시간의 단위별로 처리 객체가 분리됨.
//		new Date()로 만들어서 함. 그렇지만 자바의 timeapi는 LocalDate.now();로 됨.
//		생성자 대신 객체를 생성하는  factory 메소드를 활용함. immutable(불변) 객체로 운영됨.==> setter가 없음
//		
//	}
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, res);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LocalDateTime now = LocalDateTime.now();
		
		resp.getWriter().print(now);
	}
}
