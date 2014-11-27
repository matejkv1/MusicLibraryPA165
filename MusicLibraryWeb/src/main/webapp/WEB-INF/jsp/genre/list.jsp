<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<my:layout title="${title}">
 <jsp:attribute name="body">

        <table class="basic">
            <tr>
                <th><fmt:message key="genre.allGenresTableHeadder"/></th>
            </tr>
            <c:forEach items="${genres}" var="genre">
                <tr>
                    <td>${genre.name}</td>

                </tr>
            </c:forEach>
        </table>
</jsp:attribute>
 </my:layout>