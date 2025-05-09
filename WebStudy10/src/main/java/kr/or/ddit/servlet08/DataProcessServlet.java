package kr.or.ddit.servlet08;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.vo.DummyData08VO;

@WebServlet("/08/dataProcess")
public class DataProcessServlet extends HttpServlet{
	private DummyData08VO requestToCommandObject(HttpServletRequest req) throws ServletException {
		Map<String, String[]> parameterMap = req.getParameterMap();
		DummyData08VO vo = new DummyData08VO();
		
		try {
			BeanUtils.populate(vo, parameterMap);
			return vo;
		} catch (IllegalAccessException | InvocationTargetException e) {
			// 예외 전환
			throw new ServletException();
		}
	}
	
	private boolean validate(DummyData08VO vo) {
		boolean valid = true;
		if(vo.getP1() == null || vo.getP1().isEmpty())	valid = false;
		if(vo.getP2() == null || vo.getP2().isEmpty())	valid = false;
		return valid;
	}
	
	private DummyData08VO requestToCommandObjectFromJson(HttpServletRequest req) throws IOException{
		BufferedReader reader = req.getReader();
		Gson gson  = new Gson();
		
		DummyData08VO vo = gson.fromJson(reader, DummyData08VO.class);
		return vo;
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		super.service(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8"); // 한글이 깨져도 body 가 있을때만 사용해야함!

		
		DummyData08VO vo = requestToCommandObject(req);
		
		// 검증하는 것을 메서드를 분리함!(책임을 쪼갬!)
		boolean valid = validate(vo);
		
		if(valid) {
			System.out.println(vo);
			String message = "<html><body>정상 처리 되었음.</body></html>";
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(message);
		}else {
			resp.sendError(400,"파라미터 누락");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DummyData08VO vo = requestToCommandObject(req);
		
		// 검증하는 것을 메서드를 분리함!(책임을 쪼갬!)
		boolean valid = validate(vo);
		
		if(valid) {
			System.out.println(vo);
			String message = "<html><body>정상 처리 되었음.</body></html>";
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(message);
		}else {
			resp.sendError(400,"파라미터 누락");
		}
	}
	
	// Put요청에서는 파라미터를 받을 수 없음!
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DummyData08VO vo = requestToCommandObjectFromJson(req);
		
		// 검증하는 것을 메서드를 분리함!(책임을 쪼갬!)
		boolean valid = validate(vo);
		
		if(valid) {
			System.out.println(vo);
			String message = "<html><body>정상 처리 되었음.</body></html>";
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(message);
		}else {
			resp.sendError(400,"파라미터 누락");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("삭제 완료");
		// accept 요청 헤더와 content-type 응답 헤더는 일치 해야함
		resp.setContentType("application/json;charset=utf-8");
		Map<String, Object> map = Map.of("success", true, "message", "삭제완료");
		String json = new Gson().toJson(map);
		resp.getWriter().print(json);
//		String message = "<html><body>정상 처리 되었음.</body></html>";
//		resp.setContentType("text/html;charset=utf-8");
//		resp.getWriter().print(message);
	}
	
	private void mine(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String p1;
		String p2;
		try {
			p1 = Optional.ofNullable(req.getParameter("p1"))
							.filter(s-> s!="")
							.orElseThrow();
			p2 = Optional.ofNullable(req.getParameter("p2"))
							.filter(s-> s!="")
							.orElseThrow();
		} catch (Exception e) {
			// 둘 중 하나라도 누락시, 400 에러 전송
			resp.sendError(400, e.getMessage());
			return;
		}
		
		// 두 파라 미터가 모두 전달된 경우
		System.out.printf("%s : %s\n", "p1", p1);
		System.out.printf("%s : %s\n", "p2", p2);
		// 두 파라미터를 콘솔에 출력한 이후 하단의 메시지를 최종 응답으로 전송
		resp.setContentType("text/html;charset=utf-8");
		String message = "<html><body>정상 처리 되었음.</body></html>";
		resp.getWriter().print(message);
		// 두 파라 미터가 모두 전달된 경우

	}
	
}
