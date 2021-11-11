<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="../default/header.jsp"/>
<div class="wrap" style="text-align: center; margin-top: 20px;">
		<h2>${name}님의 정보</h2>
		<label>아이디 </label>${loginUser }<br>
		<label>비밀번호 </label>${pwd }<br>
		<label>주소 </label>${addr }<br>
		<input type="button" onclick="location.href='main'" value="메인으로">
	</div>
<c:import url="../default/footer.jsp"/>
</body>
</html>