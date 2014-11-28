<%-- 
    Document   : list
    Created on : Nov 25, 2014, 3:01:11 PM
    Author     : Matej Kvassay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message var="title" key="agenre.list.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        
        
        
        <p><fmt:message key="genre.list.all"/></p>
        
        <form method="get" action="${pageContext.request.contextPath}/genre/edit">
            <input type="submit" value="<fmt:message key='genre.add.new'/>">
        </form>
        
        <table class="basic">
            <tr>
                <th><fmt:message key="genre.list.table.headder"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${allGenres}" var="genre">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/genre/detail/${genre.id}"><c:out value="${genre.name}"/></a>
                    </td>
     
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/genre/update/${genre.id}">
                            <input type="submit" value="<fmt:message key='genre.edit.button'/>">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/genre/delete/${genre.id}">
                            <input type="submit" value="<fmt:message key='genre.delete.button'/>">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>        
    </jsp:attribute>
</my:layout>

