<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>News manager</title>
</head>
<body>
	<div>
		<c:out value="${param.errorMessage}"></c:out>
		<c:if test="${empty param.errorMessage}">
			Try again.
		</c:if>
	</div>
	<a href="controller?command=view_all_news">To news list</a>
</body>
</html>