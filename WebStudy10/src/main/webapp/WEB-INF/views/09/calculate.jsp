<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
	<input type="number" name="left"/>
	<select name="operator">
		<c:forEach items="BiOperandOperatorList" ></c:forEach>
	</select>
	<input type="number" name="right"/>
	<span id=></span>
</form>
</body>
</html>