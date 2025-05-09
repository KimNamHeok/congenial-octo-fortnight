<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>form-data 구성 방법</h4>
<h4>훈련생 등록</h4>
<form method="post" enctype="application/x-www-form-urlencoded" action="${pageContext.request.contextPath }/07/formProcess">
<ul>
	<li>
	 	이름 : <input type="text" name="name" />
	</li>
	<li>
	 	나이 : <input type="number" name="age" />
	</li>
	<li>
	 	주소 : <input type="text" name="address" />
	</li>
	<li>
	 	최종학력 : 
	 	<input type="radio" name="grade" value="G1" />고졸
	 	<input type="radio" name="grade" value="G2" checked />대졸
	</li>
	<li>
	 	자격증 : 
	 	<select name="license" multiple>
	 		<option value>자격증 선택</option>
	 		<option value="L1">정보처리기사</option>
	 		<option value="L2">정보보안기사</option>
	 		<option value="L3">SQLD</option>
	 	</select>
	</li>
	<li>
	 	경력사항 : 
	 	<textarea name="career"></textarea>
	</li>
	<li>
	 	생년월일 : <input type="date" name="birthday" />
	</li>
	<li>
	 	취미 : 
	 	<input type="checkbox" name="hobbies" value="H1"/>게임
	 	<input type="checkbox" name="hobbies" value="H2"/>독서
	 	<input type="checkbox" name="hobbies" value="H3"/>개발
	</li>
	<li>
	 	<button type="button">걍버튼</button>
	 	<button type="submit">전송</button>
	 	<button type="reset">취소</button>
	</li>
</ul>
</form>
<pre>
	input : type(입력 데이터의 종류, 입력 태그의 형태), name(paramerter명, part명)
			value(초기값, file type 의 경우  사용할 수 없음.)
			disabled(전송 데이터에서 제외, boolean), required(필수입력 여부, boolean)
			readonly(값의 변경 가능 여부, boolean)
			입력값이 없어도 ""이 전송됨.
	select : name(parameter명, part명)
			disabled(전송 데이터에서 제외, boolean), required(필수입력 여부, boolean)
			readonly(값의 변경 가능 여부, boolean)
			option 으로 value가 결정됨.
			option label(사용자에게 보여줄 텍스트), value(전송 데이터 값)
	input : type="checkbox | radio" 선택 여부(checked)로 값을 표현.
	input : type="file" 선택한 파일의 2진 데이터 전송 구조가 필요함(multipart/*)
</pre>

</body>
</html>