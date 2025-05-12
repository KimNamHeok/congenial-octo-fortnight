<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>회원가입 결과</title>
</head>
<body>
    <h2>회원가입이 완료되었습니다!</h2>
    <p><strong>아이디:</strong> <%= request.getAttribute("userId") %></p>
    <p><strong>이름:</strong> <%= request.getAttribute("name") %></p>
    <p><strong>주소:</strong> <%= request.getAttribute("address") %></p>
    <p><strong>휴대전화:</strong> <%= request.getAttribute("phone") %></p>
    <p><strong>이메일:</strong> <%= request.getAttribute("email") %></p>
    <p><strong>생년월일:</strong> <%= request.getAttribute("birthDate") %></p>
    <p><strong>이메일 수신 동의:</strong> <%= request.getAttribute("agreement") %></p>
</body>
</html>