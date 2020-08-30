<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index page</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<form action="ServletForJspElement" method="post">
		<input type="hidden" name="command" value="naming"/>
		Введите Имя: <br/>
		<input type="text" name="name" value=""/><br/>
		Введите фамилию<br/>
		<input type="text" name="surname" value=""/><br/>
		<input type="submit" value="Отправить">
	</form>
	
</body>
</html>