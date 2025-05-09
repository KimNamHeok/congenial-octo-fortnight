<%@ page import="java.time.LocalDateTime"%>
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
<h1>현재  서버의 시간 : <%=LocalDateTime.now() %></h1>
<%@ include file="/fragments/commonTitle.jsp" %>
<pre>
	: 서블릿의 하위 스펙으로 자바 기반의 스크립트 형태의 템플릿 엔진
	(서버사이드에서 템플릿과 데이터를 결합하는 SSR 방식을 사용함)
	HTML 형태의 동적 자원을 생성하기 위해 사용됨(Model2 구조에서 주로 활용됨)
	
	jsp소스 구성요소
	1. 정적 텍스트(template) : front-end
	2. 서버사이드에서 동작하는 스크립틀릿(scriptlet)
		1) scriptlet : <% // java 실행 코드 %>, 특정 메소드의 지역 코드화가 됨.
			<%
				String data = "DATA";
			%>
 		2) directive : <%--@ 지시자명 속성...  --%>, 실행에는 영향이 없고, 현재 페이지에 대한 환경설정에 사용되는 요소
 			- page (required) : 현재 페이지에서 생성되는 자원에 대한 MIME 설정(contentType).
 								현재 페이지내에서 lang 패키지 이외의 다른 패키지의 api 사용을 위한 설정(import).
 								현재 페이지내에서 세션의 지원 여부 설정(session)
 								현재 페이지내에서 응답 데이터 전송시 활용될 버퍼의 크기 설정(buffer=8kb)
 								버퍼 오버플로우를 발생시키지 않기 위한 autoFlush 설정(기본 true)
 									주의! flush 이후의 발생하는 예외에 대해서는 에러 메세지 처리가 불가능함.
 			- include (optional) : 페이지 모듈화를 구현할 때, include 지시자에 의해서 레이아웃과 프레이먼트
 			- taglib (optional) : 커스텀 태그 로딩에 사용(JSTL 에서 학습예정)
<!-- 			<접두어:태그명> -> 커스텀 태그(개발자가 새로 추가한 파일 -> jar파일로 존재 -> jar파일을 ?에 넣고 ?해줘야함) -->
< 		3) expression : <%--= 값이나 변수 혹은 출력할 expression --%> 
 		4) declaration : <%! // java 전역 코드 %>
 		<%!
 		void test(){}
 		%>
		5) comment : <%-- --%>
            <!-- HTML 주석 -->
            <script>
			// JS 주석
            </script>
            <style>
			/* CSS주석 */
            </style>
            <% // java 주석 %>
            <%-- jsp 주석 --%>
        6) EL(Expression Language) : ${attr_name }   
        7) JSTL(Java Standard Tag Library) : 커스텀 태그 라이브러리
</pre>
</body>
</html>