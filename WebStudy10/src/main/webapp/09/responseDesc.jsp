<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Http Response packaging (HttpServlet Response)</h4>
<pre>
	Response Line : Status Code (요청 처리 결과를 표현하는 3자리 숫자)
			100 ~ : ing...(websocket에서 사용됨)
			200 ~ : OK 지시자 (성공을 의미) -> 뜯어본다! -> 헤더 확인! -> content-body 확인! -> 맞으면 뿌려!
			300 ~ : response body가 없고,최종 처리가 되기 위해 클라이언트로 부터 추가액션이 필요함.
					302/304 (Moved) : 클라이언트가 요구 자원의 위치가 이미 서버에서 변경됐음을 의미
								Location:(자원의 새로운 위치) 헤더와 함께 응답이 전송됨.
								--> 새로운 위치를 대상으로 새로운 요청을 전송함
					304(Not Modified) : 정적자원에 대해 캐싱할때 쓰는..
										캐싱하는 이유 : 빠르게 응답하기 위해서
										모든 정적자원은 모두다 캐싱됨
									 	브라우저는 정적 자원데 대한 캐싱 정책이 있고,
									 	한번 로드한 정적 자원은 캐시 저장소에 저장한다.
									 	이미 캐싱된 정적 자원에 대해 서버쪽으로 요청을 보낸 경우,
									 	서버는 자원의 수정 여부를 응답으로 전송함
									 	캐싱된 이후 변경된 적이 없는 오래된 자원임을 표현할때 사용하는 상태 코드
									 	--> 캐싱된 자원을 로드하고 소비함
			<%--
				response.sendRedirect("http://localhost/ws01");
			--%>
			400 ~ : Fail clienet side error 
					404(Not Found) : 너가 입력한 주소가 잘못된거다.
					405(Method Not Allowed) : 너가 사용하고 있는 메서드는 허용하지 않는다. 
					400(Bad Request) : 요청이 잘못되었다. => 필수 파라미터 누락, 산술연산 피연산자가 파싱 불가해서 못함!
													 => 날짜데이터를 가지고 해야하는데 날짜 데이터가 아님!
										즉, 백엔드 개발자가 요청을 검증하는 경우에 직접 사용하는 경우가 많음!
					406(Not Acceptable) : 클라이언트의 요청 헤더 중 "accept" 헤더는 응답 컨텐츠의 종류를 결정함.
											(accept를 먼저꺼내고 판단해야함! 나는 그컨텐츠 못만든다! 하면 발생함)
										  accept 헤더에 해당하는 컨텐츠를 서버가 제공할 수 없음을 표현
										  ex) request Accept : application/xml - response content-type : application/xml
				    415(UnSupported Media Type) : 클라이언트의 요청 헤더 중 "content-type"헤더는 클라이언트의 전송 데이터의 종류를 표현함.
												  content-type에 해당하는 컨텐츠를 서버가 읽을 수 없음을 표현함.
												  이건 get방식에서는 나오지않음! content-type은 body가 있을때만 나오게 된다!
												  ex) request content-type : application/json -> 역직렬화가 불가능함을 표현함!
												  (너가 넘긴값 내가 해석 못하겠어!)			    
						 entity of the request == request의 body를 의미함!
					401(UnAuthorized) : 보안 처리에 사용되는 상태 코드, 미인증 사용자 표현(인증 절차를 유도 하기 위해 사용)
					403(Forbidden) : 보안 터리에 사용되는 상태 코드, 인증괸 사용자의 권함이 없음을 표현(접근 금지)
					401/403 : 보안처리할때 사용됨.
							403은 일반사용자용서비스와 관리자용 서비스가 있는데 일반사용자가 관리자용 서비스를 준것임!
							그럼 응답을 주는 것 대신 권한이 없다는 것을 표현해야함! 그걸 404로 표현하면 안됨!
							403 => 적절한 권한이 없음!
							401 => 로그인 조차 안되어있어서 권한확인도 안됨!(인증을 안받아서 확인 불가!)
							   ==> 인증을 받지 않았기 떄문에 권한도 확인 할 수 없다! 오류!
							   
							  401, 403으로 주느냐에 따라서 클라이언트의 행동이 봐뀌게 됨!
							  
			500 ~ : Fail 500(Internal Server Error) => 서버의 에러
					보통 500만 봄! 500이외의 것을 보면 피해라! 보안 1도 신경 안쓴다!
					
	Response Header : response body에 대한 메타데이터(name/value) 영역
			Content-Type : response body를 통해 전송되는 컨텐츠의 타입과 종류를 표현하는 MIME
			Content-Length : response body를 총해 전송되는 컨텐츠 길이
			<%--
				response.setContentLengthLong(100l);
			--%>
			Location
			Refresh : (초단위)주기적으로 자동 요청을 발생시키는 용도
						페이지에 대한 lock이 필요함! 동기 요청에서만 동작함
			<%--
				out.println(LocalDateTime.now());
				response.setHeader("Refresh","1");
			--%>
			Cache-Control : no-store(저장을 하지 않음.)
							no-cache(저장은 하지 않되, 저장된 캐시가 있으면 먼저 확인 유도)
							public(공용 캐시), 만료 시간 설정이 필요함.(공용캐시인경우 더 중요!)
							private(개인 캐시), 만료시간 설정이 필요함!
			
			<%
			// 금융사이트에선 무조건 no-store임! 우리는 언제 그런가? 개발서버 돌릴때 no-store임!
				response.setHeader("cache-control","no-cache");
				// 덮어씌워지게 됨 이런식으로 쓰면
				// response.setHeader("cache-control","no-store");
				response.addHeader("cache-control","no-store");
			%>
			CORS 헤더 : Access-Control-Allow-Origin => 이 출처에서 접근한 오리진이면 접근을 허용한다!
					ex)Access-Control-Allow-Origin : http://localhost:5173
					
	Response Body : message-body, content-body
</pre>
</body>
</html>