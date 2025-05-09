package kr.or.ddit.servlet08;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.vo.DummyData08VO;

@WebServlet("/08/dataProcess")
public class DataProcessServlet extends HttpServlet {
	private DummyData08VO requestToCommandObject(HttpServletRequest req) throws ServletException {
		// 두개의 파라미터가 모두 전달된 경우,
		Map<String, String[]> parameterMap = req.getParameterMap();
		DummyData08VO vo = new DummyData08VO();
		try {
			BeanUtils.populate(vo, parameterMap);
			return vo;
		} catch (IllegalAccessException | InvocationTargetException e) {

			// 예외 전환
			throw new ServletException(e);
		}
	}

	private boolean validate(DummyData08VO vo) {
		boolean valid = true;
		if (vo.getP1() == null || vo.getP1().isEmpty()) {
			valid = false;
		}
		if (vo.getP1() == null || vo.getP2().isEmpty()) {
			valid = false;
		}
		return valid;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 모든 do계열 메소드는 서비스를 통해 호출되니 여기서 특수문자 처리해버리면 됨
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");  바디가 있을 때만 작동 -> 여기서(get요청) 의미X
		DummyData08VO vo = requestToCommandObject(req);
		boolean valid = validate(vo);
		if (valid) {
			System.out.println(vo);
			// 두 파라미터를 콘솔에 출력한 이후 하단의 메세지를 최종 응답으로 전송
			String message = "<html><body>정상 처리 되었음.</body></html>";
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().print(message);
		} else {
			// 둘 중의 하나라도 누락시, 400 에러 전송
			resp.sendError(400, "파라미터 누락");

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DummyData08VO vo = requestToCommandObject(req);
		boolean valid = validate(vo);
		if (valid) {
			System.out.println(vo);
			// 두 파라미터를 콘솔에 출력한 이후 하단의 메세지를 최종 응답으로 전송
			String message = "<html><body>정상 처리 되었음.</body></html>";
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().print(message);
		} else {
			// 둘 중의 하나라도 누락시, 400 에러 전송
			resp.sendError(400, "파라미터 누락");

		}
	}
	
	private DummyData08VO requestToCommandObjectFromJson(HttpServletRequest req) 
			throws IOException{
		BufferedReader reader = req.getReader();
		Gson gson = new Gson();
		return gson.fromJson(reader, DummyData08VO.class);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DummyData08VO vo = requestToCommandObjectFromJson(req);
		boolean valid = validate(vo);
		if (valid) {
			System.out.println(vo);
			// 두 파라미터를 콘솔에 출력한 이후 하단의 메세지를 최종 응답으로 전송
			String message = "<html><body>정상 처리 되었음.</body></html>";
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().print(message);
		} else {
			// 둘 중의 하나라도 누락시, 400 에러 전송
			resp.sendError(400, "파라미터 누락");

		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("삭제 완료");
//		accept 요청 헤더와 content-type 응답 헤더가 일치해야함.
		resp.setContentType("application/json");
		Map<String, Object> map = Map.of("success", true, "message", "삭제완료");
		String json = new Gson().toJson(map);
		resp.getWriter().print(json);
	}
}
















