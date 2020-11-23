<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>시험 결과</title>
	<style>
		body{
			padding-top:200px;
			text-align:center;
			width:50%;
			margin:0 auto; 
		}
	</style>
</head>
<c:set var="i" value="1" />
<body>
	<h1>시험 결과들</h1>
	<c:import url="Header.jsp" />
	<c:forEach var="arr" items="${scores}">	
		<table>
			<tr><td>${i}차시. ${arr}점</td></tr>
		</table>
		<c:set var="i" value="${i+1 }" />
	</c:forEach>
	<input type="button" value="이전으로" onclick="window.history.go(-1);" /> 
</body>
</html>
