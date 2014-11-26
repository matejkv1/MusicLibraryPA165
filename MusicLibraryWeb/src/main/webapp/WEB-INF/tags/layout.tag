<%-- 
    Document   : layout
    Created on : Nov 26, 2014, 12:52:11 PM
    Author     : Matej Bordáč
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <p><b>Hello, I am header</b></p>
            <hr>
        </div>
        
        <div id="content">
            <jsp:invoke fragment="body"/>
        </div>
    </body>
</html>