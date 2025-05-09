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
<h4> Http Method</h4>
<form id="target-form" action="${pageContext.request.contextPath }/08/dataProcess">
	<input type="text" name="p1" value="VALUE1">
	<input type="text" name="p2" value="한글값2">
	<button type="submit">전송</button>
	<button data-method="get" type="button">GET 요청</button>
	<button data-method="post" type="button">POST 요청</button>
	<button data-method="put" type="button">PUT 요청</button>
	<button data-method="delete" type="button">DELETE 요청</button>
</form>
<pre>
GET(read), POST(insert), PUT(update), DELETE(delete)
ex) 회원 관리, RESTful URI 형태 REST의 의미 : 설계자가 가지고 있는 의도를 그대로 따라가는것
	조회 : /member/memberList.do , /member/memberView[Detail].do
			/member GET(전체 조회)
			/member/a001 GET(a001 조회)
	등록 : /member/memberInsert.do
			/member POST
	수정 : /member/memberUpdate.do
			/member PUT (전체수정)
			/member/a001 PUT(a001 수정)
			
	탈퇴 : /member/memberDelete.do
			/member DELETE(전체 삭제)
			/member/a001 (a001삭제)
</pre>
<c:url value="/resources/js/methodAndPayload.js" var="fullPath"></c:url>
<script src="${fullPath }"></script>
</body>
</html>