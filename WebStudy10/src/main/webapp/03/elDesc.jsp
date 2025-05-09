<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.vo.DummyVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> EL(Expression Language, 표현 언어)</h4>
<pre>
	개발의 패턴이 변경되면서 Model1에서 Model2로 변경되면서 scope라는 저장소가 필요하게됨!
	그러면서 EL과 JSTL이 만들어짐!
	EL : 표현식의 대체 => 값을 출력하기 위한 목적을 가진 DSL(Domain S Language, 특수목적어)
					=> 제어문의 형태가 없어서 로직 표현이 불가능한 언어
					=> 반쪽자리언어를 써야하는 이유 - 특수한 환경 때문에 (Model2)
					=> Model2 구조에서 Scope(값을 저장하는 공간) 내의 attribute(저장된 값)에 접근하는 목적을 가진 언어
					=> Scope가없으면 EL을 사용하지 못함!
					=> attribute(name, value)를 가지고 있음 즉 Scope는 Map인것임!
					=> 그래서 값에 접근할때 ${attributeName}을 이용해서 나옴!
					=> EL과 표현식의 차이 : ${attributeName} ,<%=request.getAttribute("attributeName") %>
					=> attribute란 것이 없기 때문에 안나옴!
	<%
		String data = "DATA";
		request.setAttribute("data", data);
	%>
	<%=data %> 이렇게 접근하는것은 지역변수에만 접근할 수 있는 것임!
	EL을 식별할수 있을려면 토큰의 기호가 필요함 => ${data}
	==> 값이 나오지 않음
	==> scope에 값이 안담겨있기 때문에(request.setAttribute, session.setAttribute 등을 이용해서 담지 않음)
	EL을 표현식으로 표현 ${data} = <%=request.getAttribute("data") %>
	
	<%
		request.setAttribute("requestAttr", "요청 속성");
		session.setAttribute("sessionAttr", "세션 속성");
	%>
	request EL로 변경 : <%= request.getAttribute("requestAttr") %> => ${requestAttr}
	session EL로 변경 : <%= session.getAttribute("sessionAttr") %> => ${sessionAttr}
	
	${array } => 안나옴 scope에 저장이 안되어있어서 whiteSpace로 나옴
	<%
		String[] array = new String[]{"e1","e2"};
		request.setAttribute("array", array);
		request.setAttribute("mapAttr", Map.of("k-1","v1","k2","v2"));
		
		DummyVO dummy = new DummyVO();
		dummy.setProp1("프로퍼티값");
		dummy.setProp2(34);
		
		request.setAttribute("dummy", dummy);
		request.setAttribute("attrSet", Set.of("e1","e2"));
	%>
		Map.of("k1","v1","k2","v2"); => Map.of(key1, value1, key2, value2);
	${array } 
	배열이 가지고 있는 값 출력하기 => ${array[0]}
	배열이 가지고 있지 않는 값 출력하기 => ${array[4]}
								=> EL의 목적은 제어하고 처리하는 것이 아니기때문에 오류가 나도 조용히 whiteSpace로 변경해주는 것임
	가지고 있지 않는 값 출력하기 => ${array2.length}
							=> 자바코드였다면 오류가 나게됨. 그렇지만 EL의 목적은 위에서 말했다싶이 제어 처리가 아니라서 조용히 whiteSpace로 변경
	맵 전체의 값 꺼내기 => ${mapAttr}
	맵안의 두번째 value꺼내기 => 문제 자체가 잘못된것임 Map은 순차적이지 않음! key와 value만있음!
	1. ${mapAttr.get("k-3").toString() } => 순수한 자바코드로 생각하면 이렇게 하면 됨
	2. ${mapAttr.k-1} => EL은 객체가 가진 property에 접근하는 것처럼 이렇게 함 
	3. ${mapAttr["k-3"] } => 배열식 접근법(연상배열구조)으로도 접근 할 수 있음! 문자열로 묶여있으니까 문자열로! 근데 그럼 변수로도 넣어줄수 있겠다!
	
	각각의 차이점을 알아야함.
	EL은 지원하지 않는게 많지만 연산자를 지원함 -라는 산술연산자임
	EL은 문자열 - 숫자 하면 0-숫자로 강제로 변환함.
	
	그러면 2번째는 위험함! 2번째 것 제외!
	mapAttr.get("k-3").toString()  이렇게썼었을때 EL버전을 높은걸 사용해서 오류가 났음.
	 결론 1번째도 좋은 방식은 아님!
	
	==> 즉 3번째 접근방식으로 접근해라!
	
	${dummy } => DummyVO의 값 잘 출력함!
	
	EL은 자바스크립트의 문법구조와 유사함
	자바스크립트에선 Map에 접근하는 것이나 객체에 접근하는 것이나 똑같음!
	1. ${dummy.getProp1() } => 자바의 객체 가져오는 방식 : dummy의 필드를 잘 가져옴!
	2. ${dummy.prop1 } => 자바스크립트에서 객체를 가져오는 방식 : dummy의 값 잘 가져옴!
	3. ${dummy["prop1"]} => 자바스크립트에서 연상배열구조를 이용해서 객체를 가져오는 방식 : dummy의 값 잘 가져옴!
	
	결론 == 1번은 잘 사용하지 않음!! 그러니까 2,3번을 이용!
	
	${attrSet }
	아무리 EL이여도 set에서 값을 바로 직접적으로 1개를 꺼내올수 없음! 꺼내올 방법이 없음!
		
</pre>

</body>
</html>