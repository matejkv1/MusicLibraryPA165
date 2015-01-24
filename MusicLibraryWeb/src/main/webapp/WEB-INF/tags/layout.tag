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
            <table class="header">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/images/logo-small.png" alt="Index" height="64" width="64"></a></td>
                    <td><b>
                        <a href="${pageContext.request.contextPath}/musician/list"><fmt:message key="navigation.musicians"/></a>  |  
                        <a href="${pageContext.request.contextPath}/album/list"><fmt:message key="navigation.albums"/></a>  |  
                        <a href="${pageContext.request.contextPath}/song/list"><fmt:message key="navigation.songs"/></a>  |  
                        <a href="${pageContext.request.contextPath}/genre/list"><fmt:message key="navigation.genres"/></a>
                    </b><br/>
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
