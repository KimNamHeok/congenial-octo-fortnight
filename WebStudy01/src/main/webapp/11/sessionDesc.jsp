<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Http Session</h4>
<h1>session id : ${pageContext.session.id }</h1>
<pre>
	세션 : 사용자의 한 세션을 의미하는 중의적인 표현.
		session
			통로(Database 쪽. Connect-Full) : 클라이언트와 서버사이의 유일한 connection
			시간(Web 쪽. Connect-Less, *** <- 강조 표시) : 클라이언트와 서버가 대화를 유지할 수 있는 유효 시간의 범위.
									클라이언트가 서버사이드 어플리케이션을 사용하기 시작한 순간부터
									사용 종료 이벤트가 발생할 때까지의 기간.
			세션 생명주기 : HttpSession 객체로 관리됨.
				생성 : 최초의 요청(세션 트래킹 모드에 해당하는 ID가 없는 요청)에 의해 세션 생성
				     --> 클라이언트들의 세션을 구분하기 위한 식별자 부여(session id)
				     --> 응답이 전송될 때 해당 id를 포함시켜 전송함.
				     --> 클라이언트 사이드에 저장되어있다가 다음번 요청이 발생할 때 서버로 재전송
					유지(session tacking mode) : 클라이언트 측의 세션 아이디 저장방식 + 서버쪽으로 재전송 되는 방식
					    cookie(***) : 각 브라우저가 가진 쿠기 저장소를 통해 저장 하고,
					    		 request header 를 통해 Cookie 라는 헤더로 재전송.
					    url : 브라우저는 저장하지 않음. + request line을 통해 url의 일부분으로 재전송.
					    <%=session.getId() %>
					    1) F5 로 새로고침
					    2) <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">a 태그를 이용한 새로고침</a>
					    SSL : secure channel 을 통해 암호화된 id를 주고 받는 구조.
				소멸(세션의 종료 조건)
					트래킹 모드로 저장되어있던 id 제거.
					브라우저 완전 종료.
					--> 쓰레기 세션이 서버에 누적됨.
					session timeout(쓰레기 세션 제거의 조건) 이내에 새로운 요청이 발생하지 않을때.
					로그아웃(session.invalidate) : 가장 명시적인 종료 조건.
					<%-- session.invalidate(); --%>
			session 기본 객체를 통해 session 정보를 획득하고,
					해당 클라이언트에 소속된 데이터를 저장할 수 있는 session scope를 사용할 수 있음.
			생성 시점 : ${pageContext.session.creationTime }
			마지막 접속 시점 : ${pageContext.session.lastAccessedTime }
			timeout : ${pageContext.session.maxInactiveInterval }
			<% session.setMaxInactiveInterval(4*60); %>
			timeout : ${pageContext.session.maxInactiveInterval }
				 웹쪽에서 연결통로의 의미로 Session을 사용하면 새로고침할 때마다 로그인 풀림(연결통로 끊겼다 다시 연결되니)
</pre>
</body>
</html>