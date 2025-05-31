package kr.or.ddit.buyer.controller;

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
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.util.ValidateUtils;
import kr.or.ddit.vo.BuyerVO;

/**
 * 제조사(Buyer) 정보 수정 컨트롤러 서블릿
 * 요청 URL: /buyer/buyerUpdate.do (GET, POST)
 */
@WebServlet("/buyer/buyerUpdate.do")
public class BuyerUpdateControllerServlet extends HttpServlet {

    private BuyerService service = new BuyerServiceImpl();

    /**
     * GET 요청: 제조사 수정 폼 제공
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        BuyerVO buyer = (BuyerVO) session.getAttribute("buyer");
        session.removeAttribute("buyer");
        Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
        session.removeAttribute("errors");

        req.setAttribute("buyer", buyer);
        req.setAttribute("errors", errors);

        String buyerId = req.getParameter("what");

        if (StringUtils.isBlank(buyerId)) {
            resp.sendError(400, "필수 파라미터 누락");
            return;
        }

        if (buyer == null) {
            buyer = service.readBuyer(buyerId).get();
            req.setAttribute("buyer", buyer);
        }

        String lvn = "buyer/buyerEdit";
        new ViewResolverComposite().resolveView(lvn, req, resp);
    }

    /**
     * POST 요청: 제조사 정보 수정 처리
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String what = req.getParameter("what");

        BuyerVO buyer = new BuyerVO();

        try {
            BeanUtils.populate(buyer, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ServletException(e);
        }

        Map<String, String> errors = ValidateUtils.validate(buyer, UpdateGroup.class);

        String lvn;
        if (errors.isEmpty()) {
            service.modifyBuyer(buyer);

            BuyerVO updatedBuyer = service.readBuyer(buyer.getBuyerId()).orElse(null);

            lvn = "redirect:/buyer/buyerDetail.do?what=" + buyer.getBuyerId();
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("buyer", buyer);
            session.setAttribute("errors", errors);

            lvn = "redirect:/buyer/buyerUpdate.do?what=" + what;
        }

        new ViewResolverComposite().resolveView(lvn, req, resp);
    }
}