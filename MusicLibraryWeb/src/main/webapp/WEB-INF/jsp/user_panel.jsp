
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>

<fmt:message var="title" key="user.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1><fmt:message key="user.welcome"/> ${pageContext.request.userPrincipal.name}!</h1>
        
        
        <sec:authorize access="hasRole('ADMIN')">
            <!-- add users form -->
            <h2><fmt:message key="user.new.headline"/></h2>
            <div id="user_add">
                <form:form method="post" action="${pageContext.request.contextPath}/user/new" modelAttribute="user">
                    <form:hidden path="id"/>
                    <table>
                        <tr>
                            <th><form:label path="username"><fmt:message key="user.name"/></form:label></th>
                            <td><form:input path="username"/></td>
                            <td><form:errors path="username" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <th><form:label path="password"><fmt:message key="user.password"/></form:label></th>
                            <td><form:password path="password"/></td>
                            <td><form:errors path="password" cssClass="error"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <form:checkbox path="enabled"/>
                                <form:label path="enabled"><fmt:message key="user.enabled"/></form:label>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="checkbox" name="user_role" id="role"/>
                                <label for="role"><fmt:message key="user.isadmin"/></label>
                            </td>
                        </tr>
                    </table>
                    <fmt:message var="save" key='user.save'/>
                    <input type="submit" value="${save}">
                </form:form>
            </div>
            
        </sec:authorize>
            
            
    </jsp:attribute>
</my:layout>
