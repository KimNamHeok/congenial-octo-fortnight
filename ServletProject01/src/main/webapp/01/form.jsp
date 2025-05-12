<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="">
		<form action="/item/form" method="post" enctype="multipart/form-data">
			이름 : <input type="text" name="name" /><br/>
			이름 : <input type="file" name="boFile" /><br/>
			<input type="submit" value="전송" />		
		</form>
	</div>
</body>
</html>