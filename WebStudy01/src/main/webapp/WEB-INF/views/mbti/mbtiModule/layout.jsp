<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/mbti/mbtiModule/preScript.jsp" %>
</head>
<body>
<header>
<%@include file="/WEB-INF/views/mbti/mbtiModule/header.jsp" %>
</header>
<main class="container p-3 text-primary-emphasis bg-primary-subtle border border-primary-subtle rounded-3">
include 의 방식 2가지 - include시점과 include 대상에 따른 분류.
정적 include : 컴파일 이전에(실행 전에) 소스가 include 됨. (include 지시자)
동적 include : 실행 시점에 실행 결과 데이터가 include 됨. (request dispatch include 구조)
			커스텀 태그 사용 구조도 가능.
form UI : mbtiForm.jsp
mbti content : entj.html
<jsp:include page="${contentPage }" />
<%--
	String contentPage = (String) request.getAttribute("contentPage");
	request.getRequestDispatcher(contentPage).include(request, response);
--%>
</main>
<%@ include file="/WEB-INF/views/mbti/mbtiModule/postScript.jsp" %>
</body>
</html>