package kr.or.ddit.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * cross site 에서 요청되는 rest api 에 대해 cors 헤더를 추가하기 위한 필터.
 */
public class CorsFilter extends HttpFilter{
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//*****
		// CORS 헤더 설정
		res.setHeader("ACCESS-CONTROL-ALLOW-ORIGIN", "http://localhost:6060");
		res.setHeader("ACCESS-CONTROL-ALLOW-METHODS", "GET,POST,PUT,DELETE");
		res.setHeader("ACCESS-CONTROL-ALLOW-HEADERS", "content-type");
		//*****
		chain.doFilter(req, res);
	}
}
