<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결과 페이지</title>
	<style>
		body{
			padding-top:200px;
			padding-bottom:100px;
			text-align:center;
			width:50%;
			margin:0 auto; 			
		}
	</style>
	<c:set var="i" value="1" />
</head>

<body>
	<h1>결 과 </h1>
	<c:import url="Header.jsp" />
	맞은 갯수 : ${cnt}개<br>
		<c:forEach var="arr" items="${result}">
			<table>
				<tr><td>${i}. ${arr}</td></tr>
			</table>
			<c:set var="i" value="${i+1}" />
		</c:forEach>
		<input type="button" value="처음으로" onclick="location.href='${pageContext.request.contextPath}/voca?action=log'"/>
</body>
</html>
