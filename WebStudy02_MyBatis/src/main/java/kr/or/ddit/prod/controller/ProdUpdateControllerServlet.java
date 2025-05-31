package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.ViewResolverComposite;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.util.ValidateUtils;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodUpdate.do")
public class ProdUpdateControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	/**
	 * 수정 form 제공
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ProdVO prod = (ProdVO) session.getAttribute("prod");
		session.removeAttribute("prod"); // flash attribute 방식
		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
		session.removeAttribute("errors");
		
		req.setAttribute("buyer", prod);
		req.setAttribute("errors", errors);
		
		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		if(prod==null) {
			prod = service.readProd(prodId).get();
			req.setAttribute("prod", prod);
		}
		
		req.setAttribute("action", "update");
		
		String lvn = "prod/prodForm";
		new ViewResolverComposite().resolveView(lvn, req, resp);
	}
	
	/**
	 *
	 * form 으로 입력받은 데이터 처리
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String what = req.getParameter("what");
		ProdVO prod = new ProdVO();
//		buyer.setBuyerName(req.getParameter("buyerName"));
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}

		Map<String, String> errors = 
				ValidateUtils.validate(prod, UpdateGroup.class);
		String lvn;
		if(errors.isEmpty()) {
			service.modifyProd(prod);
			// PRG 패턴
			
			lvn = "redirect:/prod/prodDetail.do?what="+prod.getProdId();
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("prod", prod);
			session.setAttribute("errors", errors);
			lvn= "redirect:/prod/prodUpdate.do?what="+what;
		}
		new ViewResolverComposite().resolveView(lvn, req, resp);
	}
}
