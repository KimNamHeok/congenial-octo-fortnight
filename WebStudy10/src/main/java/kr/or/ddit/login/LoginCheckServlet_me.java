package kr.or.ddit.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login/loginCheck_me")
public class LoginCheckServlet_me extends HttpServlet {
	
	private boolean authenticate(String username, String password) {
		// 인증여부 판단 기준: 입력한 username과 password가 동일하면 인증성공
		return username.equals(password);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 디코딩 설정
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// 파라미터 검사 
		boolean valid = true;
		String location = null;
		
		// 에러메시지 넣기
		Map<String, String> errors = new HashMap<>();
		if(username ==null || username.trim().isEmpty()) {
			location = req.getContextPath() + "/login/loginForm.jsp";
			errors.put("username","식별자가 입력되지 않음");
			valid = false;
		}
		if(password == null || password.trim().isEmpty()) {
			location = req.getContextPath() + "/login/loginForm.jsp";
			errors.put("credential","credential이 입력되지 않음");
			valid = false;
		}
		
		if(valid) {
		// 검증을 통과했을시 인증처리
			if(authenticate(username, password)) {
				// 모든 절차 성공시 메인페이지 역할을 할 welcome페이지로 보냄
				location = req.getContextPath() +"/index.jsp";
			}else {
				// 인증실패시 로그인 폼으로 보냄
				location = req.getContextPath() + "/login/loginForm.jsp";
				errors.put("false","인증 실패");
			}
			session.setAttribute("errors", errors.toString());
			resp.sendRedirect(location);
			
		}else {
			// 파라미터 누락시 누락 데이터를 다시 입력할수 있는 곳으로 보냄! 로그인 폼으로!
			session.setAttribute("errors", errors.toString());
			resp.sendRedirect(location);
		}
		// 인증하려면 인증과 관련데이터가 db에 있어야하는데
		// 없으니까 메서드 만들어줬음!
//		redirect하래..
	}
}
