<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="controller" method="post">
  <input type="hidden" name="command" value="save_new_user">
  Enter name:<br/>
  <input type="text" name="name" value="" /> <br/>
  Enter Surname:<br/>
  <input type="text" name="surname" value="" /> <br/>

  <input type="submit" value="Отправить" /><br/>
  </form>

</body>
</html>