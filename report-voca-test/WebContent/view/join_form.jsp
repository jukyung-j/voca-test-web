<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>

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
	<h1>회원가입 페이지</h1><hr>

	<form action='${pageContext.request.contextPath}/voca?action=join' method="POST">
		<table align="center" >
			<tr>
				<td>아이디:</td><td><input type="text" name="id" required /></td>
			</tr>
			<tr>
				<td><br>비밀번호:</td><td><br><input type="password" name="pwd" required /></td>
			</tr>
			<tr>
				<td><br>이름:</td><td><br><input type="text" name="name" required /></td>
			</tr>
			<tr>
				<td><br><input type="submit" value="회원가입" /></td>
				<td><br><input type="reset" value="다시작성 " /></td>
			</tr>
		</table>
	</form>
</body>
</html>
