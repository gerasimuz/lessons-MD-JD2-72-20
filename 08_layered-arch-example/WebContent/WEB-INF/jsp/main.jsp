<%@ page language="java" import="by.htp.ellib.entity.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

</head>
	<hr> <p><img src="img/icon2.png" alt="pic"></p> <hr>
<body>
<a href="controller?command=go_to_index">Main page</a>

<h1>

<body>
<%
	String userName;
	User user;
	user = (User) request.getAttribute("user");
	userName = user.getName();
	int size = userName.toCharArray().length;
	System.out.println("size");
	
	if (size<1){
		userName = user.getLogin();
	}
	
%>


<h2> <div align = "center">  Hello, 

<%-- <% out.println(userName);%> --%>

<c:out value="${requestScope.user.name }"/> 
	</div>
</h2>



</body>
</html>