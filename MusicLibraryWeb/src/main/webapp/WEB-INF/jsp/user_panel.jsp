
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User panel</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Logged in as ${pageContext.request.userPrincipal.name}</p>
            <a href="${pageContext.request.contextPath}/logout" >Logout</a>
	</c:if>
        <sec:authorize access="hasRole('ADMIN')">
            <p>Congratz, you are authenticated as admin!</p>
        </sec:authorize>
        
    </body>
</html>
