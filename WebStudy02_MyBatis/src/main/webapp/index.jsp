<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>웰컴 페이지</h1>
<h1>로그인되어있는 사용자 : ${pageContext.request.userPrincipal.realUser.memName }</h1>
<pre>
	차후에 web filter 구조를 통해 Principal 구현체로 인증된 사용자를 표현할 예정.
</pre>
<c:set value="${pageContext.request.userPrincipal }" var="principal"></c:set>
<c:if test="${not empty principal }">
		${principal.realUser.memName }(${principal.realUser.memMail })(${principal.realUser.memBir })님 <a href="<c:url value='/login/logout'/>" >로그아웃</a>
</c:if>
<c:if test="${empty authUser }">
	<a href="<c:url value="/login/loginForm.jsp" />">로그인하러가기</a>
	
</c:if>
</body>
</html>