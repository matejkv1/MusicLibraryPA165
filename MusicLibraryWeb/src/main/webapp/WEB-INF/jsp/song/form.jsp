<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table>
    <tr>
        <th><form:label path="title"><fmt:message key="song.title"/></form:label></th>
        <td><form:input path="title"/></td>
        <td><form:errors path="title" cssClass="error"/></td>
    </tr>
    <tr>
        <th><form:label path="commentary"><fmt:message key="song.commentary"/></form:label></th>
        <td><form:textarea path="commentary"/></td>
        <td><form:errors path="commentary" cssClass="error"/></td>
    </tr>
	<tr>
        <th><form:label path="positionInAlbum"><fmt:message key="song.positionInAlbum"/></form:label></th>
        <td><form:input path="positionInAlbum"/></td>
        <td><form:errors path="positionInAlbum" cssClass="error"/></td>
    </tr>
	<tr>
        <th><form:label path="bitrate"><fmt:message key="song.bitrate"/></form:label></th>
        <td><form:input path="bitrate"/></td>
        <td><form:errors path="bitrate" cssClass="error"/></td>
    </tr>
	<!--
	<tr>
        <th><form:label path="musician"><fmt:message key="song.musician.name"/></form:label></th>
        <td><form:select path="musician">
            <c:forEach items="${musicians}" var="musician">
                <form:option value="${musician.id}"><c:out value="${musician.name}"/></form:option>
            </c:forEach>
        </form:select></td>
        <td><form:errors path="musician" cssClass="error"/></td>
    </tr>
	<tr>
        <th><form:label path="genre"><fmt:message key="song.genre.name"/></form:label></th>
        <td><form:select path="genre">
            <c:forEach items="${genres}" var="genre">
                <form:option value="${genre.id}"><c:out value="${genre.name}"/></form:option>
            </c:forEach>
        </form:select></td>
        <td><form:errors path="genre" cssClass="error"/></td>
    </tr>
	<tr>
        <th><form:label path="album"><fmt:message key="song.album.name"/></form:label></th>
        <td><form:select path="album">
            <c:forEach items="${albums}" var="album">
                <form:option value="${album.id}"><c:out value="${album.title}"/></form:option>
            </c:forEach>
        </form:select></td>
        <td><form:errors path="album" cssClass="error"/></td>
    </tr>
	-->
</table>

