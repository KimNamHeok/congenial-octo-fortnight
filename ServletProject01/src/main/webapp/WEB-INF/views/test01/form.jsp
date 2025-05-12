<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/test" method="post">
	<label>아이디</label> <input type="text" name="아이디" required><button>중복확인</button><br>
	<label>비밀번호</label> <input type="password" name="비밀번호" required><br>
	<label></label> <input type="password" name="비밀번호확인" required><br>
	<label>이름</label> <input type="text" name="이름" required><br>
	<label>주소</label> <input type="text" name="우편번호 앞부분" required>-<input type="text" name="우편번호 뒤부분"><br>
					   <input type="text" name="상세주소1"><br>
					   <input type="text" name="상세주소2"><br>
	<label>휴대전화</label> <select name="휴대전화1">
  							<option value="010" selected>010</option>
						 </select>
						<input type="text" name="휴대전화2"><input type="text" name="휴대전화3"><br>
	<label>전화번호</label> <select name="전화번호1">
  							<option value="010" selected>010</option>
						 </select>
						<input type="text" name="전화번호2"><input type="text" name="전화번호3"><br>
	<label>이메일</label> <br><input type="email" name="이메일"><br>
	<label>생년월일</label> <input type="radio">양력<input type="radio">음력
						 <select id="numberSelect" />

<script>
  const select = document.getElementById("numberSelect");

  for (let i = 1900; i <= 2025; i++) {
    let option = document.createElement("option");
    option.value = i;
    option.textContent = i;
    if (i===2025) {
    	option.selected = true;
    }
    select.appendChild(option);
  }
</script>
</form>

</body>
</html>