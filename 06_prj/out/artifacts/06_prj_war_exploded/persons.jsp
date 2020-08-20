<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Persons JSP Page</title>
</head>
<body>
    
    <tr>
        <td><c:out value="${person.key}" /></td>
        <td><c:out value="${person.value}" /></td>
    </tr>
        <table border="10px">
            <tr>
                <th>Name</th>
                <th>Age</th>
            </tr>
            <c:forEach var="person" items="${persons}">
            <tr>
                <td><c:out value="${person.key}" /></td>
                <td><c:out value="${person.value}" /></td>
            </tr>
        </c:forEach>

        </table>
</body>
</html>
