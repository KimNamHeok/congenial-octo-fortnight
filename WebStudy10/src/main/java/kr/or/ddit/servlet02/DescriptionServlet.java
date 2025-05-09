package kr.or.ddit.servlet02;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 서블릿(What /Why /How) ??
 * 웹을 통해 전송된 요청을 수신하고, 요청에 맞는 처리를 진행하고, 예쁘게 담아서 서비스(동적인응답서비스)
 * 그결과로 동적 응답을 생성할 수 있는 자바 객체의 명세(spec ==> 추상적으로 말함.) ==> 이게 최종 필수요소
 * 우리는 그걸 구현하고 있는 클래스를 말함. HttpServlet이라고 함.
 * 
 * 서블릿하나를 만들고 운영하려면 WAS가 필요함
 * 서블릿은 컨테이너에 종속형으로 동작하는 객체.
 * 컨테이너란? 관리 대상이 되는 객체의 생명주기 관리자
 * 
 * 1. 서블릿 개발단계
 * 	1) HttpServlet 구현 클래스 정의
 * 	2) 필요한 callback메소드 재정의 
 * // 특정 조건이 만족되었을때(특정이벤트 발생시) 시스템이 내부적으로 함수를 호출하는 것임.
 *  // 재정의할 메서드를 선택할 수 있어야함
 * 	
 * 		생명주기 callback : 싱글컨 특성에 따라 생명주기 내에서 1번씩 실행 
 * 			init() : 실행될때 
 * 			destory : 소멸직전
 *			
 * 		요청 callback : 매 요청마다 반복적으로 실행.
 * 			 service : 요청 발생시 컨테이너가 직접 실행. 뭐인지 모르나 요청이 오면 무조건한다 => service임.
 * 			 doXXXX	: http method에 따라 service메소드 내에서 실행
 * 	3) 서블릿 컨테이너에 서블릿 등록
 * 		servlet 2.x 까지 : web.xml에 등록 web.xml => 배포 서술자 => depolyment descriptor 명시하지 않더라도 사용하는 것처럼하는것임.
 * 		servlet 3.x 부터 : @WebServlet으로 등록
 * 	4) 클라이언트가 사용할 URL 매핑
 * 		servlet 2.x 까지 : web.xml에 매핑 설정
 * 		servlet 3.x 부터 : @WebServlet으로 매핑 설정 
 	5) 서버의 재구동
 	
 	컨테이너의 특성
 		1) 싱글턴 : 클래스의 인스턴스를 하나를 생성하고 그걸 공유하는 전략
 		2) lazy-loading : 클래스의 인스턴스를 필요한 시점이 되기 전까지 생성을 지연하는 전략
 			반대 전략 : eager-loading @WebServlet(name="Descrption", value="/desc", loadOnStartup = 1)
 			loadOnStartup = -1 default => lazy-loading
 			loadOnStartup = 1 eager-loading
 			// 반대로 할 수 있는 전략을 알려줌.
 		3) CoC 전략 : CoC(Convention Over Configuration) 패러다임에 따라 생략한 경우, 적용되는 속성 값들이 있음. 기존의 관행을 따라가는 패러다임을 CoC라고함.
 		CoC랑 Spring에 대해서 Gpt에 검색
 		
 	스프링이라는 컨테이너도 똑같은 특성을 가지고 있음. 또다른 컨테이너를 이해하는데 쉬워짐. 그래서 미리 말해주는것임.
 	서블릿에 대한 제어권은 나한테 없음. 제어권은 톰캣에게 있음 => 제어가 역전되었다. IOC역전
 	아무리 제어권이 역전되었더라도 할수있는건 최대한 제어권을 가져와야할거아냐
 	필요하다면 싱글턴, lazy-loading등 기본전략을 바꿔야함.
 	1번의 전략을 바꾸는 방법은 없음.
 	2번의 전략을 바꾸는 방법은 있음. 그러면 3번째 어노테이션 종류가 나오게됨.
 */
/* 어노테이션 종류 3가지 mark-annotation => 사용이 되고 있는 서블릿은 등록된 서블릿임. 등록여부 @WebServlet
//					singleValue-annotiation => 속성명 생략가능일때만 single @WebServlet("/desc") value에 대한 속성만 해당함. value속성만 속성명 생략가능
//					multiValue-annotation => 속성명 생략 불가일때엔 모두다 multi
// 보통 mark_annotation은 서버가 구동될때 딱한번 읽힘.
// 출력이 됬다는건 요청이 들어오기 전까지 객체를 만들어 내지않음
// 컨테이너의 특성 1. 싱글턴 : 클래스의 인스턴스를 하나를 생성하고 그걸 공유하는 전략
//			   2. lazy-loading : 클래스의 인스턴스를 필요한 시점이 되기 전까지 생성을 지연하는 전략
//
//@WebServlet("/desc")// value=""이건 숨길수 있음. //
//@WebServlet 	// 명시적으로 작성하지 않는 경우엔 관례적으로 따라간다는 의미임.
				// CoC(Convention Over Configuration) 패러다임에 따라 생략한 경우, 적용되는 속성 값들이 있음. 기존의 관행을 따라가는 패러다임을 CoC라고함.
				// 무조건 생략을 해야하는 것은 아니고 생략한 경우 적용되는 값이 있는 것임.
*/
//@WebServlet(name="Descrption", value="/desc", loadOnStartup = 1) 	
public class DescriptionServlet extends HttpServlet{
	@Override
		public void init(ServletConfig config) throws ServletException {
			System.out.println("description servlet 초기화");
			// 객체는 하나만 생성되어서 싱글톤으로 객체를 소멸시키지 않고 사용하고 있음.
			// 몇번의 요청이 들어와도 객체가 사라지지않음.
		}
	// 웹이라는 공간에서는 service를 얘로 하니까 얘로 오버라이딩해야함.
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청이 들어와야지만 사용이 가능함.
		// 진짜 callback은 service => doget은 service메서드 안에서 실행되는 것임.
			System.out.println("service 메서드 시작");
			// 서비스의 역할을 확인하기 위해 super.~~를 주석처리
			// super.service(req, resp);
			// 원래 서비스 메서드 안에서는 doget을 호출해주는 역할을 했음.(doget이 호출안됨)
			super.service(req, resp); // do계열 메서드 호출			
			System.out.println("service 메서드 종료");
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("descrition 서블릿 실행.");
		// 냅두면 405에러남 super.doGet(req, resp);
		// 이게 대표적 요청 콜백.
		// 요청 콜백의 가장 큰 특징 : req가 들어온다.
		
	}
	
	@Override
	public void destroy() {
		// 일반적으로 destory는 오버라이딩대상이 아님.
		System.out.println("description servlet 소멸");
	}
}
