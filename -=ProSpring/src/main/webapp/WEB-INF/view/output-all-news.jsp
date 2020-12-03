<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>output-all-news.jsp</title>
<link href="<c:url value="/resources/css/news.css" />" rel="stylesheet">

</head>

<fmt:setLocale value="${sessionScope.locale}"/>
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
<fmt:message bundle="${locale}" key="reference.view" var="reference_view" />
<fmt:message bundle="${locale}" key="reference.edit" var="reference_edit" />
<fmt:message bundle="${locale}" key="reference.delete" var="reference_delete" />
<fmt:message bundle="${locale}" key="operation.view-all-news.logo" var="operation_view_all_news_logo" />

<c:url var="locale_en" value="/news/localization">
<c:param name="locale" value="en" />
</c:url>

<c:url var="locale_ru" value="/news/localization">
<c:param name="locale" value="ru" />
</c:url>

<c:url var="action_add_and_update" value="/news/showFormForAddAndUpdate" />
<c:url var="action_find_all_news" value="/news/findAllNews" />

<body>

<table border="0">

  <tr>
        <td width="18%">

        </td>
        <td align="center">
          <font size="5" color="black"><c:out value="${table_logo}"/></font>
        </td>

  </tr>
  
  <tr>
        <td width="18%">
            <form action="${action_add_and_update}" method="GET">
                <input type="submit" name="submit" value = "${button_create_news}" class="myButton">
            </form>

            <form action="${action_find_all_news}" method="GET">
                <input type="submit" name="submit" value="${button_list_all_news}" class="myButton"/>
            </form>
        </td>

        <td>
  
        <c:url var="deleteSelected" value="/news/deleteNewsSelected" />
  
        <form action="${deleteSelected}" method="GET">
            <fieldset>
            <c:out value="${operation_view_all_news_logo}" />

            <c:forEach var="news" items="${news}">
                <c:url var="view" value="/news/findNews">
                     <c:param name="id" value="${news.id}" />
                </c:url>

                <c:url var="edit" value="/news/updateNews">
                    <c:param name="id" value="${news.id}" />
                </c:url>

                <c:url var="delete" value="/news/deleteNews">
                    <c:param name="id" value="${news.id}" />
                </c:url>

    <table class="class1" border="0 ">
  
  <tr>

      <td>

        <table border=0 class="classhorisontalalightop">
            <tr>
                <td width="15%"> <c:out value="${form_author}" /></td>
                <td> <c:out value="${news.author}" /> </td>
            </tr>

            <tr>
                <td><c:out value="${form_brief}" /></td>
                <td> <c:out value="${news.brief}" /> </td>
            </tr>

            <tr>
                <td><c:out value="${news.date}" /></td>
                <td></td>
            </tr>

            <tr>
                <td colspan="2" align="left"><p align="left">
                    <a href="${view}"><c:out value="${reference_view}" /></a>
                    <a href="${edit}"><c:out value="${reference_edit}" /></a>
                    <a href="${delete}"><c:out value="${reference_delete}" /></a>
                    <input type="checkbox" name="selected_news" value="${news.id}" >
                </p>
                </td>
            </tr>
        </table>
     
      </td>
  </tr>
 </table>

</c:forEach>
            <input type="button" onclick="history.back();" value="${button_back}" class="myButton"/>
          	<input type="submit" name="submit" value = "${button_delete_selected}" class="myButton" align="right">

    <div align="right">
        <font size="3" color="black">
            <a href="${locale_en}"><c:out value="${button_en}" /></a>
            <a href="${locale_ru}"><c:out value="${button_ru}" /></a>
        </font>
    </div>
    </fieldset>
  </form>
  	</td>
  </tr>
</table>
</body>
</html>