<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="../default/header.jsp"/>
<div class="wrap" style="text-align: right; margin-top: 20px;">
		<c:choose>
			<c:when test="${userid == null }">
				<form action="loginChk" method="post">
					<input type="text" name="id" placeholder="input id"><br>
					<input type="password" name="pwd" placeholder="input password"><br>
					<input type="submit" value="로그인"> 
					<a href="insert">회원가입</a>
				</form>
			</c:when>
			<c:otherwise>
				${userid} 님 환영합니다 <br>
				<a href="main">home으로</a>
			</c:otherwise>
		</c:choose>


	</div>
<c:import url="../default/footer.jsp"/>
</body>
</html>




