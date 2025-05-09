<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${not empty message }">
	<script>alert("${message}");</script>
	<c:remove var="message" scope="session"/>
</c:if>
</head>
<body>
<%-- 
username(사람이름아님!) => 식별자(id) 프리벤션? => 나만알수있는정보 ( 비밀번호,핀번호,지문...)
2팩터인증? 2가지 프리벤션을 통해서 하는 것 
listener 는 서버가 시작하자마자 시작하는 곳
전체 공용..
cPath
--%>
<form method="post" action="${pageContext.request.contextPath }/login/loginCheck">
	<input type="text" name="username" placeholder="사용자의 식별자"/>
	<input type="text" name="password" placeholder="본임임을 증명할 credential"/>
	<button type="submit">로그인</button>
</form>


</body>
</html>