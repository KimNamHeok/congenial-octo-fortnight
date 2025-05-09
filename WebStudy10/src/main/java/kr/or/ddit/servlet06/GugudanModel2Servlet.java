package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/06/gugudan")
public class GugudanModel2Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/06/gugudan.jsp").forward(req, resp);	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 확보
		try {
			// 문자열을 숫자로 파싱
			int minDan = Optional.ofNullable(req.getParameter("minDan"))
								.filter(s-> !s.isEmpty())
								.map(Integer::parseInt)
								.filter(n-> n>=2&&n<=9)
								.orElseThrow();
			int maxDan = Optional.ofNullable(req.getParameter("maxDan"))
					.filter(s-> !s.isEmpty())
					.map(Integer::parseInt)
					.filter(n-> n>=2&&n<=9)
					.orElseThrow();
			// view로 이동전 값 저장
			HttpSession session = req.getSession();
			session.setAttribute("minDan", minDan);
			session.setAttribute("maxDan", maxDan);
			// PostRedirectGet 패턴 (PRG패턴)
			// 역변성을 유지하기 위해서
			// view로 데이터 전달
			resp.sendRedirect(req.getContextPath() + "/06/gugudan");
		} catch (Exception e) {
			// 파라미터 누락은 400으로
			// 숫자가 아닌 값 400으로
			resp.sendError(400, e.getMessage());
		}
	}
}
