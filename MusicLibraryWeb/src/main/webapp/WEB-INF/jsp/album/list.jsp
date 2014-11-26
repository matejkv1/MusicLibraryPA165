<%-- 
    Document   : list
    Created on : Nov 25, 2014, 3:01:11 PM
    Author     : Matej Bordáč
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message var="title" key="album.list.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        
        <p><fmt:message key="album.list.all"/></p>
        
        <table class="basic">
            <tr>
                <!--album art thumbnail here-->
                <th><fmt:message key="album.title"/></th>
                <th><fmt:message key="album.musician.name"/></th>
                <th><fmt:message key="album.releaseDate"/></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <td>Meteora</td>
                <td>Linkin Park</td>
                <td>2003</td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/book/update/${book.id}">
                        <input type="submit" value="<fmt:message key='album.edit.button'/>">
                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/book/delete/${book.id}">
                        <input type="submit" value="<fmt:message key='album.delete.button'/>">
                    </form>
                </td>
            </tr>
            <tr>
                <td>マジコカタストロフィ</td>
                <td>ShibayanRecords</td>
                <td>2012</td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/book/update/${book.id}">
                        <input type="submit" value="<fmt:message key='album.edit.button'/>">
                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/book/delete/${book.id}">
                        <input type="submit" value="<fmt:message key='album.delete.button'/>">
                    </form>
                </td>
            </tr>
        </table>
        
    </jsp:attribute>
</my:layout>