<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HTTP request packaging</title>
</head>
<body>
<h1>HTTP request packaging</h1>
<%
	
%>
	<pre>
		Request Line : 2가지 정보가 들어감. (Genenal에 포함)
					=> 수신자(서버의 대상 자원(resource)에 대한 표현, URL)
					=> 서버의 자원을 대상으로 한 액션의 표현(Request method)
					<%
					
					%>
					<%=request.getRequestURI() %>, <%=request.getMethod() %>
					${pageContext.request.requestURI}, ${pageContext.request.method} 
											=> EL안에서는 기본 객체를 사용할 수 없음.
											pageContext라는 것만 사용가능 
											pageContext안에는 모든 기본객체를 getter로 가져올 수 잇음
											EL안에서 기본객체가 필요하면 pageContext를 가져올수 있음.
											EL에서는 get메서드를 이런식(getRequestURI())으로 접근하지 않음.
											getter의 이름은 어떻게 결정이 되나? 
											javabin 규약? lombok	
											EL의 규칙성 > java bon의 규약을 역으로 적용시킴! get버리고 ()버리고 맨 앞 대문자-> 소문자		
											get메서드처럼 적을때 오류가 나는 것 처럼 보이지만 오류가 아님!
						C create(등록) : Post
									=> form action 속성(url), method 속성
						R read (조회) : GET(서버의 요청을 클라이언트가 가져가는것!) 대부분의 클라이언트의 요청을 get으로 처리
									=> default method
									=> a href 속성 imc src 속성, script src 속성, video src 속성
									
						U update(수정) : PUT / PATCH
						D delete(삭제) : DELETE
							action의 종류가 4가지 이상이 되어야함.
						Preflight 요청 : OPTION
						HEAD : 응답을 수신할 때, 응답의 BODY가 없는 상태로 수신할 요청의 표현
						TRACE : 서버를 대상으로 해서 디버깅할 때 사용되는 메소드 => 아주 제한적인 환경에서 관리자 ~~일때 사용ㅇ되는 메소ㅡㄷ
						PUT/PATCH 구분하는 방법 : 전체를 다?(PUT)  아님 일부분 하는가?(PATCH)
												
						ex) /member/memberInsert.do , /member/memberUpdate.do, /member/memberDelete.do
						/member (POST) : 등록
						/member (PUT) : 수정
						/member (DELETE) : 삭제
						/member (GET) : 조회
						=> 메소드에 따라서 달라짐 url은 하나로 고정되어있음
						update, delete 에는 동기 요청으로 하지 못함.
		
			특정 서버가 요청을 받을수 있는지 확인하기 위해서 Preflight 요청이 있음
			
		Request Header : 요청에 대한 부가 정보를 표현하는 메타데이터 영역 (name : value)
		<%= request.getHeader("Accept") %> => 대소문자 상관없음
		${pageContext.request.getHeader("Accept")}
			=> 이것은 매개변수가 있기 때문에 getter가 아님! 그러면 
		${header["Accept"]} => EL에서 header의 모든 정보를 가지고 있는 객체임! => 연관배열구조는 소문자로만!
		${header.accept }
		
		${header['Accept-Language']}
		\${header.Accept-Language}  ==> 하이푼이 아니고 -연산으로 처리됨!
			{}로 표현 되기 때문에 객체 혹은 map임 그러면 header는 map으로 된것임!
			* Accept : 응답에 대한 조건의 표현(Mime)
				ex) Accept : application/json=> 답장줄때 json내놔
							(ajax - dataType : json ==> 위의 것이 됨!)
				ex) Accept : application/xml=> 답장줄때 xml내놔
							(ajax - dataType : xml ==> 위의 것이 됨!)
				ex) Accept : text/html=> 답장줄때 html내놔
							(ajax - dataType : html ==> 위의 것이 됨!)
				ex) Accept : text/javascript=> 답장줄때 html내놔
							(ajax - dataType : script ==> 위의 것이 됨!)
				Accept-Language : 언어와 국가(Locale)에 표현
				 	ex) Accept-Languge : ko-KR(한글-한국)
				 	ex) Accept-Languge : en-US(영어-미국)
				 	ex) Accept-Languge : en-UK(영어-영국)
				Accept-Encoding : 응답의 압축 포맷 표현(gzip)
			* Content-* : 요청의 컨텐츠(content body)에 대한 표현(이게 있어야 body가 있음을 의미!)
				Content-Length : content body의 길이
				Content-Type : content body의 MIME
				 	ex) Content-Type : application/json, 요청 페이로드 전송 형태, 비동기 요청에서 활용
				 	ex) Content-Type : application/x-www-form-urlencoded, 요청 파라미터 전송 
				 	ex) Content-Type : multipart/form-data, 멀티파트 요청
			*User-Agent : 사용자의 OS, 랜더링 엔진, 브라우저 정보 표현
		Request Body (Message Body, Content Body) body역할 
			* method="post", content-* 헤더가 존재하는 경우 형성됨.
			1) 요청 파라미터
			2) 멀티 파트
			3) 요청 페이로드
			get방식 => Payload => queryString ~~==> body가 아님 편지봉투에다가 쓱싹한거
		    post 방식 => Payload => Form Data => 바디임!
		    
		편지지가 없으면 편지봉투에 끄적여서 보내기도 함! 그게 get방식 => 
		보내주고 있는 메세지는 어떤 메세지 형태인지 알아야함 : 맨아래 request-headers
			**accept 로 시작되는 것들이 있음. => 요청에대한 정보가 아닌 응답에 대한 조건을 표현하고 있는 것
									*/* 어떤 마임이 와도 상관없으니 응답만 줘 라고 마지막에 되어있음
			**content
			accept-Encoding => 압축 형태 gzip 응답데이터를 줄때 gzip형태로 주면 풀수 있어!
							응답을  결정해서 내보낼때 중요한 것이 됨!
			accept-Language => ko-KR
			**user-agent 사용자가 사용하고 있는 길이에 대한.. OS 브라우저 OS=> 안드로이드 IOS 이런거면 모바일기기로 읽어냄!
			WebKit : 되게 중요! 랜더링엔진 : html번역기 => 
			웹 표준화 : 어떤 브라우저, 어떤 것을 하든지 똑같이 표현되어야함!
			deligation을 한번은 해야함! 대표적으로 해야하는게 wc3..
			
	</pre>
<!-- <form action="수신자" method="전송방식"> -->
<form method="post" enctype=" application/json">
	<input type="text" name="dummy1">
	<input type="text" name="dummy2">
	<button type="submit">전송</button>
</form>
<table>
	<thead>
		<tr>
			<th>헤더 이름</th>
			<th>헤더 값</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${header}" var="entry">
			<tr>
				<td>${entry.key }</td>
				<td>${entry.value }</td>
			</tr>
		</c:forEach>		
	</tbody>
</table>
</body>
</html>