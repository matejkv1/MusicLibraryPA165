<%-- 
    Document   : list
    Created on : Nov 25, 2014, 3:01:11 PM
    Author     : Matej Kvassay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:message var="title" key="genre.list.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        
        
        
        <p id='listTitle'><fmt:message key="genre.list.all"/></p>
         <sec:authorize access="hasAnyRole('ADMIN','USER')">
            <form method="get" action="${pageContext.request.contextPath}/genre/new">
                <input type="submit" value="<fmt:message key='genre.add.new'/>">
            </form>
         </sec:authorize>
        <table class="basic">
            <tr>
                <th><fmt:message key="genre.list.table.headder"/></th>
                <sec:authorize access="hasAnyRole('ADMIN','USER')">
                    <th></th>
                    <th></th>
                </sec:authorize>
            </tr>
            <c:forEach items="${allGenres}" var="genre">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/genre/${genre.id}"><c:out value="${genre.name}"/></a>
                    </td>
                    <sec:authorize access="hasAnyRole('ADMIN','USER')">
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
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>        
                <hr>
                <sec:authorize access="hasAnyRole('ADMIN','USER')">
                    <p><i><fmt:message key='genre.deletewarning'/></i></p>
                </sec:authorize>
    </jsp:attribute>
</my:layout>

