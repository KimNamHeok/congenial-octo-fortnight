package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/factorial")
public class FactorialServletJSON extends HttpServlet {
	private long factorial(int op) {
		if(op<=0) throw new IllegalArgumentException("양의 정수만 처리함.");
		if(op==1) return 1;
		return op * factorial(op-1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. op 파라미터 확보
		String opParam = req.getParameter("op");
//		2. 파라미터 누락 여부 확인 : 누락됐다면, 400 상태코드 전송
		if(opParam==null||opParam.trim().isEmpty()) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
//		3. 문자열 파라미터를 숫자로 파싱 : 파싱이 안되는 파라미터라면? 400 전송
		try {
			int op = Integer.parseInt(opParam);
//		4. 연산 실행
			long result = factorial(op);
//		5. 연산결과 json형태로 만들어야 함 -> {"result":55} 형태의 json 생성
			Map<String, Long> resultMap = Map.of("result", result);
			String json = new Gson().toJson(resultMap);
//		6. MIME 결정 후 응답 기록.
			resp.setContentType("application/json;charset=UTF-8");
			try (PrintWriter out = resp.getWriter()) {
				out.print(json);
			}
		} catch (NumberFormatException e) {
			resp.sendError(400, e.getMessage());
		}
	}
}
