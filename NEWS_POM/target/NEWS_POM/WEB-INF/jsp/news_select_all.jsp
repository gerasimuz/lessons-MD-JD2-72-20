<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<fmt:setLocale value="${sessionScope.local}"/>
	<fmt:setBundle basename="localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="local.href.CreateNews" var="create_news"/>
	<fmt:message bundle="${loc}" key="local.href.ViewAllNews" var="view_all_news"/>
	<fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
	<fmt:message bundle="${loc}" key="local.message.NewsTitle" var="news_title"/>
	<fmt:message bundle="${loc}" key="local.message.NewsBrief" var="news_brief"/>
	<fmt:message bundle="${loc}" key="local.message.NewsDate" var="news_date"/>
	<fmt:message bundle="${loc}" key="local.href.ViewNews" var="view_news"/>
	<fmt:message bundle="${loc}" key="local.href.EditNews" var="edit_news"/>
	<fmt:message bundle="${loc}" key="local.checkbox.CheckForDelete" var="check_for_delete"/>
	<fmt:message bundle="${loc}" key="local.button.DeleteCheckedNews" var="delete_checked_news"/>
	<fmt:message bundle="${loc}" key="local.message.Path" var="path"/>
	<title>News manager</title>
	<link href="/News_web_app/css/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
	<div id="header" class="big_block">
		<div id=title_div>News management</div>

		<div id="english_button_div">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="change_locale">
				<input type="hidden" name="local" value="en">
				<input type="hidden" name="fromPage" value="news_list_page">
				<input type="submit" value="${en_button}">
			</form>
		</div>
			<div id="russian_button_div">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="change_locale">
					<input type="hidden" name="local" value="ru">
					<input type="hidden" name="fromPage" value="news_select_all">
					<input type="submit" value="${ru_button}">
				</form>
			</div>

	</div>
	
	<div id="left_side_div" class="big_block">
		<a  href="controller?command=news_create">${create_news}</a><br>
		<a  href="controller?command=news_select_all">${view_all_news}</a>
	</div>
	
	<div id="main_div" class="big_block">
		<div id="path_div">${path}</div>
		<form action="controller" method="post">
		<input type="hidden" name="command" value="delete_few_news">
			<c:forEach items="${requestScope.newsList}" var="news">
				<div id="news_div">
					<div id="news_title">${news_title} ${news.getTitle().toString()}</div> 
					<div id="news_date">${news_date} ${news.getDate().toString()}</div>
					<div id="news_brief">${news_brief} ${news.getBrief().toString()}</div>
			
					<div id="view_news_ref"><a href="controller?command=to_news_page&newsId=${news.getId()}">${view_news}</a></div>  
					<div id="edit_news_ref"><a href="controller?command=to_edit_page&newsId=${news.getId()}">${edit_news}</a></div>
				
				
					<div id="check_box_div">
						<input type="checkbox" id="${news.getId()}" name="${news.getId()}" value="">
						<label for="${news.getId()}">${check_for_delete}</label>
					</div>
				</div><br>
			</c:forEach>
			<br>
		<input type="submit" value="${delete_checked_news}">
		</form>
	</div>
</body>
</html>