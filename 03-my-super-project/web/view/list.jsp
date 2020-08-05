<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Отображение списка существущих пользователей</title>
</head>
<body>

<div>
    <h1>Super app!</h1>
</div>

<div>
    <div>
        <div>
            <h2>Users</h2>
        </div>



        <%
            List<String> names = (List<String>)request.getAttribute("userNames");

            if (names !=null && !names.isEmpty()){
                out.println("<ui>");
                for (String s: names){
                    out.println ("<li>" + s + "</li>");
                }
                out.println("<ui>");
            } else out.println ("<p>There are no users</p>");
        %>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
