<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Redirect 사용 케이스</h4>
	<pre>
		1. 자원의 위치 재지정(대부분 서버에 의해 자체적으로 처리됨.)
		2. PRG 패턴(개발자의 영역)
			Post-Redirect-Get 패턴
			=> Post 요청으로 서버에 새로운 자원이 만들어지면,
				서버에서 자원이 갱신되고,(Redirect 이동구조로 location 헤더를 결정해주고)
				갱신된 자원에 대한 새로운 요청(GET)이 발생하도록 유도하는 방식
			주의 사항 : 역등성에서 문제가 생길때만 redirect해도 method 변경되지 않음
						=> post => get put => put
						
			웹에서 인증 시스템을 표현할때도 많이 활용되는 패턴.
	</pre>
	<form method="post" action="${pageContext.request.contextPath }/mbti/create" en>
		<input type="text" name="mbtiType"/>
		<input type="text" name="mbtiDesc"/>
		<button type="submit">전송</button>
	</form>
</body>
</html>