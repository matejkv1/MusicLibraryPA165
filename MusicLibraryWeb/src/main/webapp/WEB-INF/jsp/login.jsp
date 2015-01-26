
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:message var='title' key='login.title'/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1><fmt:message key="login.headline"/></h1>
        
        <c:if test="${not empty error}">
            <span class="error"><fmt:message key="login.error"/></span>
        </c:if>
        
        <div id="login-box">
            <c:url value="/login" var="loginUrl" />
            <form name='loginForm' action="${loginUrl}" method='POST'>
                <table>
                    <tr>
                        <td><fmt:message key="login.username"/>:</td>
                        <td><input type='text' name='username' value=''></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="login.password"/>:</td>
                        <td><input type='password' name='password' value=''></td>
                    </tr>
                    <tr>
                        <td colspan='2'>
                            <input name="submit" type="submit" value="<fmt:message key='login.submit'/>" />
                        </td>
                    </tr>
                </table>
                
            </form>
        </div>
        
        
    </jsp:attribute>
</my:layout>