<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

</head>
<body>
    <h2>회원가입 폼</h2>
    <form action="${pageContext.request.contextPath }/login" method="post">
        <label>아이디:</label> <input type="text" name="아이디" required><br>
        <label>비밀번호:</label> <input type="password" name="비밀번호" required><br>
        <label>이름:</label> <input type="text" name="이름" required><br>
        <label>주소:</label> <input type="text" name="주소"><br>
        <label>휴대전화:</label> <input type="text" name="휴대전화"><br>
        <label>이메일:</label> <input type="email" name="이메일" required><br>
        <label>생년월일:</label> <input type="date" name="생년월일"><br>
        <label>이메일 수신 동의:</label> <input type="checkbox" id="emailAgree" name="이메일동의" value="동의합니다"> 동의합니다<br>
        <button type="submit">회원 가입</button>
    </form>
</body>
</html>