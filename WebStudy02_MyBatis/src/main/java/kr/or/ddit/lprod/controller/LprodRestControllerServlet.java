package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.ViewResolverComposite;
import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;
import kr.or.ddit.vo.LprodVO;

@WebServlet("/ajax/lprod/*")
public class LprodRestControllerServlet extends HttpServlet {
	private LprodService service = new LprodServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<LprodVO> lprodList = service.readLprodList();
		req.setAttribute("lprodList", lprodList);
		String lvn = null;
		new ViewResolverComposite().resolveView(lvn, req, resp);
	}
}
