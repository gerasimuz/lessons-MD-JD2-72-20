<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="ServletForJspElement" method="post">
		<input type="hidden" name="command" value="naming" /> Введите имя:<br />
		<input type="text" name="name" value="" /><br /> Введите фамилию:<br />
		<input type="text" name="surname" value="" /><br /> <input
			type="submit" value="Отправить" /><br />
	</form>
</body>
</html>