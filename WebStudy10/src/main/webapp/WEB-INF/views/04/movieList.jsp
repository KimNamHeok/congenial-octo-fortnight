<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/03/media">
	<select name="video" onchange="changeHandler(event)">
	<!-- 글로벌 이벤트 : event객체의 이름은 event로 고정되어있음.
	이름을 따로 주고싶으면 addEventListener를 사용 -->
<!-- 변화하지않음 템플릿, jsp안에서 사용되는 것은 자바언어임. JSP는 자바라는 언어를 기반으로 템플릿엔진역할을 해주는 것임.
	템플릿과 데이터를 연결해주는 템플릿엔진이라고 함.
 -->
		<%=request.getAttribute("options") %>
	</select>
	<div id="resultArea"></div>
</form>
<script>
function changeHandler(){
	let select = event.target;
	console.log(select);
	resultArea.innerHTML = `<video src="${pageContext.request.contextPath}/03/media?\${select.name}=\${select.value}" autoplay controller></video>`
	// ${pageContext.request.contextPath} js안에서 사용시 ${}는 표현언어임 그래서 템플릿 연산자를 알기 위해서 \${}써야함
	// 근데 좋은 방식은 아님. 
}
</script>
</body>
</html>