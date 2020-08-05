
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta charset="UTF-8">
    <title>My super project!</title>
</head>
<body>
<!-- header -->
<div><h1>Super app</h1></div>

<div> <!-- content -->
    <div>   <!-- buttons holder -->
        <button onclick="location.href='/list'">List users</button>
        <button onclick="location.href='/add'">Add user</button>
    </div>
</div>
</body>
</html>