<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="mybean" beanName="_java._ee._02._servlet.SimpleBean"
		type="_java._ee._02._servlet.SimpleBean" scope="request" />



	<jsp:getProperty property="name" name="mybean" />
	<jsp:getProperty property="surname" name="mybean" />
	<jsp:getProperty property="date" name="mybean" />

	<jsp:useBean id="pageDate" class="java.util.Date" />

	<c:set target="${mybean}" property="date" value="${pageDate}" />
	<jsp:getProperty property="date" name="mybean" />

	<c:out value="${requestScope.mybean.name}" />


  











	
	
	
</body>
</html>