<%-- 
    Document   : layout
    Created on : Nov 26, 2014, 12:52:11 PM
    Author     : Matej Bordáč
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

    <sec:authorize access="hasRole('ADMIN')">
        <body style="background-color: #c9ead6">
    </sec:authorize>
    <sec:authorize access="!hasRole('ADMIN')">
        <body>
    </sec:authorize>
        <div id="header">
            <div style="position: fixed; right: 22px; top: 20px; text-align: right" class="login">
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
            <table class="header">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/images/logo-small.png" alt="Index" height="64" width="64"></a></td>
                    <td><b>
                        <a href="${pageContext.request.contextPath}/musician/list"><fmt:message key="navigation.musicians"/></a>  |  
                        <a href="${pageContext.request.contextPath}/album/list"><fmt:message key="navigation.albums"/></a>  |  
                        <a href="${pageContext.request.contextPath}/song/list"><fmt:message key="navigation.songs"/></a>  |  
                        <a href="${pageContext.request.contextPath}/genre/list"><fmt:message key="navigation.genres"/></a>
                        </b>
                        <br/>
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
                    </td>
                </tr>                
            </table>
            <hr>
        </div>
                    
        <div id="content">
            <c:if test="${not empty message}">
                <div class="message"><c:out value="${message}"/></div>
                <br/>
            </c:if>
                
            <jsp:invoke fragment="body"/>
        </div>
    </body>
</html>
