package kr.or.ddit.mbti;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Properties;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * HttpServlet
 * HttpServletRequest
 * HttpServletResponse
 * HttpSession
 * ServletContext : 하나의 어플리케이션(컨텍스트)내에서 싱글턴
 */
@WebServlet("/mbti")
public class MbtiContentController extends HttpServlet{
	
	private Properties mbtiProps;
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//mbtiProps를 가져올수 있는 방법 고민
//		HttpSession session = req.getSession();
//		mbtiProps = (Properties)session.getAttribute("mbtiProps");
		mbtiProps = (Properties)application.getAttribute("mbtiProps");
		String layout = req.getParameter("layout"); // 필수 파라미터가 아니고 optional 파라미터
		
		// mbtiType 파라미터 넘어오는 값 확인
		String mbti=null;
		try {
			mbti = Optional.ofNullable(req.getParameter("mbtiType"))
									.filter(s->s!="")
									.orElseThrow();
		} catch (Exception e) {
			resp.sendError(400, e.getMessage());
			return;
		}
		
		// mbti값 검증
		if(!mbtiProps.containsKey(mbti)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("%s 에 해당하는 mbti는 없음", mbti));
//			resp.sendError(404, "존재하지 않는 mbti입니다.");
			return;
		}
		
		// 이동
//		StringBuffer url = new StringBuffer();
//		url.append("/WEB-INF/views/mbti/");
//		url.append(mbti);
//		url.append(".html");
		
//		req.getRequestDispatcher(url.toString()).forward(req, resp);
		String prefix = "/WEB-INF/views/mbti/";
		String suffix =".html";
		String path = prefix + mbti + suffix;
		// location에는 WEB-INF경로가 존재할 수 없다
		/*
		 	이동방식 결정
		 	redirect ?? : 불가능
		 	include ?? : 가능, 불필요(요청에 대한 응답만 주면 되기때문에)
		 	forward ?? : 가능
		 	
		 */
		String contentPage = path;
		
		if(layout !=null && layout.equals("none")) {
			// 비동기 요청일때, 페이지를 모듈화 할 필요가 없을때 사용
			req.getRequestDispatcher(path).forward(req, resp);
		}else {
			// 동기 요청일때, 모듈화 할때 사용
		req.setAttribute("contentPage", contentPage);
		req.getRequestDispatcher("/WEB-INF/views/mbti/mbtiModule/layout.jsp").forward(req, resp);
			
		}
	}
	
}
