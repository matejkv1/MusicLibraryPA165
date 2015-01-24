<%-- 
    Document   : index
    Created on : Nov 25, 2014, 2:59:55 PM
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message var="title" key="index.title"/>
<%--<my:layout title="${title}">--%>
<%--<jsp:attribute name="body">--%>
<html lang="${pageContext.request.locale}">
    <head>
        <title><c:out value="${title}"/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <%--<jsp:invoke fragment="head"/>--%>
    </head>

    <body>
        <div id="index">
            <img src="${pageContext.request.contextPath}/resources/images/logo.png">
            <br>
            <a href="${pageContext.request.contextPath}/musician/list"><fmt:message key="navigation.musicians"/></a>  |  
            <a href="${pageContext.request.contextPath}/album/list"><fmt:message key="navigation.albums"/></a>  |  
            <a href="${pageContext.request.contextPath}/song/list"><fmt:message key="navigation.songs"/></a>  |  
            <a href="${pageContext.request.contextPath}/genre/list"><fmt:message key="navigation.genres"/></a>
        </div>  
        
        <div id="search_div">
            <form name="search_form" onsubmit="OnSubmitForm()" method="get">
                <fmt:message key="index.search.title"/> <input type="text" id="q" name="q" />
                <select id="search_selection">
                    <option value="song"><fmt:message key="index.search.selection.song"/></option>
                    <option value="album"><fmt:message key="index.search.selection.album"/></option>
                    <option value="musician"><fmt:message key="index.search.selection.musician"/></option>
                </select>
                <input type="submit" value=<fmt:message key="index.search.submit"/> />
            </form>
            
            <SCRIPT language="JavaScript">
                function OnSubmitForm()
                {
                    var contextPath='<%=request.getContextPath()%>';
                    var query = document.getElementById('q').value;   
                    var type = document.getElementById('search_selection').value; 
                    document.search_form.action=contextPath+"/"+type+"/search?q="+query;
                }
            </SCRIPT>
                
        </div>
        
    </body>
</html>
<%--</jsp:attribute>--%>
<%--</my:layout>--%>
