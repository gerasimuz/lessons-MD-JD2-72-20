<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
 BOOk
 
<%--  <%
 java.util.List<by.htp.ellib.entity.Book> books = (java.util.List<by.htp.ellib.entity.Book>)request.getAttribute("books");
 
 for (int i=0; i<books.size(); i++){
	 by.htp.ellib.entity.Book b = books.get(i);
	 out.println(b.getTitle()+"- "+ b.getPrice());
	 
 }
  
 %> --%>
 
 <table border = "1">
 <tr><td>Title</td><td>Price</td></tr>
 <c:forEach items = "${requestScope.books}" var = "book">
	<tr>	
	<td> ${book.title}</td>
 	<td> ${book.price}</td> </tr>
 </c:forEach> 
 </table>
 
</body>
</html>