<%-- 
    Document   : layout
    Created on : Nov 26, 2014, 12:52:11 PM
    Author     : Matej Bordáč
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <title><c:out value="${title}"/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <jsp:invoke fragment="head"/>
    </head>

    <body>
        <div id="header">
            <table class="basic">
                <tr>
                    <!--http://i.imgur.com/euNuShD.jpg default img-->
                    <td><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="Logo" height="64" width="64"></a></td>
                    <td><b>
                        <a href="${pageContext.request.contextPath}/musician/list"><fmt:message key="navigation.musicians"/></a>  |  
                        <a href="${pageContext.request.contextPath}/album/list"><fmt:message key="navigation.albums"/></a>  |  
                        <a href="${pageContext.request.contextPath}/song/list"><fmt:message key="navigation.songs"/></a>  |  
                        <a href="${pageContext.request.contextPath}/genre"><fmt:message key="navigation.genres"/></a>
                    </b></td>
                </tr>
            </table>
            <hr>
        </div>
                    
        <div id="content">
            <c:if test="${not empty message}">
                <div class="message"><c:out value="${message}"/></div>
            </c:if>
                <br/>
            <jsp:invoke fragment="body"/>
        </div>
    </body>
</html>
