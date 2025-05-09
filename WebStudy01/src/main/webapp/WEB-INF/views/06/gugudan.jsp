<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		border-collapse: collapse;
	}
	th,td{
		border: 1px solid black;
	}
</style>
</head>
<body>
<form method="post">
	<select name="minDan">
		<c:forEach begin="2" end="9" var="danValue">
		<option value="${danValue }">${danValue }단</option>
		</c:forEach>
	</select>
	<select name="maxDan">
		<c:forEach begin="2" end="9" var="danValue">
		<option value="${danValue }">${danValue }단</option>
		</c:forEach>
	</select>
	<button type="submit">전송</button>
</form>

<c:if test="${not empty minDan and not empty maxDan}">
	<!-- 2단부터 9단까지의 구구단 출력. -->
	<!-- 승수 : 9까지 증가. 출력 형식 : 2 * 3 = 6 -->
	<table>
	<c:forEach begin="${minDan }" end="${maxDan }" var="dan">
		<tr>
		<c:forEach begin="1" end="9" var="mul">
			<td>${dan } * ${mul } = ${dan*mul } </td>
		</c:forEach>
		</tr>
	</c:forEach>
	</table>
	
	<script>
		document.querySelector('[name="minDan"]').value = `${minDan }`;
		document.querySelector('[name="maxDan"]').value = `${maxDan }`;
	</script>
	<c:remove var="minDan" scope="session"/>
	<c:remove var="maxDan" scope="session"/>
</c:if>
</body>
</html>
