<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.odd{
		color : red;
	}
	.even{
		color : green;
	}
</style>
</head>
<body>
<!-- 1부터 10까지의 숫자를 li태그로 완성 EL과 JSTL을 가지고 만들기 -->
<ul>
	<c:forEach var="i" begin="1" end="10">
		<c:choose>
			<c:when test="${i % 2==1}">
				<li class="odd">${i}</li>
			</c:when>
			
		</c:choose>
	</c:forEach>
</ul>
<hr>
	<c:forEach var="i" begin="1" end="10">
		<c:choose>
			<c:when test="${i % 2==1}">
			<!-- ${i % 2==1} == ${i % 2 eq 1}-->
				<li class="odd">${i}</li>
			</c:when>
			<c:otherwise>
				<li class="even">${i}</li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
<hr>
선생님 답
<hr>
	<c:forEach var="i" begin="1" end="10" step="2">
		<c:choose>
			<c:when test="${i % 2 eq 1}">
				<c:set var="clz" value="odd"/>
			</c:when>
			<c:otherwise>
				<c:set var="clz" value="even"/>
			</c:otherwise>
		</c:choose>
				<li class="${clz}">${i}</li>
	</c:forEach>
	
<hr>
	<c:forEach var="i" begin="1" end="10">
		<c:choose>
			<c:when test="${i % 2 eq 1}">
				<c:set var="clz" value="odd"/>
			</c:when>
			<c:otherwise>
				<c:set var="clz" value="even"/>
			</c:otherwise>
		</c:choose>
				<li class="${clz}">${i}</li>
	</c:forEach>
</body>
</html>