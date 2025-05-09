<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>자원의 식별 체계</h4>
<pre>
	자원의 종류를 구별할 수 있어야함.
	종류를 나눌 수 있는 기준 :  자원의 위치
	대표적으로 3가지 형태로 구분됨.
	1. 파일 시스템 자원 : 파일 시스템 상의 절대경로(물리 경로)를 통해 접근하는 자원
		-- 파일 시스템상의 전체주소(물리 주소상의 주소로 이동)
		ex)D:\00.medias\images\cat1.jpg
	2. 클래스 패스 자원 : 빌드 및 실행환경에 존재하는 자원으로 
					  클래스 패스 이후의 경로를 qulified name의 형태로 접근하는 자원
		ex) kr.or.ddit.servlet01.HelloServlet
		-- dummy.xml이 필요해 그러면 어떻게 해야할까
				ex) /kr/or/ddit/dummy.xml (copy qulified name에서 잘라낸 부분!)
	    -- 1번과 결정적인 차이 : 클래스라는 자원은 물리경로를 통해서 접근 불가 ==> 진짜가 아님
	    					변경되지않는 경로로 접근해야함. 변경되지 않는 절대경로 : qulified name
							-- 논리경로의 형태인 것임!
		-- 클래스 패스가 존재하려면 적어도 빌드환경과 실행환경이 있어야함.
		-- 현재 target아래에 classes안에 들어있는 자원들!
		-- 식별을 할때엔 k.o.d.s.H 이런식으로 qulified name으로 함!
	3. 네트워크 자원(웹 자원) : 네트워크를 통해 서비스 되는 자원으로 URL이라는 식별체계를 통해 접근하는 자원.
		-- 1번과 2번의 공통점: 네트워크 상에서 제공x URL이 없어서!
		-- 네트워크 자원의 특성 : url필요
		-- 네트워크 자원은 서버라는 개념이 필요. 그게 없으면 서비스 불가능!
		-- 서버의 위치에 따라 자원의 위치도 변경됨!
		ex) https://www.google.com/logos/2024/moon/moon_april-rc2/cta.png
		
		URL : 3가지 구성요소 - 서버식별"/"컨텍스트패스"/"컨텍스트내에서자원의패스
		서버식별 : 서버가 공개하고 있는 독베이스까지 들어가기위한 가상의 주소임!
		그이후의 주소는 물리경로상 있는것 보통 그대로 감.
		
		URL은 왜 필요한가? 네트워크상에서 자원에 접근할때 진짜 주소가 아닌
		URI (Uniform Resource Identifier) : 네트워크를 통해 서비스되는 자원을 표현하는 식별체계에 대한 포괄적인 명칭
				네트워크 자원 식별
		URN (Uniform Resource Name) : 자원의 이름으로 식별 
			=> 이름이 중복 될 수 있음.이름이 겹치면 식별자라는 게 완성될 수 없음
			=> 이름을 모르면 이름을 못부름!출석부라는 개념이 필요 모든 자원의 이름을 등록해놓은 메모리 공간 필요!
			=> 식별자로선 영.. 광범위하게 쓰이지않음
		URC (Uniform Resource Content) : 자원의 속성으로 식별 
			=> 하나의 자원이 가지고 있는 여러가지 속성들을 가지고 자원을 식별한다
			=> 특성을 가지고 있는 자원이 많을 수 있음.
			=> 얘도 식별자로선 영..
		URL (Uniform Resource Locator) : 서버의 설정으로 자원에 대한 위치가 논리적으로 결정된 식별체계
			=> 기준점을 기준으로 위치를 통해서 자원 식별 => 하나밖에 없음!
			=> 식별가능
			=> 식별되는 기준이 없다면 기준점은 끝도 없이 올라갈수 있음
			=> url을 표현하려면 어느 위치인가 시작점을 정확하게 해줘야함
			=> 시작점 == 독베이스
			=> 논리적 : 가상으로
			=> scheme://IP[Domain]:port - 서버에 대한 식별(자원에 대한 출처 - origin)
				scheme는 대부분 프로토콜을 표현하기 위해 사용됨
				origin => 동일 출처의 원칙 (현재페이지와 동일한출처면 로딩이 가능 아니면 비동기로 해도 로딩안됨!)
				origin 자바스크립트시간에 했음!
			=> origin 이후의 모든 경로 - path
				path : context path + resource path
				
			절대 경로 : url을 full path로 표현
					 origin이 생략된 path로 표현 -- 빠져있는 origin을 location에 넣어줌!
					 origin이 생략 가능한 경우 :  location의 origin과 자원의 origin이 동일 할때
					 -- 경로의 시작점이 정해져있음.
			상대 경로 : 현재 document의 path를 기준으로 경로를 표기하는 방식 
					-- 경로의 시작점이 동적으로 바뀜. 경로의 시작점을 정해줘야함. 파일이 기준이 아니고 작성하는 document가 기준
					-- . => 파일이 들어있는 현재 폴더 // ..==> 현재 폴더의 상위 폴더
</pre>

<img src="../resources/images/cat1.jpg">
<!-- <img src="http://localhost:80/ws01/resources/images/cat1.jpg"> -->
<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg">
<!-- 2번을 요청 했지만 같은 경로로 인식했기 때문에 한번만 요청을 했음 -->
<!-- 
	url 표현시 완전한 url 표현 혹은 path를 표현하면 됨! 동일한 contextPath는 변경이 가능함! 그렇기때문에
	표현식으로 넣어주는 것이 좋음!
 -->
<img src="https://www.google.com/logos/2024/moon/moon_april-rc2/cta.png">
<!-- 브라우저는 현재 document가 가지고 있는 오리진을 그대로 가져다가 붙여줌! 브라우저가 소비하는 오리진형식-->
<!-- 
	location이 가지고 있는것 : 자원의 식별
	즉 url을 가지고 있음.
	지금 우리가 사용하고 있는 이 이미지에서 document의 오리진과 소비하고 있는 자원의 origin이 동일하면 생략이 가능함.
	만일 document의 오리진과 소비하고 있는 자원의 오리진이 다르면 생략 불가능!
 -->
</body>
</html>