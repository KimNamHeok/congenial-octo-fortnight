<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
    label {
      display: inline-block;
      width: 70px;
      margin-top: 10px;
    }
    .form-row {
      margin-bottom: 10px;
    }
    input, select, button {
      margin-top: 5px;
    }
  </style>
</head>

<body>
<form action="${pageContext.request.contextPath }/test" method="post">
	<label>아이디</label>
	<input type="text" name="userid" required>
	<button type="button">중복확인</button><br>
	
	<label>비밀번호</label>
	<input type="password" name="password" required><br>
	<label></label><input type="password" name="verifypassword" required><br>
	
	<label>이름</label>
	<input type="text" name="name" required><br>
	
	<label>주소</label>
	<input type="text" size="5" name="zipcode1" required>-
	<input type="text" size="5" name="zipcode2" required>
	<button type="button">우편번호</button><br>
	<input type="text" size="40" name="address1" required><br>
	<input type="text" size="40" name="address2" required><br>
	
	<label>휴대전화</label>
	<select name="cellphone1">
  	  <option value="010" selected>010</option>
	</select>
	<input type="text"  size="4" name="cellphone2" required>
	<input type="text" size="4" name="cellphone3" required><br>
	
	<label>전화번호</label>
	<select name="phone1">
  	  <option value="010" selected>010</option>
	</select>
	<input type="text" size="4"  name="phone2" required>
	<input type="text" size="4"  name="phone3" required><br>
	
	<label>이메일</label>
	<input type="text" name="email_id">
    <select name="email_domain">
      <option>@naver.com</option>
    </select><br>
	
	<label>생년월일</label>
<input type="radio" name="calendar" checked>양력
<input type="radio" name="calendar">음력<br>

<select name="birth_year" id="numberSelect1"></select>
<select name="birth_month" id="numberSelect2"></select>
<select name="birth_day" id="numberSelect3"></select><br>

<input type="checkbox" name="agree" checked> 네.. 뭐 동의 합니다.<br>

<button type="reset">가입취소</button>
<button type="submit">가입하기</button>

<script>
const select1 = document.getElementById("numberSelect1");
for (let i = 1900; i <= 2025; i++) {
  let option = document.createElement("option");
  option.value = i;
  option.textContent = i;
  if (i === 2025) {
    option.selected = true;
  }
  select1.appendChild(option);
}

const select2 = document.getElementById("numberSelect2");
for (let i = 1; i <= 12; i++) {
  let option = document.createElement("option");
  option.value = i;
  option.textContent = i;
  if (i === 1) {
    option.selected = true;
  }
  select2.appendChild(option);
}

const select3 = document.getElementById("numberSelect3");
for (let i = 1; i <= 31; i++) {
  let option = document.createElement("option");
  option.value = i;
  option.textContent = i;
  if (i === 1) {
    option.selected = true;
  }
  select3.appendChild(option);
}
</script>

</body>
</html>