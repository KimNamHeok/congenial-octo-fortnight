package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/06/gugudan_me")
public class GugudanModel2Servlet2_me extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/06/gugudan.jsp").forward(req, resp);	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int minParam = Integer.parseInt(req.getParameter("minDan"));
		int maxParam = Integer.parseInt(req.getParameter("maxDan"));
		
		if(minParam > maxParam) {
			resp.sendError(400, "max값은 min값보다 작을 수 없습니다.");
			return;
		}
		
		List<Integer> reList = new ArrayList<>();
		for(int i=minParam; i<=maxParam; i++) {
			for(int j=1;j<=9;j++) {
				reList.add((i * j));
			}
		}
		Map<String, Object> result = Map.of("minResult",minParam,"maxResult",maxParam,"result",reList);
		
		HttpSession session = req.getSession();
		session.setAttribute("result", result);
		
		resp.sendRedirect(req.getContextPath() + "/06/gugudan");
		
	}
}
