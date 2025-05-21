package kr.or.ddit.filter;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 	웹 필터 ? 요청에 대한 전처리와 응답에 후처리를 담당하는 객체.
 *  : 1) 실제 요청을 처리하는 컨트롤러들에서 반복적인 처리가 필요한 경우, 필터를 통해 중복을 해결할 수 있음.
 *    2) 변경할 수 없는 request 에 대해 wrapper request 를 정의하여,
 *       필요한 수정 사항을 반영할 수 있음. 	 
 *  사용 단계
 *  1. Filter 나 HttpFilter 를 상속.
 *  2. doFilter 내부에서 매 요청에 대한 실제 필터링 작업 수행.
 *  	- chain.doFilter 에 의해 제어 이동.
 *  3. WAS(서블릿 컨테이너)에 등록 : web.xml : filter 엘리먼트로 등록
 *  4. 필터링할 수 있는 요청에 대한 매핑 설정. : web.xml : filter-mapping 엘리먼트로 매핑 설정    
 */
public class CharacterEncodingFilter extends HttpFilter {
	private String encoding;

	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		encoding = Optional.ofNullable(config.getInitParameter("encoding"))
				        .orElse("UTF-8");
	}	 
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		req.setCharacterEncoding(encoding);
		res.setCharacterEncoding(encoding);
		
		chain.doFilter(req, res);
		
	}
}










