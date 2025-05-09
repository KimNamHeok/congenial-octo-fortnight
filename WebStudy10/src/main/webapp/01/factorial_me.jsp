<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
	long factorial(int num){
		if(num<=0){
			 throw new IllegalArgumentException("팩토리얼 연산의 피연산자는 양의 정수");
		}
		if(num==1) return 1;
		return num * factorial(num-1);
	}
%>
<form>
	<input type="number" name="num" min="1" max="10" onchange="this.form.submit();">
</form>
<% 
	String number  = request.getParameter("num");
	int num = (number !=null && !number.isEmpty()) ? Integer.valueOf(number) : 10;
	%>
<h1 id="res"><%= num %>! = <%= factorial(num) %></h1>
<%-- <hr>	
<h1>10! = </h1>
<% 	
	long result = 1;
	for(int op=1; op<=10; op++){
		result *= op;
	}
	
	out.print(result);
%> --%>

</body>
<script>
	let num = <%=num%>;
	let number = document.querySelector("input[name='num']");
	if(num !=null){
		number.value = num;
	}
</script>
</html>