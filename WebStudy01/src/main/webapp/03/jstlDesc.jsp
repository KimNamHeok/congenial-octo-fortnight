<%@page import="java.util.List"%>
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
<h4>JSTL (Java Standard Tag Library)</h4>
<pre>
	: 동적 페이지 구성에 사용할 수 있는 커스텀 태그의 집합.
	커스텀 태그 : &lt;접두어:태그명 속성...&gt;
	
	1. 태그 라이브러리 의존성 추가, jstl-api, jstl
	2. 태그 라이브러리 로딩 : taglib 지시자(prefix 결정) 사용
	3. &lt;접두어:태그명 속성...&gt; 형태로 사용.
		자주 활용되는 코어(c) 태그
		1) 속성 제어 : set, remove
		<c:set scope="session" var="attr" value="VALUE" />
		${attr }
<%-- 		<c:remove var="attr" scope="session"/> --%>
		--> ${attr }
		2) 조건문 : if, choose~when~otherwise
		<c:if test="${empty attr }">
			attr 속성이 없음.
		</c:if>
		<c:if test="${not empty attr }">
			attr 속성이 있음.
		</c:if>
		<c:choose>
			<c:when test="${empty attr }">
				attr 속성이 없음.
			</c:when>
			<c:otherwise>
				attr 속성이 있음.
			</c:otherwise>
		</c:choose>
		3) 반복문 : foreach, fortokens
		
		
			for (선언절;종료조건식;증감절)
			for (선언절 : 반복집합)
		<c:set value="<%=List.of(10,20,30) %>" var="listAttr" />
		
		<c:forEach var="i" begin="1" end="10" step="1">
			${i }
		</c:forEach>
<%-- 		, ${vs.frist }, ${vs.last }, ${vs.index }, ${vs.count } --%>
		<c:forEach items="${listAttr }" var="num" varStatus="vs">
			${num * 100 }
			<c:if test="${not vs.last }">
			,
			</c:if>
		</c:forEach>
		<c:forTokens items="아버지 가방에 들어가신다" delims=" " var="token">
			${token }
		</c:forTokens>
		4) url 재처리 : url, param
		<c:url value="/05/model2/factorial.do" var="facURL"/>
		<a href='${facURL }'>팩토리얼(Model2)</a>
		<c:url value="/01/factorial.jsp" var="facURL2">
			<c:param name="op" value="10" />
			<c:param name="n1" value="p1" />
			<c:param name="n2" value="p2" />
		</c:url>
		<a href='${facURL2 }'>팩토리얼(Model1)</a>
</pre>
</body>
</html>


























