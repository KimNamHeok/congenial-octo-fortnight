package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.InternalResourceViewResolver;
import kr.or.ddit.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.util.ValidateUtils;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertController extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ProdVO prod = (ProdVO) session.getAttribute("prod");
		Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
		session.removeAttribute("prod");
		session.removeAttribute("errors");
		req.setAttribute("prod", prod);
		req.setAttribute("errors", errors);
		
		String lvn = "prod/prodForm";
		new ViewResolverComposite().resolveView(lvn, req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 여러개의 파라미터를 수신하고, Command object 로 만듦.
		ProdVO prod = new ProdVO();
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
//		2. Command object를 대상으로 검증.
		Map<String, String> errors = ValidateUtils.validate(prod, InsertGroup.class);
		String lvn;
		if(errors.isEmpty()) {
//		3. 검증 통과
//		3-1. 로직을 이용해 등록
			service.createProd(prod);
//		3-2. PRG  패턴을 위한 logical view name 결정
			lvn = "redirect:/prod/prodList.do";
		}else {
//		4. 검증 실패
//		4-1. 세션 스코프를 이용해서 command object, errors 공유(flash attribute)
			HttpSession session = req.getSession();
			session.setAttribute("prod", prod);
			session.setAttribute("errors", errors);
//		4-2. PRG 패턴을 위한 logical view name 결정
			lvn = "redirect:/prod/prodInsert.do";
		}
		
//		3-3. ViewResolver 이용
//		4-3. ViewResolver 이용
		new ViewResolverComposite().resolveView(lvn, req, resp);
	}
}

















