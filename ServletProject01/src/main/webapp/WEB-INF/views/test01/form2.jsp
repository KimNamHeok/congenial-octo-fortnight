<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Register All Form</title>
<style>
th {
    width: 200px; /* 원하는 너비 설정 */
    text-align: left; /* 왼쪽 정렬 */
}
</style>
</head>
<body>
    <form action="${pageContext.request.contextPath }/test2" method="post">
        <h1>Register All Form</h1>
        <table border="1">
		<tr>
	        <th>유저 ID</th>
	        <th><input type="text" name="userID"></th>
		</tr>
		<tr>
	        <th>패스워드</th>
	        <th><input type="password" name="password"></th>
		</tr>
		<tr>
	        <th>이름</label></th>
	        <th><input type="text" name="name" ></th>
		</tr>
		<tr>
	        <th>E-Mail</label></th>
	        <th><input type="email" name="email" ></th>
		</tr>
		<tr>
	        <th>생년월일</label></th>
	        <th><input type="date" name="birthdate" ></th>
		</tr>
		<tr>
	        <th>성별</label></th>
	        <th>
	        <input type="radio" name="gender" value="Male" checked> Male
	        <input type="radio" name="gender" value="Female"> Female
	        <input type="radio" name="gender" value="Other"> Other
	        </th>
		</tr>
		<tr>
	        <label for="developer">개발자 여부:</label>
	        <input type="checkbox" id="developer" name="developer" checked> 개발자
		</tr>
		<tr>
	        <label for="foreigner">외국인 여부:</label>
	        <input type="checkbox" id="foreigner" name="foreigner" checked> 외국인
		</tr>
		<tr>
	        <label for="nationality">국적:</label>
	        <select id="nationality" name="nationality">
	            <option value="대한민국">대한민국</option>
	        </select>
		</tr>
		<tr>
	        <label for="vehicles">소유차량:</label>
	        <select id="vehicles" name="vehicles" multiple>
	            <option value="JEEP">JEEP</option>
	            <option value="BMW">BMW</option>
	            <option value="Audi">Audi</option>
	            <option value="VOLVO">VOLVO</option>
	        </select>
		</tr>
		<tr>
	        <label for="hobbies">취미:</label>
	        <input type="checkbox" id="sports" name="hobbies" checked> Sports
	        <input type="checkbox" id="music" name="hobbies"> Music
	        <input type="checkbox" id="movie" name="hobbies" checked> Movie
		</tr>
		<tr>
	        <label for="postalCode">우편번호:</label>
	        <input type="text" id="postalCode" name="postalCode" value="09809">
		</tr>
		<tr>
	        <label for="address">주소:</label>
	        <input type="text" id="address" name="address" value="대전광역시 중구 오류동 123">
		</tr>
		<tr>
	        <label for="card1Number">카드1 - 번호:</label>
	        <input type="text" id="card1Number" name="card1Number" value="12345">
		</tr>
		<tr>
	        <label for="card1Expiry">카드1 - 유효년월:</label>
	        <input type="date" id="card1Expiry" name="card1Expiry" value="2025-05-13">
		</tr>
		<tr>
	        <label for="card2Number">카드2 - 번호:</label>
	        <input type="text" id="card2Number" name="card2Number" value="56789">
		</tr>
		<tr>
	        <label for="card2Expiry">카드2 - 유효년월:</label>
	        <input type="date" id="card2Expiry" name="card2Expiry" value="2025-05-14">
		</tr>
		<tr>
	        <label for="introduction">소개:</label>
	        <textarea id="introduction" name="introduction">반갑습니다!</textarea>
		</tr>
	        <button type="submit">등록</button>
	        <button type="reset">초기화</button>
		</table>
    </form>
</body>
</html>