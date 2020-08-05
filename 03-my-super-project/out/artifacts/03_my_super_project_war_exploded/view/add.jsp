<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Добавение нового пользователя</title>
</head>
<body>
    <div>
        <h1>Super APP!!</h1>
    </div>


    <div>
        <%
        if (request.getAttribute("userName")!=null){
        out.println("<p>User '" +request.getAttribute("userName")+"' added</p>");
        }
    %>
     <div>
        <div>
        <h2>Users</h2>
        </div>

<form action="/add" method="post">
    <label>Name:
        <input type="text" name="name"> <br/>
    </label>

    <label>Password:
        <input type="password" name="pass"> <br/>
    </label>
    <button type="submit">Submit</button>
</form>
     </div>
    </>div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>
</body>
</html>
