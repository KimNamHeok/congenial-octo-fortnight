package kr.or.ddit.login;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * 해당 객체가 보유한 저장소를 가지고 있는 객체들.. 저장소(scope)들은 보유 객체와 생명주기가 동일함.
 * 
 * request (HttpServletRequest) : 요청을 전송한 클라이언트와 해당 요청에 대한 모든 정보를 가진 객체(http request package)
 * 	 --> request scope
 * response (HttpServletResponse) : 서버가 클라이언트에게 전송하는 응답에 대한 모든 정보를 가진 객체로 request 와 1:1 구조를 가짐.(http response package)
 *   --> request scope (Map) : name/value 로 구성된 attribute 의 저장소
 * application (ServletContext) : 서블릿 컨테이너와 하나의 컨텍스트에 대한 모든 정보를 가진 객체로 동일 컨텍스트 내에 싱글턴으로 운영됨.
 * 	 --> application scope (Map) : name/value 로 구성된 attribute 의 저장소
 * 
 * ** 인증 시스템에서는 한 클라이언트가 독점할 수 있는 저장소가 필요함.
 * session(HttpSession) : 어플리케이션 사용 시작부터 종료까지의 한세션의 정보를 가진 객체
 * --> session scope
 * 
 * setAttribute(String name, Object value)
 * Object getAttribute(String name) --> EL 을 사용하는 경우가 일반적, ${attributeName}
 * removeAttribute(name) : 생존범위가 명확하지 않은 scope 에 있는 데이터에 대해 사용함.
 * 
 */

@WebServlet("/login/loginCheck")
public class LoginCheckServlet extends HttpServlet{
	private AuthenticateService service = new AuthenticateServiceImpl();
	/**
	 * SRP(단일 책임의 원칙)
	 * O
	 * L
	 * I
	 * D
	 * 
	 * 로그인 처리 과정
	 * 1. username/password 입력 - loginForm.jsp
	 * 2. 데이터베이스로부터 username 에 해당하는 회원 정보 조회 - 신원 확인 -> dao
	 * 3. 저장된 비밀번호와 입력 받은 비밀번호 비교 - 본인 여부 확인 (여기서 사용하는게 크리덴셜) -> service(비지니스 로직)
	 * 4. 상황에 따른 흐름 제어 구조 필요 -> controller
	 * @param username
	 * @param password
	 * @return
	 */
//	private boolean authenticate(String username, String password) {		
////		인증여부 판단 기준 : 입력한 username 과 password가 동일하면 인증 성공
//		return username.equals(password);
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//		1. 디코딩 설정
		req.setCharacterEncoding("UTF-8");
//		2. 파마리터 수신
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		String dest = null;
		String message = null;
//		3. 파라미터 검증
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
//		5. 검증 실패 : 로그인 페이지 이동
			dest = "/login/loginForm.jsp";
			message = "아이디나 비밀번호 누락";
		}else {
//		4. 검증 통과
			MemberVO inputData = new MemberVO();
			inputData.setMemId(username);
			inputData.setMemPassword(password);
//			1) 인증 여부 판단
			if(service.authenticate(inputData)) {
//			2) 인증 성공 : 웰컴 페이지로 이동
				//1. Principal 구현 객체 생성
				//2. rquest.getUserPrincipal 에서 반환될 수 있도록 세팅.
//				--> Web Filter 의 활용
				session.setAttribute("authUser", inputData);
				dest = "/";
			}else {
//			3) 인증 실패 : 로그인 페이지 이동
				dest = "/login/loginForm.jsp";
				message = "아이디와 비밀번호가 서로 다른 경우";
			} // if(authenticate(username, password)) end
		} // 검증 if end
		if(StringUtils.isNotBlank(message)) {
			session.setAttribute("message", message);
		}
		
		String location = req.getContextPath() + dest;
		resp.sendRedirect(location);
		
	}
}		
		
		
		
		
/*
내가 만든
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if(username==null || username.trim().isEmpty()) {
			
			String location = req.getContextPath() + "/login/loginForm.jsp";
		}
		if(password==null || password.trim().isEmpty()) {
			
			String location = req.getContextPath() + "/login/loginForm.jsp";
		}
*/		

