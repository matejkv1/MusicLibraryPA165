<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="musician.search.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">

        <p id='listTitle'><fmt:message key="search.result.for"/><c:out value=" '${q}'"/></p>
        
        <c:if test="${musicians.isEmpty()}">
            <span><fmt:message key="musician.search.none" /></span>
        </c:if>

        <c:if test="${!musicians.isEmpty()}">
            <table class="basic">
                <tr>
                    <th><fmt:message key="musician.name"/></th>
                    <th><fmt:message key="musician.biography"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${musicians}" var="musician">
                    <tr>
                        <td><my:a href="/musician/${musician.id}"><c:out value="${musician.name}"/></my:a></td>
                        <td><c:out value="${musician.biography}"/></td>

                        <td>
                            <form method="get" action="${pageContext.request.contextPath}/musician/update/${musician.id}">
                                <input type="submit" value="<fmt:message key='musician.list.edit'/>">
                            </form>
                        </td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/musician/delete/${musician.id}">
                                <input type="submit" value="<fmt:message key='musician.list.delete'/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
                <hr>
                <p><i><fmt:message key='musician.deletewarning'/></i></p>
<!--
        <form:form method="post" action="${pageContext.request.contextPath}/musician/update" modelAttribute="musician">
            <fieldset><legend><fmt:message key="musician.list.newmusician"/></legend>
                <%@include file="form.jsp"%>
                <input type="submit" value="<fmt:message key='musician.list.createmusician'/>">
            </fieldset>
        </form:form>
                -->
    </jsp:attribute>
</my:layout>