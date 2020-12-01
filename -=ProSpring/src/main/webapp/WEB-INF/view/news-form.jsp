<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>news-form.jsp</title>
<link href="<c:url value="/resources/css/news.css" />" rel="stylesheet">
</head>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.local" var="locale" />

<fmt:message bundle="${locale}" key="reference.russian" var="button_ru" />
<fmt:message bundle="${locale}" key="reference.english" var="button_en" />
<fmt:message bundle="${locale}" key="head" var="table_logo" />
<fmt:message bundle="${locale}" key="button.create-news" var="button_create_news" />
<fmt:message bundle="${locale}" key="button.list-all-news" var="button_list_all_news" />
<fmt:message bundle="${locale}" key="copyright" var="copyright" />
<fmt:message bundle="${locale}" key="form.logo" var="form_logo" />
<fmt:message bundle="${locale}" key="form.section" var="form_section" />
<fmt:message bundle="${locale}" key="form.author" var="form_author" />
<fmt:message bundle="${locale}" key="form.brief" var="form_brief" />
<fmt:message bundle="${locale}" key="form.content" var="form_content" />
<fmt:message bundle="${locale}" key="reference.delete" var="reference_delete" />
<fmt:message bundle="${locale}" key="button.delete-selected" var="button_delete_selected" />
<fmt:message bundle="${locale}" key="button.back" var="button_back" />
<fmt:message bundle="${locale}" key="button.submit" var="button_submit" />
<fmt:message bundle="${locale}" key="reference.view" var="reference_view" />
<fmt:message bundle="${locale}" key="reference.edit" var="reference_edit" />
<fmt:message bundle="${locale}" key="reference.delete" var="reference_delete" />
<fmt:message bundle="${locale}" key="operation.update-news.logo" var="operation_update_news_logo" />

<c:url var="locale_en" value="/news/localization">
	<c:param name="locale" value="en"/>
	<c:param name="id" value="${news.id}"/>
</c:url>

<c:url var="locale_ru" value="/news/localization">
	<c:param name="locale" value="ru" />
	<c:param name="id" value="${news.id}"/>
</c:url>

<c:url var="add_update" value="/news/showFormForAddAndUpdate" />
<c:url var="find_all" value="/news/findAllNews" />


<body>

	<table border="1">

		<tr>
			<td><font color="black"><c:out value="${table_logo}" /></font></td>
			<td align="right" class="classverticalalignbottom"><font
				size="1" color="black"> 
				
	<a href="${locale_en}"><c:out value="${button_en}" /></a> 
    <a href="${locale_ru}"><c:out value="${button_ru}" /></a>
			
			</font></td>
		</tr>

		<tr>
			<td width="25%">
				        <form action="${add_update}" method="GET">
				 <input	type="submit" name="submit" value="${button_create_news}"
						class="myButton">
				</form>

				       <form action="${find_all}" method="GET">
					 <input	type="submit" name="submit" value="${button_list_all_news}"
						class="myButton">
				</form>
			</td>

			<td>
				<form:form action="createNews" modelAttribute="news" method="POST">


					<fieldset>
						<legend><c:out value="${form_logo}" /></legend>

						<form:hidden path="id" />

						<table>
							<tr>
								<td><c:out value="${operation_create_news_logo}" /></td>
								<td></td>
							</tr>
							<tr>
								<td><label for="author"><c:out value="${form_author}" /></label></td>
								<td><form:input path="author"/></td>
							</tr>

							<tr>
								<td><label for="brief"><c:out value="${form_brief}" /></label></td>
								<td><form:input path="brief" /></td>
							</tr>

							<tr>
								<td><label for="content"><c:out value="${form_content}" /></label></td>
								<td>
                
                <form:textarea path="content" rows="10" cols="50"></form:textarea>
                
                
                </td>
							</tr>
						</table>
						<input type="submit" name="submit" value="${button_submit}"
							class="myButton"> <input type="button"
							onclick="history.back();" value="${button_back}" class="myButton" />
						<br>
					</fieldset>

				</form:form>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><b></b></td>
		</tr>
	</table>
</body>
</html>