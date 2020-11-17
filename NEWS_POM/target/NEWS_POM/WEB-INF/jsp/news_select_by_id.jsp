<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<fmt:setLocale value="${sessionScope.local}"/>
	<fmt:setBundle basename="localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="local.href.ViewAllNews" var="view_all_news"/>
	<fmt:message bundle="${loc}" key="local.href.CreateNews" var="create_news"/>

	<fmt:message bundle="${loc}" key="local.newsview.message.Path" var="path"/>
	<fmt:message bundle="${loc}" key="local.newsview.message.NewsTitle" var="news_title"/>
	<fmt:message bundle="${loc}" key="local.newsview.message.NewsDate" var="news_date"/>
	<fmt:message bundle="${loc}" key="local.newsview.message.NewsBrief" var="news_brief"/>
	<fmt:message bundle="${loc}" key="local.newsview.message.Content" var="news_content"/>
	<fmt:message bundle="${loc}" key="local.newsview.href.ToNewsList" var="to_news_list"/>
	<fmt:message bundle="${loc}" key="local.newsview.button.Edit" var="edit"/>
	<fmt:message bundle="${loc}" key="local.newsview.button.Delete" var="delete"/>
	<fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
	<title>News manager</title>
<%--	<link href="/News_web_app/css/style.css" rel="stylesheet" type="text/css"/>--%>
</head>
<body>
	<div id="header" class="head">
		<div id=title_div>News management</div>

		<div id="english_button_div">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="change_locale">
				<input type="hidden" name="local" value="en">
				<input type="hidden" name="fromPage" value="news_page">
				<input type="hidden" name="newsId" value="${requestScope.news.getId()}">
				<input type="submit" value="${en_button}">
			</form>
		</div>

		<div id="russian_button_div">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="change_locale">
					<input type="hidden" name="local" value="ru">
					<input type="hidden" name="fromPage" value="news_page">
					<input type="hidden" name="newsId" value="${requestScope.news.getId()}">
					<input type="submit" value="${ru_button}">
				</form>  
		</div>

	</div>
	
	<div id="left_side_div" class="big_block">
		<a  href="controller?command=to_edit_page">${create_news}</a><br>
		<a  href="controller?command=view_all_news">${view_all_news}</a>
	</div>
	
	<div id="main_view_div" class="big_block">
		<div id="path_div">${path}</div>
		
		<div id="news_view_title">${news_title}</div> <div id="news_view_title_real"><c:out value="${requestScope.news.getTitle()}"></c:out></div>
		<div id="news_view_date">${news_date}</div> <div id="news_view_date_real"><c:out value="${requestScope.news.getDate().toString()}"></c:out></div>
		<div id="news_view_brief">${news_brief}</div> <div id="news_view_brief_real"><c:out value="${requestScope.news.getBrief()}"></c:out></div>
		<div id="news_view_content">${news_content}</div> <div id="news_view_content_real"><c:out value="${requestScope.news.getText()}"></c:out></div>
			
		<div id="edit_button_news_view">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="to_edit_page">
				<input type="hidden" name="newsId" value="${requestScope.news.getId()}">
				<input type="submit" value="${edit}">
			</form>
		</div>
		<div id="delete_button_news_view">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="delete_news">
				<input type="hidden" name="newsId" value="${requestScope.news.getId()}">
				<input type="submit" value="${delete}">
			</form>
		</div>
	</div>
	
</body>
</html>