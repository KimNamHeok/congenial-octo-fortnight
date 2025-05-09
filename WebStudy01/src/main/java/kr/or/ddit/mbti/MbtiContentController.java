package kr.or.ddit.mbti;

import java.io.IOException;
import java.util.Properties;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mbti")
public class MbtiContentController extends HttpServlet{
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties mbtiProps = (Properties) application.getAttribute("mbtiProps");
//		1. 파라미터 수신(mbtiType)
		String mbtiType = req.getParameter("mbtiType");
		String layout = req.getParameter("layout");
//		2. 검증
		if(mbtiType==null || mbtiType.isEmpty()) {
//			1) 필수 파라미터 검증 -> 400
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			return;
		}
		if(!mbtiProps.containsKey(mbtiType)) {
//			2) 유효한 타입인지 여부 검증 -> 404
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, 
						String.format("%s 에 해당하는 mbti 는 없음", mbtiType));
			return;
		}
//		3. MBTI 컨텐츠 파일로 흐름 이동.
		String prefix = "/WEB-INF/views/mbti/";
		String suffix = ".html";
		String path = prefix + mbtiType + suffix;
//		4. 이동 방식 결정.
//		1) redirect ?? : 불가능
//		2) include ?? 가능, 불필요
//		3) forward ?? 가능
		String contentPage = path;
		if(layout!=null&&layout.equals("none")) {
			// 비동기 요청으로 fragment 를 요청한 경우.
			req.getRequestDispatcher(path).forward(req, resp);
		}else {
			// 동기 요청으로 모듈화된 페이지를 요청한 경우.
			req.setAttribute("contentPage", contentPage);
			req.getRequestDispatcher("/WEB-INF/views/mbti/mbtiModule/layout.jsp").forward(req, resp);
		}
		
	}
}
