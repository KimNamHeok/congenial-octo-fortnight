package kr.or.ddit.servlet02;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 서블릿 (What / Why / How) ??
 *  웹을 통해 전송된 요청을 수신하고, 그에 맞는 처리를 진행하고, 그 결과로 동적 응답을 생성할 수 있는
 *  자바 객체의 명세(spec). 해당 명세를 구현하고 있는 클래스 HttpServlet.
 *  서블릿은 컨테이너에 종속형으로 동작하는 객체
 *  컨테이너란? 관리 대상이 되는 객체의 생명주기 관리자.
 *  
 *  1. 서블릿 개발 단계
 *  1) HttpServlet 구현 클래스 정의
 *  2) 필요한 callback 메소드 재정의.
 *  	생명주기 callback : 싱글턴 특성에 따라 생명주기 내에서 1번씩 실행.
 *  		init(생성 직후), 
 *  		destroy(소멸 직전)
 *  	요청 callback : 매 요청마다 반복적으로 실행.
 *  		service : 요청 발생시 컨테이너가 직접 실행.
 *  		do계열 : http method 에 따라 service 메소드 내에서 실행
 *  3) 서블릿 컨테이너에 서블릿 등록
 *  	servlet 2.x까지 : web.xml 에 등록
 *  	servlet 3.x부터 : @WebServlet 으로 등록
 *  4) 클라이언트가 사용할 URL 매핑.
 *  	servlet 2.x까지 : web.xml 에 매핑 설정
 *  	servlet 3.x부터 : @WebServlet 으로 매핑 설정
 *  5)	서버의 재구동
 *  
 *  2. 컨테이너의 특성
 *  1) 싱글턴 : 클래스의 인스턴스 하나만 생성하고 그걸 공유하는 전략.
 *  2) lazy-loading : 클래스의 인스턴스를 필요한 시점이 되기 전까지 생성을 지연하는 전략. (실제로 객체가 필요하기 전까지 생성을 뒤로 미뤄놓는)
 *  	반대 전략 : eager-loading : 즉시 생성
 *  3) CoC 전략.
 */
//@WebServlet // marker annotation
//@WebServlet("/desc")  // single value annotation (value 속성만 속성명 생략 가능)
//(CoC)Convention Over Configuration 패러다임에 따라 생략한 경우, 적용되는 속성값들이 있음.
//@WebServlet(name="Description", value="/desc", loadOnStartup = 1,) // multi value annotation (반드시 속성명 명시)
public class DescriptionServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("description servlet 초기화");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service 메소드 시작");
		super.service(req, resp);	// do 계열 메소드 실행
		System.out.println("service 메소드 종료");
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("description 서블릿 실행.");
	}
	
	@Override
	public void destroy() {
		System.out.println("description servlet 초기화");
	}
	
}
