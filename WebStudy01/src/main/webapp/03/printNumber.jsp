<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.odd{
		color: red;
	}
	.even{
		color: yellow;
	}
</style>
</head>
<body>

<!-- 1부터 10까지의 숫자를 li 태그로 완성 -->
<ul>
	<c:forEach var="num" begin="1" end="10" step="2">
	<c:choose>
		<c:when test="${num % 2 eq 1}">
			<c:set var="clz" value="odd"/>
		</c:when>
		<c:otherwise>
			<c:set var="clz" value="even"/>
		</c:otherwise>
	</c:choose>
			<li class="${clz }">${num }</li>
	</c:forEach>
</ul>

</body>
</html>