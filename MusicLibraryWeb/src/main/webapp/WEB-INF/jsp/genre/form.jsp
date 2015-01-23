<%-- 
    Document   : form
    Created on : Nov 25, 2014, 3:01:35 PM
    Author     : Matej Kvassay
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="form">
<table>
    <tr>
        <th><form:label path="name"><fmt:message key="genre.name"/></form:label></th>
        <td><form:input path="name"/></td>
        <td><form:errors path="name" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="description"><fmt:message key="genre.description"/></form:label></th>
        <td><form:textarea path="description"/></td>
        <td><form:errors path="description" cssClass="error"/></td>
    </tr>
</table>
</div>
