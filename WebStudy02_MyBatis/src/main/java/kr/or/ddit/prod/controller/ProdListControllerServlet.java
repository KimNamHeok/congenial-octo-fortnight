package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;

/**
 * 1. 요청 수신
 * 2. 요청 검증
 * 3. 로직을 사용하고, 모델을 생성
 * 4. 모델을 뷰로 전달(scope 를 통해 공유함)
 *  - 모델의 사용 범위
 *  - 뷰로 이동하는 방식
 * 5. 뷰를 선택
 * 6. 뷰로 이동(forward, redirect)
 * 
 */

@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ProdVO> prodList = service.readProdList();
		
		req.setAttribute("prodList", prodList);
		
		String view = "/WEB-INF/view/prod/prodList.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
