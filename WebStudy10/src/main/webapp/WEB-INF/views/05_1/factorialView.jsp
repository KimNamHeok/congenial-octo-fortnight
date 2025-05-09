<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">
<%-- 
	브라우저는 불완전한 경로는 location에서 꺼내오기 때문에 location에 있는 href를 사용
	아무것도 안쓰면 현재 브라우저 주소를 사용한 것임!(상대주소를 사용)
	개발환경을 기준으로 하면 안됨! 클라이언트는 이런 주소체계를 모르니까 노출된 url주소가 들어가게 되는것임!
 	form에는 반드시 action이 있어야함. 없어도 강제로 부여됨!
 --%>
 
<%-- 	
<input type="number" name="op" min="1" max="10" required>=<%=session.getAttribute("result") %> 
EL은 null이 화이트스페이스로 나오고 표현식은 null 그대로 나옴. 결과값은 똑같음. 
둘의 차이는 null의 표현방식임!
EL은 session의 키값만 넣어주면 value가 나오게됨.
--%>
	<input type="number" name="op" min="1" max="10" value="${op}" required>=${result},<%=session.getAttribute("result") %>
	<%
		session.removeAttribute("op");
		session.removeAttribute("result");
	%>
	<button type="submit">전송</button>
</form>

<%-- 절대 경로 : <img src="<%=request.getContextPath()%>/resources/images/cat1.jpg"><br>
상대 경로 : <img src="../../resources/images/cat1.jpg"> --%>
<%-- 
	개발환경과 실행환경은 다르기 때문에! 상대경로 쓸때 잘 써야한다 현재 경로를 개발환경에서 하면 안된다!
 	상대경로는 기준점을 잡는게 중요함!
 --%>
 <%-- Model2 구조에서는 Scope필요 --%>
</body>
</html>