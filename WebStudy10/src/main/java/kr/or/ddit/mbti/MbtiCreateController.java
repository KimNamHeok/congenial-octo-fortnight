package kr.or.ddit.mbti;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mbti/create")
public class MbtiCreateController extends HttpServlet{
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String mbtiType = req.getParameter("mbtiType");
		String mbtiDesc = req.getParameter("mbtiDesc");
		
		boolean valid = true;
		Map<String,String> errors = new HashMap<>(); 
		if(mbtiType==null || mbtiType.trim().isEmpty()) {
			valid= false;
			errors.put("mbtiType","mbti 유형 타입 누락");
		}
		if(mbtiDesc==null || mbtiDesc.isEmpty()) {
			valid = false;
			errors.put("mbtiDesc","mbti 유형 설명 누락");
		}
		
		if(valid) {
			Properties mbtiProps = (Properties)application.getAttribute("mbtiProps");
			mbtiProps.put(mbtiType, mbtiDesc);
			// 처리가 완료된 명령(요청)에 대한 정보를 제거하고,
			// 동일한 요청이 여러번 발생하더라도 실행결과는 동일해야하며(역등성)
			// 갱신된 자원에 대한 정보를 사용자에게 제공할 수 있어야함.
			// P-R-G 패턴으로 처리함
			String location = req.getContextPath() + "/mbti/form";
			resp.sendRedirect(location);
		}else {
			resp.sendError(400, errors.toString());
		}
	}
	
// 	디코딩 설정 필요
//	 2개 파라미터 필수파라미터
//	 누락시 400에러
//	 검증을 통과시 mbti 추가
//	 갱신된 자원 이있으니
//	 다시 select태그를 가지고 있는 form 으로 보내야함.
//	 보내는 방식은 2가지 
//	 역등성의 원리를 생각해서 redirect로만 보내자
//	 최종적으로 mbti formcontroller로 보내자
//	 get방식의 새로운 요청 필요 
//	 prg패턴..
	  
	
}
