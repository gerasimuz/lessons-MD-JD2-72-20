<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta charset="utf-8">
	<title>NEWS INDEX PAGE</title>
</head>
<body>
<h1>hello to index page</h1>
<%--	<c:redirect url="controller?command=news_select_all" />--%>
<c:redirect url="controller?command=news_create" />

</body>
</html>