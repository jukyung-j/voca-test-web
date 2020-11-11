<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Voca Test Testing</title>
	<style>
		body{
			padding-top:200px;
			text-align:center;
			width:50%;
			margin:0 auto; 
		}
	</style>
</head>
<body>
	<c:import url="Header.jsp" />
	<form action="${pageContext.request.contextPath}/voca/test?index=${index+3}" method="POST">
	<c:forEach var="i" items="${eng}" >

	 	<table>
			<tr>
				<td>${index}. ${i}</td>
			</tr>
			<tr>
				<td>답: <input type="text" name="ans${index}" /></td>
			</tr>
		</table>
		<c:set var="index" value="${index+1}" />
	</c:forEach>
	<c:if test="${index>4}">
		<input type="button" value="이전" onclick="window.history.go(-1)" />
	</c:if>
		<input type="submit" value="다음" />
	</form>
</body>
</html>
