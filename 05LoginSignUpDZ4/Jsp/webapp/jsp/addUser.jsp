
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    Please add User
    <form method="post" action="/users">
        <label for="first-name"> First Name
            <input class="input-field" type="=text" id="first-name">
        </label>

        <label for="last-name"> Last Name
            <input class="input-field" type="=text" id="last-name">
        </label>

<%--        <label for="password"> User password--%>
<%--            <input class="input-field" type="=password" id="password">--%>
<%--        </label>--%>
        <input type="submit" value="Add User">
    </form>

</body>
</html>
