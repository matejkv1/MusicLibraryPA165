<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="musician.edit.title"/>
<my:layout title="${title}">
 <jsp:attribute name="body">
<form:form method="post" action="${pageContext.request.contextPath}/musician/update" modelAttribute="musician">
    <form:hidden path="id"/>
    <fieldset><legend><fmt:message key="musician.edit.edit"/></legend>
        <%@include file="form.jsp"%>
        <input type="submit" value="<fmt:message key='musician.edit.save'/>">
    </fieldset>
</form:form>
</jsp:attribute>
</my:layout>