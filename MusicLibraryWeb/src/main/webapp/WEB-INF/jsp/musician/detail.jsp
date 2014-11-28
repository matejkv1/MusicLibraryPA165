<%-- 
    Document   : detail
    Created on : Nov 26, 2014, 7:37:50 PM
    Author     : Marián Macik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:layout title="${musician.name} - details">
    <jsp:attribute name="body">
        <div id="detail">
            <h1>${musician.name}</h1>
            <!--<form method="get" action="${pageContext.request.contextPath}/book/update/${book.id}">-->
                
                <form method="get" action="${pageContext.request.contextPath}/musician/update/${musician.id}">
                            <input type="submit" value="<fmt:message key='musician.list.edit'/>">
                </form>
            <!--</form>-->
            <br>
            <b><fmt:message key="musician.biography"/>: </b>
            <p><c:out value="${musician.biography}"/></p>
            
            <b><fmt:message key="musician.detail.albumstitle"/>:</b>
            <table class="basic">
            <tr>
                    <th><fmt:message key="album.title"/></th>
                    <th><fmt:message key="album.releaseDate"/></th>
            </tr>
            
            <c:forEach items="${albums}" var="album">
                    <tr>
                        <td><my:a href="/album/${album.id}"><c:out value="${album.title}"/></my:a></td>
                        <td><c:out value="${album.dateOfRelease}"/> 
                    </tr>
                </c:forEach>
            
            </table>
            
        </div>
    </jsp:attribute>
</my:layout>
