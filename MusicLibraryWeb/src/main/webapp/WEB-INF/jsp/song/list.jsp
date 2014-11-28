<%-- 
    Document   : list
    Created on : Nov 25, 2014, 3:01:11 PM
    Author     : Matej Kvassay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message var="title" key="song.list.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        
        <a href="${pageContext.request.contextPath}/song/edit"><fmt:message key="song.add.new"/></a>
        
        <p><fmt:message key="song.list.all"/></p>
        
        <table class="basic">
            <tr>
                <th><fmt:message key="song.list.table.headder"/></th>
				<th><fmt:message key="song.list.table.position"/></th>
				<th><fmt:message key="song.list.table.bitrate"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${songs}" var="song">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/song/detail/${song.id}"><c:out value="${song.title}"/></a>
                    </td>
					<td>
						${song.positionInAlbum}
					</td>
					<td>
						${song.bitrate}
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
    </jsp:attribute>
</my:layout>

