<%@page import="java.util.Optional"%>
<%@page import="java.util.Objects"%>
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
<% 
	String number  = request.getParameter("num");
%>
<form>
	<input type="number" name="num" min="1" max="10" value="<%=Objects.toString(number,"")%>" onchange="this.form.submit();">
</form>
<%
int op = Optional.ofNullable(number)
					.filter(s-> s.matches("[0-9{1,2}]"))
					.map(s-> Integer.parseInt(s))
					.filter(n-> n>=1 && n<=10)
					.orElse(-1);
boolean valid = op != -1;
if(valid){
%>
<h1 id="res"><%= op %>! = <%= factorial(op) %></h1>
<%-- <hr>	
<h1>10! = </h1>
<% 	
	long result = 1;
	for(int num=1; num<=op; num++){
		result *= num;
	}
	
	out.print(result);
%> --%>
<%
}
%>
</body>
</html>