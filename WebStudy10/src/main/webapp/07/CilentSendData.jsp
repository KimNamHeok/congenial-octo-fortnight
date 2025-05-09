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
<h4>클라이언트 전송데이터의 종류</h4>
<a href="?p1=v1&p2=한글">쿼리 스트링 전송</a>
<h1> p2 파라미터의 값 : <%=request.getParameter("p2") %></h1>
<form method="post" enctype="multipart/form-data">
	<input type="text" value="v1" name="p1">
	<input type="text" value="한글" name="p2">
	<input type="file" name="upload">
	<button type="submit">전송</button>
</form>
<pre>
	1. query string : request line의 URL을 통해 전송됨.
						(문자열만 전송, 길이 제한, 보안 취약성)
			ex) URL?queryString
					section&section&...
					name=value
					value : 한글을 비록한 특수문자가 url encoding 방식으로 인코딩이 된 상태로 전송.
					수신 측에서 디코딩을 해서 수신해야함.(encoding charset == decoding charset).
					문제는 decoding은 누가 하느냐!
					ex) tomcat인 경우, server.xml 변경
					한글이 깨지면 decoding하는 애 설정 변경!
	
	http(비보호 데이터 전송)
	https(secure channel 사용. 암호화된 데이터 전송 => body 영역에 있는 데이터)
	2. request body content 형태 : method=[post, put/patch], content-* 헤더 
<%-- 		<% 
 			request.setCharacterEncoding("UTF-8");
 			// request body의 디코딩 charset 결정
 			// body가 있을때만 동작함.
			// 데이터를 꺼내기 전에 사용해야함.
		%> --%>
		1) 일반적인 파라미터 형태 (application/x-www-form-urlencoded)
			form 태그 필요( method="post", enctype="application/x-www-form-urlencoded")
			parameter name : 입력태그(form-data)가 가진 name 속성으로 결정.
			String request.getParameter(name) // 하나의 이름으로 전달된 하나의 값을 꺼내는 것
			String[] request.getParameterValues(name) // 하나의 이름으로 전달된 여러개의 값을 꺼내는 것
			전달되는 파라미터의 이름을 모른다? => Enumeration request.getParameterNames()
			
			BeanUtils.populars(); => 한줄짜리로 바꿔주는 중요한 거!
			Map&lt;String,String[]&gt; request.getParameterMap() => Map으로 파라미터 꺼내기
		2) 멀티 파트 컨텐츠 형태	(multipart/form-data)
			form 태그 필요( method="post", enctype="multipart/form-data")
				ex) 파일 업로드(*)
				ex) 동시에 여러 형태의 데이터를 전송 해야 할 때
			part name : form-data 가 가진 name 속성으로 결정
			Part request.getPart(name)
			Collection&li;Part&gt; request.getParts()
		3) 요청 페이로드 형태 (application/json[xml]) : 비동기로만 전송
			InputStream request.getInputStream() => body를 그대로 읽어 들일수 있는 inputStream
		<script>
		// 1. request line
			let url = "${pageContext.request.contextPath}/08/sendJson";
			let method = "post";
		// 2. request header
		let headers = {
			"Accept" : "text/html",
			"Content-Type" : "application/json",
		}
		let obj = {prop : "value1", prop2 : 34};
		let jsonBody = JSON.stringify(obj);
			fetch(url,{
				method : method,
				headers : headers,
				body : jsonBody
			})
		</script>
</pre>
</body>
</html>