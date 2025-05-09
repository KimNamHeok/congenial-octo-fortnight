package kr.or.ddit.login;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet
public class test extends HttpServlet{

	private boolean authenticate(String username, String password) {		
//		인증여부 판단 기준 : 입력한 username 과 password가 동일하면 인증 성공
		return username.equals(password);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	1. 디코딩 설정
		req.setCharacterEncoding("UTF-8");
//	2. 파마리터 수신
		String username = req.getParameter("username");
		String password = req.getParameter("password");
//	3. 파라미터 검증
		boolean vaild = true;
		if(username==null || username.trim().isEmpty()) {
			vaild = false;
		}
		if(password==null || password.trim().isEmpty()) {
			vaild = false;
		}
		if(vaild) {
//	4. 검증 통과
//		1) 인증 여부 판단
			if(authenticate(username, password)) {
//		2) 인증 성공 : 웰컴 페이지로 이동
				
			}else {
//		3) 인증 실패 : 로그인 페이지 이동
				
			}
			
		}
//	5. 검증 실패 : 로그인 페이지 이동
		
	}
}