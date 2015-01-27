<%-- 
    Document   : index
    Created on : Nov 25, 2014, 2:59:55 PM
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:message var="title" key="index.title"/>

<html lang="${pageContext.request.locale}">
    <head>
        <title><c:out value="${title}"/></title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
    </head>

    <sec:authorize access="hasRole('ADMIN')">
        <body style="background-color: #c9ead6">
    </sec:authorize>
    <sec:authorize access="!hasRole('ADMIN')">
        <body>
    </sec:authorize>
        <div style="position: fixed; right: 15px; top: 20px" class="login">
            <c:url value="/logout" var="logoutUrl" />
 
            <form action="${logoutUrl}" method="post" id="logoutForm" >
            </form>
                
            <script>
                function formSubmit() {
                    document.getElementById("logoutForm").submit();
                }
            </script>

            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <a href="${pageContext.request.contextPath}/login" ><fmt:message key="navigation.login"/></a>
            </c:if>
            <sec:authorize access="hasAnyRole('ADMIN', 'USER')">
                <a href="${pageContext.request.contextPath}/user" >${pageContext.request.userPrincipal.name}</a>
                (<a href="javascript:formSubmit()" ><fmt:message key="navigation.logout"/></a>)
            </sec:authorize>
        </div>
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
                    document.search_form.submit();
                }
            </SCRIPT>
                
        </div>
        
    </body>
</html>
