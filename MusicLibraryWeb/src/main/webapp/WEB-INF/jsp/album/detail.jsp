<%-- 
    Document   : detail
    Created on : Nov 26, 2014, 7:37:50 PM
    Author     : Matej Bordáč
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:layout title="${album.title} - details">
    <jsp:attribute name="body">
        <div id="detail">
            <h1>${album.title}</h1>
            
            <form method="get" action="${pageContext.request.contextPath}/album/update/${album.id}">
                <input type="submit" value="<fmt:message key='album.detail.edit'/>">
            </form>
            <br>
            
            <img src="${album.albumArt}" height="256" width="256" onerror="this.src='${pageContext.request.contextPath}/resources/images/default-album.png'"><br>
            
            <br>
            
            <b><fmt:message key="album.detail.musicianName"/>: </b>
                
                    <a href="${pageContext.request.contextPath}/musician/${musician.id}"><c:out value="${musician.name}"/></a>
                    <br>
            <b><fmt:message key="album.detail.releaseDate"/>: </b><c:out value="${album.dateOfRelease}"/><br>
            <b><fmt:message key="album.detail.commentary"/>: </b><c:out value="${album.commentary}"/><br>
            
            <table class="tracklist">
                <tr>
                    <th>#</th>
                    <th><fmt:message key="album.detail.tracklist"/></th>
                </tr>
                <c:forEach items="${songs}" var="song">
                    <tr>
                        <td><c:out value="${song.positionInAlbum}"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/song/${song.id}"><c:out value="${song.title}"/></a>
                            <i>(<fmt:message key='album.detail.songBy'/> <a href="${pageContext.request.contextPath}/musician/${musician.id}"><c:out value="${musician.name}"/></a>)</i>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:attribute>
</my:layout>
