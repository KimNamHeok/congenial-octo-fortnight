<%@page import="java.util.List"%>
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
<h4>JSTL (Java Standard Tag Library)</h4>
<pre>
	JSTL : 동적 페이지 구성에 사용할 수 있는 커스텀 태그의 집합.
			일반 태그와 커스텀태그를 식별해야함. => 접두어
	커스텀 태그 : &lt;접두어:태그명 속성...&gt;
			=>	사용 방법 - 일반적인 HTML태그와 같음
	1. 태그 라이브러리 의존성 추가(dependency 추가) => jstl-api, jstl
			==> 넣어줬다고 무조선 사용 할수 없음
	2. 태그 라이브러리 로딩 : taglib 지시자(prefix 결정) 사용
	&lt;%@ taglib uri="jakarta.tags.core" prefix="c" %&gt;	
		내가 받은 라이브러리랑 같은 버전으로 uri 설정하기
		 prefix="c" 이게 바로 접두어!
		 uri="jakarta.tags.core" 그나마 쓰이는게 이거임!
		 
		 현재 필드에서 jsp는 거의 쓰이지 않음.
	
	EL이 가지지 못한 제어와 접근을 하는 역할을 해주는 것이 JSTL임! 그래서 둘이 붙어 다님!
	
	자주 활용되는 코어(c) 태그  속칭 c태그(prefix를 c로 사용하기 때문임)
		선언할때 var 사용(var="") ==> var는 속성
		1) 속성 제어 : set, remove
		<c:set scope="session" var="attr" value="VALUE"/>
		${attr } ==> JSTL의 set한값을 꺼내오는 EL
		이건 위험하게 사용하는 것임 scope를 명시하지 않았기 때문에 모든 scope에서 지울 수 있음! 그러니까 scope를 잘 작성하자
		<c:remove var="attr" scope="session"/>
		scope의 종류 : page() // 이페이지 안에서만 해당 속성 사용가능
					 request, session
					default => page() 
					
		속성이름, 값, 속성을 넣으려면 저장하려는 scope 필요 3가지 다 표현해야함.
			
		session에서 지울때 쓰는 태그 remove 
			
		2) 조건문 : if *주의) else가 없음, choose~when~otherwise
			choose~when~otherwise => 자바로 따지면 switch~case~default
			EL은 로직은 작성할 수 없지만 연산은 할수 없음 그러니까 조건에 EL사용해서 연산식을 넣어서 함!
			<c:if test="${empty attr}">
				조건을 만족 했을 때 실행 구문 == attr속성이 없음
			</c:if>
			
			이게 else를 표현하는 방법임 => 참일때 해야하는 조건을 반대로 했을때!
			조건  : empty attr 을 많이 씀 =>  
			<c:if test="${not empty attr}">
				조건을 만족 했을 때 실행 구문 == attr 속성이 있음
			</c:if>
			EL에서는 연산자의 기호보다 연산자의 키워드를 더 많이 사용함!
			자바에서 ! == not
			
			이건 다중 조건문이 들어가게 됨.
			<c:choose>
				<c:when test="${empty attr }">
					조건을 만족 했을 때 실행 구문 == attr속성이 없음
				</c:when>	
				<c:otherwise>
					조건이 없을 때 실행 구문 == attr 속성 없음
				</c:otherwise>		
			</c:choose>
		3) 반복문 : foreach, fortokens		
			자바에서 for형태 2가지 일반적 for, 향상된for문
				일반적 for문 for( int i=0(선언절 == 인덱스 선언);반복문의 종료조건식;증감절(반복돌때 마다 index를 얼마씩 증감할거니))
				향상된 for문 for( 선언절 : 반복집합(배열, Collection))
				forEach는 위의 두개와 같음
			<c:forEach var="i" begin="1" end="10" step="1">
				${i }
			</c:forEach>
			&lt;c:forEach var="i" begin="1" end="10" step="1">EL(i)&lt;/c:forEach>
			단, step에는 감소식은 넣을 수 없음. 그리고 step="1"일 경우 생략가능
			위의 절은 for(int i=1; i<=10; i++)와 같이 적은 것과 같음
			
			<c:set value="<%=List.of(10,20,30) %>" var="listAttr"/>
<%-- 			 ${vs.first} 처음일때 true ,${vs.last } 마지막일때 true ,${vs.index }번index ,${vs.count }번 반복 --%>
			<c:forEach items="${listAttr}" var="num" varStatus="vs">
				${num * 100 }
				<c:if test="${not vs.last}">
					,
				</c:if>
			</c:forEach>
			커스텀 태그는 향상된 for문에서 변수와 index를 둘다 줄 수 있음 그게 바로 varStatus
			
			varStatus 에서 LoopTag
			
			inti=4;
			int i = 4; 
			두개를 구분 할 수 있는 이유 :  token이 분리 되어있음!
			token : 문장의 의미를 부여할수 있는 최소한의 분리단위
			
			==> fortokens : 문장을 분리할수 있는 단위를 가지고 반복하는 반복문
							=> 문장을 줘야함!
							=> delims : token => 분리하는 기준?
							=> var로 나뉜 것을 반복해서 쓰는 것임!
			<c:forTokens items="아버지 가방에 들어가신다." delims=" " var="token">
				${token}
			</c:forTokens>
		4) url 재처리 : url(우리는 contextPath()붙였는데 그걸 자동으로 만들어주는 코어태그를 의미)
					 param(쿼리스트링을 만들어주는 코어태그)
			<a href="/05/model2/factorial.do">팩토리얼</a> => 이렇게 하면 동작 되지 않음
			<a href="<c:url value="/05/model2/factorial.do"/>">팩토리얼</a>
			value에 넣으면 이렇게 처리해줌 ==> "/ws01/05/model2/factorial.do"
			
			저렇게 넣으니 뭔가 이상함! 문법이 이상해 보임!
			<c:url value="/05/model2/factorial.do" var="facURL2"/>
			<a href="${facURL2}">팩토리얼(Model2)</a>
			
			피연산자 넘길수 있는 주소로 하고 싶음
			/01/factorial.jsp?num=10 이런식으로!
			
			하나하나 하드 코딩을 안하고 ?나 &를 빼먹지 않게금!
			<c:url value="/01/factorial.jsp" var="facURL1">
				<c:param name="op" value="10" />
				<c:param name="n1" value="p1" />
				<c:param name="n2" value="p2" />
			</c:url>
			href="/ws01/01/factorial.jsp?op=10&n1=p1&n2=p2" 와 같은 방식으로 만들어짐!
			
			<a href="${facURL1}">팩토리얼(Model1)</a>
			이렇게 할 수 있음!
					
</pre>
</body>
</html>