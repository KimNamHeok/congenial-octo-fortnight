package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.ViewResolverComposite;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.vo.BuyerVO;

@WebServlet({"/buyer/buyerList.do", "/ajax/buyer"})
public class BuyerListControllerServlet extends HttpServlet{
	private BuyerService service = new BuyerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BuyerVO> buyerList = service.readBuyerList();
		req.setAttribute("buyerList", buyerList);
		String lvn = "buyer/buyerList";
		new ViewResolverComposite().resolveView(lvn, req, resp);
	}
}











