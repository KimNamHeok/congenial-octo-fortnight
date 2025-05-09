<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSP(Java Server Page)</h1>
<h1>현재 서버의 시간 : <%=LocalDateTime.now() %></h1>
<%@ include file="/fragments/commonTitle.jsp" %> 
<!-- 페이지 모듈화 -->
	<pre>
	: 서블릿의 하위 스펙으로 자바 기방의 스크립트 형태의 템플릿 엔진
	(서버사이드에서 템플릿과 데이터를 결합하는 SSR방식을 사용함.)
	HTML 형태의 동적 자원을 생성하기 위해 사용됨.
	
	
	jsp 소스 구성요소
	1. 정적 텍스트(template)
	2. 서버 사이드에서 동작하는 스크립틀릿(scriptlet)
		1) scriptlet : <% // Java 실행 코드 %>, 특정 메소드의 지역 코드화가 됨.
						고스란히 메소드안에 들어가는 지역변수로 들어가기 때문에 
						저 코드 자체는 실행되지않음.
						JSP안에서 전역변수로 만들어야할 경우가 있음.
						그래서 필요한게  선언부라는 구조임
			<%
				String data = "DATA";
			// try안에 들어있는 블록변수임. 그리고 이변수는 메소드 안에 들어간 지역변수인것임.
			// void test(); 메소드안에 메소드 선언 못하니까 오류남!
			if(true){
				String blockVar;
				// blockVar가 가장 사용범위가 작음.
				//  블록변수 -> 지역변수 -> 전역변수(instance  : 동일한 변수, 클래스변수: )
				// 클래스 안보이고 메소드 안보이잖아 근데 자바는 객체언어잖아 우리눈엔 클래스가 안보이지만 진짜 소스가 아님.
				// 어딘가에는 클래스가 존재하고 메소드가 존재해야한다.
				
			}
			%>
			
			한번 방출된 데이터는 회수가 불가능하다 buffer에서 flush된 데이터는 을루!
		2) derective <%--<%@ 지시자명 속성... %> --%>, 실행에는 영향이 없고, 현재 페이지에 대한 환경설정에 사용되는 요소
			- page : (required) == 필수지시자 => 현재 페이지에서 생성되는 자원에 대한 MIME 설정(contentType)  page contentType="text/html; charset=UTF-8" import=""
										   		컨텐츠가 동적으로 생성되야하기 때문에
										   => 현재 패키지 내에서 lang 패키지 이외의 다른 패키지의 api사용을 위한 설정(import)
										   => 현재 페이지 내에서 세션의 지원 여부 설정(session)
										   => 현재 페이지내에서 응답 데이터 전송시 활용될 버퍼의 크기 설정(buffer=8kb)
										    	버퍼 오버플로우를 발생시키지 않기 위한 autoFlush설정(true);
										    	주의! flush 이후의 발생하는 예외에 대해서는 에러 메시지 처리가 불가능함.
			- include : (optional) => 페이지 모듈화를 구현할 때 사용한다.
								   =>include 지시자에 의해서 레이아웃과 프레그먼트(모듈)가 결합됨.
			- taglib : (optional) => 커스텀 태그 로딩에 사용.(JSTL에서 학습 예쩡)
		3) expression : <%-- <%= 값이나 변수 혹은 출력할 expression %> --%>
		4) declaration(선언부): <%! // java 전역 코드 %>
		<%! 
			void test(){};
		%>	
		전역코드를 만들어 낸다.
		5) commment : <%-- --%> ,서버에서 주석처리 되기 때문에 응답데이터에서 보이지 않는다.
			<!-- HTML 주석 -->
			<script>
			// JS 주석
			</script>
			<style>
			/* CSS 주석 */
			</style>
			<% // Java 주석 %>
			<%-- jsp 주석 --%>
			
			주석하나를 사용하더라도 의미를 정확하게 봐가면서 쓰자 프론트엔드 주석은 쓰지말자
			응답데이터에 포함 되어서 나오기 때문에 네트워크에 부하가 발생함. 
			주석은 백엔드형태의 주석으로 사용해라!
 		6)  EL(Expression Language) :${attr_name} 로직 표현 불가능 expression을 대체 할 것임.
 			EL이 가지지 못한 반쪽짜리를 JSTL이 가지고 있음!
 		7) JSTL(Java Standard Tag Library) : 커스텀 태그 라이브러리
	</pre>
</body>
</html>