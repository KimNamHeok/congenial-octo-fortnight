package kr.or.ddit.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * 해당 객체가 보유한 저장소를 가지고 있는 객체들..저장소(scope)들은 보유 객체와 생명주기가 동일함.
 * response는 request와 생명주기가 같기 때문에 저장소를 가질 필요가 없음!
 * 
 * request (HttpServletRequest) : 요청을 전송한 클라이언트와 해당 요청에 대한 모든 정보를 가진 객체(http request package)
 * response (HttpServletResponse) : 서버가 클라이언트에게 전송하는 응답에 대한 모든 정보를 가진 객체로 request와 1:1 구조를 가짐.(http response package)
 * --> request scope(Map) : name/value 로 구성된 attribute의 저장소
 * application (ServletContext) : 서블릿 컨테이너와 하나의 컨텍스트에 대한 모든 정보를 가진 객체로 동일 컨텍스트 내에 "싱글턴"으로 운영됨.
 * --> application scope(Map) : name/value 로 구성된 attribute의 저장소
 * --> 자동으로 사라지지않기 때문에 지워야하는 경우가 생김
 * 
 * ** 인증 시스템에서는 한 클라이언트가 독점할 수 있는 저장소가 필요함.
 * session (HttpSession) : 어플리케이션 사용 시작 붙터 종료 까지의 한 세션의 정보를 가진 객체
 * --> session scope
 * // 모든 사용자가 접근 해야할 데이터가 저장될! 서버가 생성될때 삭제!..
 * 
 * 모든 Scope 는 Map으로 저장됨!(name/value 로 구성된 attribute의 저장소)
 * setAttribute(String name, Object value)
 * Object value = getAttribute(String name) --> EL을 사용하는 경우가 일반적,${attributeName}
 * removeAttribute(name) : 생존 범위가 명확하지 않는 scope에 있는 데이터에 대해 사용함(session, application)
 * session에도 많이 저장을하면 서버가 힘들어짐! 세션을 가지고 인증시스템을 하면 서버에 굉장히 부하가 많이 걸림!
 * 실제로 네이버같은 시스템은 부하를 줄이려함 ==> 헤더기반의 인증
 * header기반의 인증과 session기반의 인증의 차이를 알아보자
 * 
 * 3개의 객체는 생명주기가 다름
 * request, response => 요청 생성시 생성, response가 전송시 삭제
 * application => 서버 실행시 생성, 서버가 종료시 삭제
 * 
 */
@WebServlet("/login/loginCheck")
public class LoginCheckServlet extends HttpServlet {

	private boolean authenticate(String username, String password) {
		// 인증여부 판단 기준: 입력한 username과 password가 동일하면 인증성공
		return username.equals(password);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//		1. 디코딩 설정
		req.setCharacterEncoding("UTF-8");

//		2. 파라미터 수신
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		String dest = null;
		String message = null;
//		3. 파라미터 검증
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
//			5. 검증 실패 : 로그인 페이지이동
			dest = "/login/loginForm.jsp";
			message = "아이디나 비밀번호 누락";
		} else {
//		4. 검증 통과
//			1) 인증 여부 판단
			if (authenticate(username, password)) {
//			2) 인증 성공 : 웰컴 페이지로 이동
				session.setAttribute("authUser", username);
				dest ="/";
			} else {
//			3) 인증 실패 : 로그인 페이지로 이동 
				dest = "/login/loginForm.jsp";
				message = "아이디나 비밀번호가 서로 다른 경우, 로그인 실패";
			} // 인증 if end
		} //검증 if end
		if(StringUtils.isNotBlank(message)) {
			session.setAttribute("message",message);
		}
		String location = req.getContextPath() + dest;
		resp.sendRedirect(location);
//		1. include => 사용할 필요 없음
//		2. forward => 
//		3. redirect => 
//		post 요청 : 역등성을 유지하기 위해서 이 요청을 남겨선 안됨
//		정상적인 요청이 아니기 때문에 남겨놓을 필요없음!
//		일반적인 인증시스템에서는 forward를 사용하지않음!
//		무조건 redirect로 이동하게 됨!
	} // post end
}
