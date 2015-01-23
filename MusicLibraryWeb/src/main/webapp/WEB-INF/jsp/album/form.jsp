<%-- 
    Document   : form
    Created on : Nov 25, 2014, 3:01:35 PM
    Author     : Matej Bordac
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="form">
<table>
    <tr>
        <th><form:label path="title"><fmt:message key="album.title"/></form:label></th>
        <td><form:input path="title"/></td>
        <td><form:errors path="title" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="commentary"><fmt:message key="album.commentary"/></form:label></th>
        <td><form:textarea path="commentary"/></td>
        <td><form:errors path="commentary" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="dateOfRelease"><fmt:message key="album.releaseDate"/></form:label></th>
        <td>
            <fmt:formatDate value="${album.dateOfRelease}" var="dateString" pattern="dd/MM/yyyy" />
            <form:input path="dateOfRelease" value="${dateString}" id="datepicker" />
        </td>
        <td><form:errors path="dateOfRelease" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="albumArt"><fmt:message key="album.albumArt"/></form:label></th>
        <td><form:input path="albumArt"/></td>
        <td><form:errors path="albumArt" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="musician"><fmt:message key="album.musician.name"/></form:label></th>
        <td><form:select path="musician">
            <c:forEach items="${musicians}" var="musician">
                <form:option value="${musician.id}"><c:out value="${musician.name}"/></form:option>
            </c:forEach>
        </form:select></td>
        <td><form:errors path="musician" cssClass="error"/></td>
    </tr>
</table>
</div>
