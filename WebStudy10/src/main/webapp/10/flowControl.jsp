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
	흐름제어 왜 필요함?
		=> 가장 많이 드는예 : 피자 하나 주문하는 상황(내가 주문할 곳을 선택할때 일일히 보는.. 페이지 단계별)
		=> 도미노피자 사이트에서 맘에드는 걸 주문하기 버튼을 눌러서 주문을함
		=> 끝도 없이 선택해야하는 상황이 생김.
		=> 현재 사용자가 로그인을 안함
		=> 주문하기 => 로그인
		=> 주문하기(로그인이 되어있는 경우) => 다음페이지
		==> 흐름의 제어를 함. 주문이 되어있다고 바로 주문이 되지않음
	즉 하나의 웹 객체에서 또 다른 웹객체로 이동하는 방식
	HTTP : Connect-less, State-less  
			: 웹이라는 공간의 특성상 서버의 부하를 줄이기 위해, 
			http는 기본적으로 연결을 지향하지 않는 특성을 갖는다.
			(클라이언트와 서버사이의 요청이 발생가히전, 3way handshake 방식으로 수립된 connection은 
			하나의 요청에 대한 1:1의 응답이 전송 된 후 종료[close]됨)
			(연결이 종료된 후에는 서버사이드에서 해당 요청에 대한 모든 데이터가 사라짐.)
			c.f) connect-full : connection의 생성과 종료를 의도적으로 표현할 수 있는 방식으로
								주로 데이터베이스에서 활용되는 연결 형태
								
			Connect-less : 요청과 응답이후에 연결이 끊기는 것
							*연결을 수립하는것도 연결을 끊는것도 내가 하지 않음
							의도를 표현하지 않더라도 연결을 수립하고 알아서 끊음!
			Connect-full : 요청과 응답이후에 연결이 안끊기는 것
							*MyBatis sqlSession
							=> Connection을 내가 직접 제어하는 구조
							
			Connect-less라는 구조때문에 파생되는 구조가 있음! => Stateless
			1. Connection수립 
			2. request보냄 
			3. 유지되고 있는 통로를 통해서 request 보냄.
			4. 연결 끊김
			State-less : 응답이 남아있지 않은 상태. web이가진 한계점  대화가 안됨!
						 온라인 서점에서 한권만 구매하지 않음!
						 --> request의 요청이 서로 관련이 없는? 그런상태
	request dispatche (요청분기) : A에서 B로 이동할때, 연결이 종료되지 않고,
								응답이 전송되지 않는 상태에서 이동하는 방식
								기존의 A 쪽으로 발생한 request 가 그대로 B로 전달되는 형태.
		1단계
		<%
			// ㅊ요청에 대한 처리를 위임했다해서 위임처리방식
			// 두개의 공통점 : 동일한 request를 가지고 있는다.
			String path="/10/dest.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
// 			rd.forward(request, response);
			rd.include(request, response);
			// 한 개의 페이지에서 두 개의 페이지를 만들어 내는것!
			
		%>
		- forward => request.response에 대한 책임을 확실하게 구분지어져있음
				=> 요청에 대한 최종 처리를 B에게 위임하는 방식(서버사이드 위임.)
				=> 클라이언트는 위임되는 구조를 전혀 모른다.
				=> Model2 구조에 활용
		- include => response에 대한 책임이 쪼개져있음. 
				=>  하나의 응답 페이지를 여러개의 view layer를 이용해서 구성하는 페이지 모듈화에 활용
	redirect location (위치 재지정) : A 방향으로 요청이 발생하고,
								A(서버사이드)에서 body 가 없는 응답이 전송됨(line : 3XX대 코드, header : Location -> B)
								응답은 여기에서 끊겨버림. Location 헤더에 설정된 B쪽으로 새로운 요청을 전송하고,
								B에서 최종(body가 포함된) 응답(200)이 전송됨.
<%--
 			String location = request.getContextPath() +  "/10/dest.jsp";
 			response.sendRedirect(location);
--%>
</pre>
</body>
</html>