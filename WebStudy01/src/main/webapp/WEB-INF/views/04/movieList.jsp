<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/03/media">
	<select name="video" onchange="changeHandler(event);">
	${options }
	</select>
</form>
<div id='resultArea'></div>

<script>

function changeHandler(event){
	let select = event.target;
	console.log(select.value);
	resultArea.innerHTML = `<video src="${pageContext.request.contextPath}/03/media?\${select.name}=\${select.value}" autoplay controls></video>`
}
</script>
</body>
</html>










