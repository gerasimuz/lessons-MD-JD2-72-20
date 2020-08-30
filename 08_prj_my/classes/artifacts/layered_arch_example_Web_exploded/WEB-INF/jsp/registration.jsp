<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	


	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resources.locale" var="loc" />
	<fmt:message bundle = "${loc}" key = "locale.registration.enter_login_message" var="enter_login"/>
	<fmt:message bundle = "${loc}" key = "locale.registration.enter_password_message" var="enter_login"/>
	
	 	<div align = "right"> 
	  		<form action= "controller" method="post" > 
				<input type="hidden" name="command" value="change_locale">
				<input type="hidden" name="locale" value="ru">
				<input type="submit" name="submit" value="ru" /> 
			</form>
	 		
			<form action= "controller" method="post" > 
				<input type="hidden" name="command" value="change_locale">
				<input type="hidden" name="locale" value="eng">
				<input type="submit" name="submit" value="eng" /> 
			</form>
			
			
		</div>



	<form action="controller" method="post">
		<input type="hidden" name="command" value="registration">

		<%-- ${enter_login}:  --%>
		
		
	<h1> 

	<c:out value = "${requestScope.error}"/>

	</h1>
	
	<br>
	<br>

		Enter login:
		<input type="text" name="login" value="" /> <br /> 
		
	<%-- 	${enter_password}: --%>
		Enter password:
		<input type="password" name="password" value="" /> <br />
		
		Enter e-mail:

		<input type="email" name="email" value="" /> <br />
		
		Enter name:

		<input type="name" name="name" value="" /> <br /> 
		
		Enter surname:

		<input type="surname" name="surname" value="" /> <br /> 
		
		<input
		
			type="submit" name="submit" value="press me" />
			
			
	</form>
</body>
</html>