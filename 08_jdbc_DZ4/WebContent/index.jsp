<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Title</title>
</head>
<body>

<img src="pics/2.jpg" alt="img">
<p> LOGIN </p>
<form action="Controller" method="post">
	<input type="hidden" name ="command" value="forward" />
	<br/><input type="text" name="login" placeholder="login" />
	<br/><input type="text" name="password" placeholder="password" />
	<br/><input type="submit" value="OK" />
</form>


<p> For registration  go link below </p>
<a href = "/registration.jsp"> REGISTRATION LINK </a>

</body>
</html>