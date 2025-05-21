<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/bootstrap-5.3.6-dist/css/bootstrap.min.css" />
</head>
<body>
<h4>상품 목록 조회</h4>
<main class="container">

<table class="table">
	<thead>
		<tr>
			<th>상품명</th>
			<th>상품분류</th>
			<th>제조사</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>요약정보</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty prodList }">
				<c:forEach items="${prodList }" var="prod">
					<c:url value="/prod/prodDetail.do" var="detailURL">
						<c:param name="what" value="${prod.prodId }"/>
					</c:url>
					<tr>
						<td>
						<a href="${detailURL}">${prod.prodName }</a>
						</td>
						<td>${prod.lprodGu }</td>
						<td>${prod.buyerId }</td>
						<td>${prod.prodCost }</td>
						<td>${prod.prodPrice }</td>
						<td>${prod.prodOutline }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">
						아직 상품 없음.
					</td>
				</tr>
			</c:otherwise>
		</c:choose>	
	</tbody>
</table>
</main>
<script src="<c:url value='/resources/bootstrap-5.3.6-dist/js/bootstrap.bundle.min.js'/>"></script>
</body>
</html>