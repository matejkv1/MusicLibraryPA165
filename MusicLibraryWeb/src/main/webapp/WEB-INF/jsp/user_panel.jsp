
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>

<my:layout title="${userpanel.title}">
    <jsp:attribute name="body">
        <h1>Hello World!</h1>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Logged in as ${pageContext.request.userPrincipal.name}</p>
            <a href="${pageContext.request.contextPath}/logout" >Logout</a>
	</c:if>
        <sec:authorize access="hasRole('ADMIN')">
            <p>Congratz, you are authenticated as admin!</p>
        </sec:authorize>
    </jsp:attribute>
</my:layout>
