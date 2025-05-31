<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
  <head>
    <title><sitemesh:write property="title"/></title>
    <%@ include file="/WEB-INF/view/prod/prodList.jsp" %>
  </head>
  <body>
  	<h1>사이트메시 공통 헤더</h1>
    <sitemesh:write property="body"/>
    
    <%@ include file="/WEB-INF/fragments/postScript.jspf" %>
  </body>
</html>