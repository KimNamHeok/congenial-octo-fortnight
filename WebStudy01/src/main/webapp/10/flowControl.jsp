<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>웹 어플리케이션에서 흐름 제어</h4>
<pre>
	흐름 제어 : 하나의 웹 객체에서 또 다른 웹 객체로 이동하는 방식.
	HTTP : Connect-less, State-less
		: 웹이라는 공간의 특성상 서버의 부하를 줄이기 위해,
		http는 기본적으로 연결을 지향하지 않는 특성을 갖는다.
		(클라이언트와 서버사이의 요청이 발생하기 전, 3way handshake 방식으로 수립된 connection 은
		하나의 요청에 대한 1:1 의 응답이 전송된 후 종료[close]됨)
		(연결이 종료된 후에는 서버사이드에서 해당 요청에 대한 모든 데이터가 사라짐.)
		c.f) connect-full : connection 의 생성과 종료를 의도적으로 표현할 수 있는 방식으로
							주로 데이터베이스에서 활용되는 연결 형태.
		
	request dispatche(요청 분기) : A 에서 B 로 이동할 때, 연결이 종료되지 않고,
								응답이 전송되지 않은 상태에서 이동하는 방식
								기존의 A 쪽으로 발생한 request 가 그대로 B로 전달되는 형태.
	<%
		String path = "/10/dest.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
// 		rd.forward(request, response);
		rd.include(request, response);
	%>							
	
		- forward : 요청에 대한 최종 처리를 B 에게 위임하는 방식(서버사이드 위임.) - Model2 구조에 활용.
		- include : 하나의 응답 페이지를 여러개의 view layer 를 이용해서 구성하는 페이지 뮤듈화에 활용. 
				ex) B에 C, D를 함께 구성, B에서 응답을 보내지만 책임은 다 같이
	redirect location(위치 재지정) : A 방향으로 요청이 발생하고,
								A 에서 body 가 없는 응답이 전송됨(line-3xx, header-Location : B).
								Locaton 헤더에 설정된 B 쪽으로 새로운 요청을 전송하고,
								B 에서 최종 (body 가 포함된)응답(200)이 전송됨.
		<%--
			String location = request.getContextPath() + "/10/dest.jsp";
			response.sendRedirect(location);
		--%>
		
</pre>
</body>
</html>