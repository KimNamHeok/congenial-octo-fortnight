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
<c:if test="${not empty message}">
	<script>
		alert("${message}");
	</script>
	<c:remove var="message" scope="session" />
<%-- 	<%session.removeAttribute("message"); %> --%>
</c:if>


<form method="post" action="${pageContext.request.contextPath }/login/loginCheck">
	<input type="text" name="username" placeholder="사용자의 식별자"/>
	<input type="password" name="password" placeholder="본인임을 증명할 credential"/>
	<button type="submit">로그인</button>
</form>
</body>
</html>