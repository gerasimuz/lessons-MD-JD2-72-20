<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="errorPage.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center">News Management</h2>
<table align="center" width="60%" border="1">
    <tr>
        <td align="top" width="20%">
            <table align="center">
                <tr>
                    <td><a href="/controller?command=news_select_all">News List</a></td>
                </tr>
                <tr>
                    <td><a href="/controller?command=news_create">Add News</a></td>
                </tr>
            </table>
        </td>
        
        <td>
            <table width="100%">
                <c:forEach items="${requestScope.news}" var="news">
                    <tr>
                        <td width="25%" align="left"><c:out value="${news.title}"/></td>
                        <td width="50%" align="left"><c:out value="${news.brief}"/></td>
                        <td width="25%" align="left"><c:out value="${news.date}"/></td>
                    </tr>
                    <!--  <tr>
                        <td><c:out value="${news.brief}"/></td>
                    </tr> -->
                    <tr>
                        <td></td>
                      <!--    <td><table width="100%"><tr>
                            <td align="center"><a href="newsview?command=byId&action=view&id=${news.id}" name="view">view</a></td>
                            <td align="center"><a href="editnews?command=byId&action=edit&id=${news.id}" name="edit">edit</a></td>
                        </tr></table></td> -->
                    </tr>
                    <tr><td><br><br></td></tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>


</body>
</html>
