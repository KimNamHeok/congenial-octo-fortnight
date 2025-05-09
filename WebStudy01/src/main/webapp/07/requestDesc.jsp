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
<h4> HTTP request packaging : HttpServlertRequest </h4>
<pre>
	Request Line : 수신자(서버의 대상 자원에 대한 표현, URL)
				서버의 자원을 대상으로 한 액션의 표현(Request method) 적어도 7개 이상 존재
		<%
		
		%>
		<%=request.getRequestURI() %>, <%=request.getMethod() %>
		${pageContext.request.requestURI }, ${pageContext.request.method }
				Create(등록) : POST
						ex) form action 속성(url), method 속성 <- 이 경우가 아니면 대부분 Get발생
				Read(조회) : GET (default method)
						ex) a href 속성, img src 속성, script src 속성, video src 속성
				Update(수정) : PUT / PATCH (PUT은 전체 수정하고 덮어씌우기, PATCH는 일부수정)
				Delete(삭제) : DELETE
				Preflight 요청 : OPTION
				HEAD : 응답을  수신할 때, 응답의 BODY 가 없는 상태로 수신할 요청의 표현.
				TRACE : 서버를 대상으로 디버깅을 할 때 사용되는 메소드 (사용하는 서버 거의X, 관리자 시스템 내부에서만 사용)
				
				ex) /member/memberInsert.do, /member/memberUpdate.do, /member/memberDelete.do
					RESTful URI 구조 : URL을 하나로 고정하고 메소드를 통해 요청을 표현하는 방식
					/member (POST) : 등록		
					/member (PUT) : 수정
					/member (DELETE) : 삭제
					/member (Get) : 조회			
	Request Header : 요청에 대한 부가정보를 표현하는 메타데이터 영역 (name : value)
		<%=request.getHeader("Accept") %>
		${pageContext.request.getHeader("Accept") }
		-->${header['accept'] }, ${header.accept }
		${header['accept-language'] }
		\${header.accept-language }
		* Accept : 응답에 대한 조건의 표현
			ex) Accept : application/json  (ajax - dataType : json)  응답데이터를 json형태로 줬으면 좋겠어
			ex) Accept : application/xml   (ajax - dataType : json)  응답데이터를 xml형태로 줬으면 좋겠어
			ex) Accept : application/html  (ajax - dataType : json)  응답데이터를 html형태로 줬으면 좋겠어
			ex) Accept : application/plain  (ajax - dataType : text)  응답데이터를 text형태로 줬으면 좋겠어
			ex) Accept : application/javascript  (ajax - dataType : script)  응답데이터를 script형태로 줬으면 좋겠어
		  Accept-Language : 언어와  국가에 표현(locale)
		  	ex) Accept-Languge : ko-KR (한글-한국)
		  	ex) Accept-Languge : en-US (영어-미국)
		  	ex) Accept-Languge : en-UK (영어-영국)
		  Accept-Encoding :응답의 압축 포맷 표현 (gzip)
		  * Content-* : 요쳥의 컨텐츠(content body)에 대한 표현
		  	  Content-Length : content body 의 길이
		  	  Content-Type : content body의 MIME
		  	  ex) Content-Type : application/json, 요청페이로드 전송형태, 비동기 요청에서 활용.  클라이언트가 서버에게 편지를 보내는데 json형태로 되어 있다 -> 서버에서 편지를 받으면 언마샬링 해야함
			  ex) Content-Type : application/x-www-form-urlencoded, 요청 파라미터 전송
			  ex) Content-Type : multipart/form-data, 멀티 파트 요청
		* User-Agent : 사용자의 OS, 랜더링 엔진, 브라우저 정보 표현.
	Request Body (Message Body, Contnet Body)
		* method="post", content-* 헤더가 존재하는 경우 형성됨.  <- Request Body 존재 조건
		1) 요청 파라미터
		2) 멀티 파트
		3) 요청 페이로드
</pre>
<form method="post" enctype="application/json">
	<input type="text" name="dummy1" />
	<input type="text" name="dummy2" />
	<button type="submit">전송</button>
</form>
<table>
	<thead>
		<tr>
			<th>헤더이름</th>
			<th>헤더값</th>
		</tr>
	</thead>
		  <c:forEach items="${header }" var="entry">
		  	  <tr>
		  	  	  <td>${entry.key }</td>
		  	  	  <td>${entry.value }</td>
		  	  </tr>
		  </c:forEach>
	<tbody>
	</tbody>
</table>
</body>
</html>