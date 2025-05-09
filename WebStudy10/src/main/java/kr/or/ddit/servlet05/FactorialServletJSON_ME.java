package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/05/factorial_me")
public class FactorialServletJSON_ME extends HttpServlet {
//	1. 의사코드 먼저 만들기 내가 먼저해야하는거 한글로 쓰기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long result = 0;
		try {
			// 1. op파라미터 
			String opStr = req.getParameter("op");
			// 2. 파라미터 검증 통과 x => 400
		int op = Optional.ofNullable(opStr)
					.map(Integer::parseInt)
					.filter(n->n<=10 && n>=1)
					.orElseThrow();
		result = factorial(op);
		} catch (Exception e) {
			resp.sendError(400, e.getMessage());
		}
		
		JsonObject obj = new JsonObject();
		obj.addProperty("result", result);
		
		Gson gson = new Gson();
		String res = gson.toJson(obj);
		
		PrintWriter out = resp.getWriter();
		out.print(res);
		
	}
	private long factorial(int op) {
		if(op<=0) throw new Error("팩토리얼의 값은 음수가 될수 없습니다.");
		if(op==1)return 1;
		return op * factorial(op -1);
	}
}
