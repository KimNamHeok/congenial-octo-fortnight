<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.vo.DummyVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> EL(Expression Language , 표현 언어)</h4>
<pre>
	: 표현식의 대체. 값을 출력하기 위한 목적을 가진 DSL		
		--> ${attributeName}, <%=request.getAttribute("attributeName") %>
		제어문의 형태가 없어서 로직 표현이 불가능한 언어.
		Model2 구조에서 Scope(Map) 내의 attribute(name, value) 에 접근하는 목적을 가진 언어.
		
<%
	String data = "DATA";
	request.setAttribute("data",data);
%>
<%=data %>, ${data}, <%=request.getAttribute("data") %>

<%
	request.setAttribute("requestAttr", "요청 속성");
	session.setAttribute("sessionAttr", "세션 속성");
%>
<%=request.getAttribute("requestAttr") %>, ${requestAttr }
<%=session.getAttribute("sessionAttr") %>, ${sessionAttr }
<%
	String[] array = new String[]{"e1", "e2"};
	request.setAttribute("array", array);
	request.setAttribute("mapAttr", Map.of("k-1", "v1", "k2", "v2"));
	
	DummyVO dummy = new DummyVO();
	dummy.setProp1("프로퍼티값");
	dummy.setProp2(34);
	request.setAttribute("dummy", dummy);
	
	request.setAttribute("attrSet", Set.of("e1", "e2"));
%>
${array[0] }, ${array[4] }, ${array2.length }
${mapAttr }, ${mapAttr.get("k-3").toString() }, ${mapAttr.k-1}, ${mapAttr["k-3"].toString() }
${dummy.getProp1() }, ${dummy.prop1 }, ${dummy["prop1"] }
${attrSet }
</body>
</html>


















