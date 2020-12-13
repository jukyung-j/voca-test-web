<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Voca Test</title>
	<style>
		body{
			padding-top:200px;
			text-align:center;
			width:50%;
			margin:0 auto; 
			
		}
		span{
			padding:50px;
			
		}
	</style>
	<script>
	  function confirm_test() {
	        var confirm_test = confirm("로그아웃 하시겠습니까?");
	        if ( confirm_test == true ) {
	            location.href="${pageContext.request.contextPath}/voca?action=logout"
	        } else if ( confirm_test == false ) {
	            alert("취소를 누르셨습니다.");
	        }
	    }
</script>
</head>
<body>
	<c:import url="Header.jsp" />
	<span><input type="button" value="시험치기" onclick="location.href='voca/test?index=1'" /></span>
	<span><input type="button" value="점수확인" onclick="location.href='voca?action=scores'" /></span>
	<span><input type="button" value="로그아웃" onclick='confirm_test();' /></span>
</body>
</html>
