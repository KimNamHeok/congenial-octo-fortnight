package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/factorial")
public class FactorialServletJSON extends HttpServlet {
//	1. 의사코드 먼저 만들기 내가 먼저해야하는거 한글로 쓰기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. op파라미터 확보
		String opStr = req.getParameter("op");
		// 2. 파라미터 누락 여부 확인
		if(opStr==null||opStr.trim().isEmpty()) {
			resp.sendError(400, "필수파라미터 누락");
			return;
		}		
		try {
			// 3. 문자열 파라미처를 숫자로 파싱
			int op = Integer.parseInt(opStr);
			// 4. 연산실행
			long result = factorial(op);
			// 5. {"result" : 55} 형태의 json 생성
			Map<String, Long> resultMap = Map.of("result", result);	
			String json = new Gson().toJson(resultMap);
			// 6. MIME 결정후 응답 기록
			resp.setContentType("application/json;charset=UTF-8");
			
			try (PrintWriter out = resp.getWriter()) {
				out.print(json);
			}
		} catch (NumberFormatException e) {
			resp.sendError(400, e.getMessage());
			return;
		}
		

	}
	private long factorial(int op) {
		if(op<=0) throw new Error("팩토리얼의 값은 음수가 될수 없습니다.");
		if(op==1)return 1;
		return op * factorial(op -1);
	}
}
