<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<h1>${cal.targetMonth }</h1>
<table>
	<thead>
		<tr>
			<c:forEach begin="1" end="7" var="idx" >
				<c:set value="${cal.fdow.plus(idx-1) }" var="turnweek"/>
				<th>${turnweek.getDisplayName(cal.textStyle, cal.locale) }</th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
	<c:set value="${cal.turnDate }" var="turnDate"/>
		<c:forEach begin="1" end="6">
			<tr>
			<c:forEach begin="1" end="7">
				<c:choose>	
					<c:when test="${turnDate.isBefore(cal.firstDate) }">
						<c:set value="before" var="clzValue"/>
					</c:when>
					<c:when test="${turnDate.isAfter(cal.endDate) }">
						<c:set value="after" var="clzValue"/>
					</c:when>
					<c:when test="${turnDate.isEqual(cal.today) }">
						<c:set value="today" var="clzValue"/>
					</c:when>
					<c:otherwise>
						<c:set value="${turnDate.dayOfWeek }" var="clzValue"/>
					</c:otherwise>
				</c:choose>
				<td class="${clzValue}">${turnDate.dayOfMonth}</td>
				<c:set value="${turnDate.plusDays(1) }" var="turnDate"/>
			</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
</table>
