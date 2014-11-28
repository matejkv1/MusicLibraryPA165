<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <table>
        <tr>
            <th><form:label path="name"><fmt:message key="musician.name"/></form:label></th>
            <td><form:input path="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <th><form:label path="biography"><fmt:message key="musician.biography"/></form:label></th>
            <td><form:textarea path="biography"/></td>
            <td><form:errors path="biography" cssClass="error"/></td>
        </tr>
    </table>
