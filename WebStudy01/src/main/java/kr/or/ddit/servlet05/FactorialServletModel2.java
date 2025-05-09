package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/05/model2/factorial.do")
public class FactorialServletModel2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/05_1/factorialView.jsp").forward(req, resp);
	}
	
	private long factorial(int op) {
		if(op<=0) throw new IllegalArgumentException("양의 정수만 처리함.");
		if(op==1) return 1;
		return op * factorial(op-1);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int op = Optional.ofNullable(req.getParameter("op"))
			 		.map(Integer::parseInt)
			 		.orElseThrow();
			long result = factorial(op);
			HttpSession session = req.getSession();
			session.setAttribute("result", result);
			session.setAttribute("op",op);
			resp.sendRedirect(req.getContextPath()+ "/05/model2/factorial.do");
		} catch (Exception e) {
			resp.sendError(400, e.getMessage());
		}
	}
}
