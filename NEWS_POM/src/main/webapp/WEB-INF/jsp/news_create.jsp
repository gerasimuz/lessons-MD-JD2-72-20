<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<fmt:setLocale value="${sessionScope.local}"/>
	<fmt:setBundle basename="localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="local.href.CreateNews" var="create_news"/>
	<fmt:message bundle="${loc}" key="local.href.ViewAllNews" var="view_all_news"/>
	<fmt:message bundle="${loc}" key="local.editnews.message.PathCreate" var="path_create"/>
	<fmt:message bundle="${loc}" key="local.editnews.message.PathEdit" var="path_edit"/>
	<fmt:message bundle="${loc}" key="local.editnews.message.NewsTitle" var="news_title"/>
	<fmt:message bundle="${loc}" key="local.editnews.message.NewsBrief" var="news_brief"/>
	<fmt:message bundle="${loc}" key="local.editnews.message.NewsDate" var="news_date"/>
	<fmt:message bundle="${loc}" key="local.editnews.message.Content" var="news_content"/>
	<fmt:message bundle="${loc}" key="local.editnews.button.Create" var="create_news"/>
	<fmt:message bundle="${loc}" key="local.editnews.button.Edit" var="edit_news"/>
	<fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
	<fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
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
				<input type="hidden" name="fromPage" value="edit_page">
				<input type="hidden" name="newsId" value="${requestScope.news.getId()}">
				<input type="submit" value="${en_button}">
			</form>
		</div>

		<div id="russian_button_div">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="change_locale">
					<input type="hidden" name="local" value="ru">
					<input type="hidden" name="fromPage" value="edit_page">
					<input type="hidden" name="newsId" value="${requestScope.news.getId()}">
					<input type="submit" value="${ru_button}">
				</form>  
		</div>

	</div>
	
	<div id="left_side_div" class="big_block">
		<a  href="controller?command=news_create">${create_news}</a><br>
		<a  href="controller?command=news_select_all">${view_all_news}</a>
	</div>
	
	<div id="main_edit_div" class="big_block">
		<c:if test="${empty requestScope.news}">
		<div id="path_div">${path_create}</div>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="create_news">
				<div id="news_view_title">${news_title}</div> <div id="news_view_title_real"><input type="text" name="newsTitle"></div>
    			<div id="news_view_date">${news_date}</div> <div id="news_view_date_real"><input type="date" name="newsDate"></div>
    			<div id="news_view_brief">${news_brief}</div> <div id="news_view_brief_real"><textarea rows="5" cols="30" name="newsBrief"></textarea></div>
    			<div id="news_view_content">${news_content}</div> <div id="news_view_content_real"><textarea rows="10" cols="30" name="newsText"></textarea></div>
    			<div id="create_button_news_edit"><input type="submit" value="${create_news}"></div>
			</form>
		</c:if>
		<c:if test="${not empty requestScope.news}">
		<div id="path_div">${path_edit}</div>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="edit_news">
				<input type="hidden" name="newsId" value="${news.getId()}">
				<div id="news_view_title">${news_title}</div> <div id="news_view_title_real"><input type="text" name="newsTitle" value="${news.getTitle()}"></div>
    			<div id="news_view_date">${news_date}</div> <div id="news_view_date_real"><input type="date" name="newsDate" value="${news.getDate()}"></div>
    			<div id="news_view_brief">${news_brief}</div> <div id="news_view_brief_real"><textarea rows="5" cols="30" name="newsBrief">${news.getBrief()}</textarea></div>
    			<div id="news_view_content">${news_content}</div> <div id="news_view_content_real"><textarea rows="10" cols="30" name="newsText">${news.getText()}</textarea></div>
    			<div id="create_button_news_edit"><input type="submit" value="${edit_news}"></div>
			</form>
		</c:if>
	</div>
</body>
</html>