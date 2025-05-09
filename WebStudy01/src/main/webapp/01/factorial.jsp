<%@page import="java.util.Optional"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%!
long factorial(int op){
	if(op<=0){
		throw new IllegalArgumentException("팩토리얼 연산의 피연산자는 양의 정수");
	}
	if(op==1){
		return op;
	}else{
		return op * factorial(op-1);
	}
}

%>
<%
	String opParam = request.getParameter("op");
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form>
	<input type="number" name="op" min="1" max="10"
	value="<%=Objects.toString(opParam, "") %>"
	onchange="this.form.submit();" />
</form>
<%
int op = Optional.ofNullable(opParam)
		.filter(s->s.matches("[0-9]{1,2}"))
		.map(s->Integer.parseInt(s))
		.filter(n->n>=1 && n<=10)
		.orElse(-1);
		
boolean valid = op != -1;

if(valid){
%>
<h1><%=op %>! = <%=factorial(op) %></h1>
<%	
	long result = 1;
	for(int num=1; num<=op; num++){
		result *= num;
	}
	out.print(result);
%>
<%
}
%>
</body>
</html>