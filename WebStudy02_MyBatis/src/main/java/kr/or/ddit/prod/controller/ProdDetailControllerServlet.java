package kr.or.ddit.prod.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.ViewResolverComposite;
import kr.or.ddit.exception.ResponseStatusException;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodDetail.do")
public class ProdDetailControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		try {
			ProdVO prod = service.readProd(prodId)
								.orElseThrow(()->
									new ResponseStatusException(400, "그런 상품 없음"));
			req.setAttribute("prod", prod);
			String lvn = "prod/prodDetail";
			new ViewResolverComposite().resolveView(lvn, req, resp);
		}catch (ResponseStatusException e) {
			resp.sendError(e.getStatus());
		}
	}
}










