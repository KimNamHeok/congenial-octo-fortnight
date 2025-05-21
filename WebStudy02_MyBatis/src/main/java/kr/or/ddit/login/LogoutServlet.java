package kr.or.ddit.login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login/logout")
public class LogoutServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 강제로 세션을 만료시키려면? 동시에 session scope내의 모든 속성 제거
		HttpSession session = req.getSession();
//		session.removeAttribute("authUser");
		session.invalidate();
		// 웰컴 페이지로 이동 (forward, redirect)
		String location = req.getContextPath() + "/";
		resp.sendRedirect(location);
	}
}
