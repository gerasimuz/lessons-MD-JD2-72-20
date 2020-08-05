<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>USERS INFO</title>

</head>
<body>

<h2>Построенная таблица с данными</h2>	

<table border="1">
<tr> 
<td>Имя</td>
<td>Фамилия</td>
<td>Отчество</td>
<td>e-mail</td>
<td># телефона</td>
</tr>
<tr>
<td><%= request.getParameter("firstName") %></td>
<td><%= request.getParameter("secondName") %></td>
<td><%= request.getParameter("thirdName") %></td>
<td><%= request.getParameter("email") %></td>
<td><%= request.getParameter("mobile") %></td>
</tr>

</table>
</body>
</html>