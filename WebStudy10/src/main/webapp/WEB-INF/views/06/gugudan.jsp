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

<form method="post">
 	<label>최소값</label><select name="minDan">
 		<c:forEach var="min" begin="2" end="9">
 			<option value="${min}">${min}</option>
 		</c:forEach>
 	</select>
 	<label>최대값</label><select name="maxDan">
 		<c:forEach var="max" begin="2" end="9">
 			<option value="${max}">${max}</option>
 		</c:forEach>
 	</select>
 	<button type="submit">전송</button>
 	
</form>
<table>

<c:if test="${not empty minDan and not empty maxDan}">
<c:forEach var="i" begin="${minDan}" end="${maxDan}">
	<tr>
	<c:forEach var="j" begin="1" end="9">
		<td>${i}* ${j} = ${i * j}</td>
	</c:forEach>
	</tr>

</c:forEach>
<script>
document.querySelector('[name="minDan"]').value = `${minDan}`;
document.querySelector('[name="maxDan"]').value = `${maxDan}`;
</script>
<c:remove var="minResult"/>
<c:remove var="maxResult"/>
</c:if>
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
</body>
</html>