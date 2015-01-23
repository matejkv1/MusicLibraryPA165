<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message var="title" key="song.search.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">

        <p id='listTitle'><fmt:message key="search.result.for"/><c:out value=" '${q}'"/></p>

        <c:if test="${songs.isEmpty()}">
            <span><fmt:message key="song.search.none" /></span>
        </c:if>

        <c:if test="${!songs.isEmpty()}">
            <table class="basic">
                <tr>
                    <th><fmt:message key="song.list.table.headder"/></th>
                    <th><fmt:message key="song.list.table.musician"/></th>
                    <th><fmt:message key="song.list.table.album"/></th>
                    <th><fmt:message key="song.list.table.genre"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${songs}" var="song">
                    <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/song/${song.id}"><c:out value="${song.title}"/></a>
                        </td>

                        <td>
                            <a href="${pageContext.request.contextPath}/musician/${song.musician.id}"><c:out value="${song.musician.name}"/></a>
                        </td>

                        <td>
                            <a href="${pageContext.request.contextPath}/album/${song.album.id}"><c:out value="${song.album.title}"/></a>
                        </td>

                        <td>
                            <a href="${pageContext.request.contextPath}/genre/${song.genre.id}"><c:out value="${song.genre.name}"/></a>
                        </td>

                        <td>
                            <form method="get" action="${pageContext.request.contextPath}/song/update/${song.id}">
                                <input type="submit" value="<fmt:message key='song.edit.button'/>">
                            </form>
                        </td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/song/delete/${song.id}">
                                <input type="submit" value="<fmt:message key='song.delete.button'/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
                <hr>
    </jsp:attribute>
</my:layout>

