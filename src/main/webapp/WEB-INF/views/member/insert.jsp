<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../default/header.jsp" />
	<div class="wrap" style="text-align: right; margin-top: 20px;">
		<form action="insert" method="post">
			아이디: <input type="text" placeholder="input id" name="id"><br>
			비밀번호: <input type="text" placeholder="input password" name="pwd"><br>
			이름: <input type="text" placeholder="input name" name="name"><br>
			주소: <input type="text" placeholder="input address" name="addr"><br>
			<input type="submit" value="가입">
		</form>
	</div>
	<c:import url="../default/footer.jsp" />
</body>
</html>