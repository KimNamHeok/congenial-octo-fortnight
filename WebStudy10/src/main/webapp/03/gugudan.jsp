<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		border-collapse: collapse;
	}
	th,td{
		border : 1px solid black;
	}
</style>
</head>
<body>
<!-- 2단부터 9단까지 구구단 출력 -->
<!-- 승수 : 9까지 증가 출력형식  : 2 * 3 = 6 -->
</body>
<table>
<c:forEach var="i" begin="2" end="9">
	<tr>
	<c:forEach var="j" begin="1" end="9">
		<td>${i}* ${j} = ${i * j}</td>
	</c:forEach>
	</tr>

</c:forEach>
</table>
<!-- <hr> -->
<%-- <c:forEach var="i" begin="2" end="9"> --%>
<%-- 	<c:forEach var="j" begin="1" end="9"> --%>
<%-- 		${i} * ${j} = ${i * j} <br> --%>
<%-- 	</c:forEach> --%>
<%-- 	<c:if test="${ i != 9}"> --%>
<!-- 		<hr> -->
<%-- 	</c:if> --%>
<%-- </c:forEach> --%>
</html>