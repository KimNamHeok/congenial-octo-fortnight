<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>공통 레이아웃</h1>
<div id="part1">
	<%--
		request.getRequestDispatcher("/WEB-INF/views/fragments/b.jsp").include(request, response);
	--%>
	<%@ include file="/WEB-INF/views/fragments/b.jsp" %>
</div>
<div id="part2">
	<%--
		request.getRequestDispatcher("/WEB-INF/views/fragments/c.jsp").include(request, response);
	--%>
	<%@ include file="/WEB-INF/views/fragments/c.jsp" %>
</div>
</body>
</html>