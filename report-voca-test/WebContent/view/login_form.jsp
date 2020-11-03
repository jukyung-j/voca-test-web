<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<style>
		table{
			text-align:right;
			padding:10px;
		}
		body{
			padding-top:200px;
			text-align:center;
			width:50%;
			margin:0 auto; 
			
		}
	</style>
</head>
<body align="center">
	<h1>로그인 페이지</h1><hr>
	<form action='${pageContext.request.contextPath}/voca?action=login' method="POST">
		<table align="center">
			<tr>
				<td>아이디:</td><td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<td><br>비밀번호:</td><td><br><input type="password" name="pwd" /></td>
			</tr>
			<tr>
				<td><br><input type="submit" value="로그인" /></td>
				<td><br><input type="button" value="회원가입 " onclick="location.href='join_form'"/></td>
			</tr>
		</table>
	</form>
	
</body>
</html>
