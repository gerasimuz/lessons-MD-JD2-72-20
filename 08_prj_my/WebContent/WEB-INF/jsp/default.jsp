<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>default page</title>

<style type="text/css">

			form, td{
				font-weight: bolder;
				width: 200px; 
    			background: #696969;				
			}
			#button{
				background: #800000;
				color: white;
			}
			div{
				margin: 5px;
			}
			
			label, legend, h1, h2, span, a {color: white}
			body {font-family: Roboto;}
			table {margin-left: 20px; border: 1; border-collapse: collapse;}
			a {height: 250px; margin-top: 30px;}
			h1,h2 {margin-left: 15px;}
			h4 {color: #800000}
			
</style>

</head>

<hr>

<!-- <body vlink="#cecece" alink="#ff0000"> -->

<table>
<col width="1000"> <col width="1000"> <tr> <td colspan="2">
	
	
<!-- <p><img src="img/icon2.png" alt="pic"></p> <hr> -->
	
	
<a href="controller?command=go_to_index">Main page</a>

<h1>



	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resources.locale" var="loc" />
	<fmt:message bundle = "${loc}" key = "locale.default.welcome_message" var="welcome_message"/>
	
	 	<div align = "right"> 
	  		<form action= "controller" method="post" > 
				<input type="hidden" name="command" value="change_locale">
				<input type="hidden" name="locale" value="ru">
				<input type="submit" name="submit" value="ru" /> 
			</form>
	 		
			<form action= "controller" method="post" > 
				<input type="hidden" name="command" value="change_locale">
				<input type="hidden" name="locale" value="en">
				<input type="submit" name="submit" value="eng" /> 
			</form>	
			
			
		</div>
	
</h1>

<h2> <div align = "center"> ${welcome_message} </div> </h2>

<form action="controller" method="post">
		<input type="hidden" name="command" value="authorization">

	<a>	Login: 	<input type="text" name="login" value="" /> <br> 
		
		Password:
		<input type="password" name="password" value="" /> <br> 
		<input type="submit" name="submit" value="Login" id="button" />
	</a>
	
</form>

	<h4> <c:out  value = "${requestScope.error}"/><br>	</h4>
	
<h3>			
	<a href="controller?command=goToRegistratioPage">Registration</a><br><br>

	<a href="controller?command=find_book">FindBook</a><br><br>

	<fieldset>
						<legend>Searching your book</legend>
						
						<div>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="search_genre">
						
							<select name="genre" id="genre">
								<option disabled value=">Genre" selected>Genre</option>										
								<option value="Comedy">Comedy</option>
								<option value="Novel">Novel</option>								
								<option value="Story">Story</option>						
							</select>
						<label for="genre">Genre</label><br>
						
						</form>	
						
						</div>
						
						<div>
						<input type="submit" value="Search" id="button"><br>
						</div>		
									
						
						</fieldset>

<div align= center> <fieldset>	<legend>Our novelty:</legend>
		<table border = "1">
 		<tr><td>Title</td><td>Price</td></tr>
 
 		<c:forEach items = "${requestScope.books}" var = "book">
		<tr>	
		<td> ${book.title}</td>
 		<td> ${book.price}</td> </tr>
 		</c:forEach> 
 		</table>
 </fieldset>
 </div>
 
 </table>
 
 
 
 </h3> 
 
 

</body>
</html>