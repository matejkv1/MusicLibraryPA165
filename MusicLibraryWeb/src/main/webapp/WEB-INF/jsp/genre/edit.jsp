<%-- 
    Document   : edit
    Created on : Nov 25, 2014, 3:00:40 PM
    Author     : Matej Kvassay
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="genre.edit.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/genre/update" modelAttribute="genre">
            <form:hidden path="id"/>
            <fieldset>
                <legend>
                    <c:if test="${empty genre.id}">
                        
                        <fmt:message key="genre.add.new"/>
                    </c:if>
                    <c:if test="${not empty genre.id}">
                        <fmt:message key="genre.edit.editgenre"/></legend>
                    </c:if>
                
                
                </legend>
                <%@include file="form.jsp"%>
                <input type="submit" value="<fmt:message key='genre.edit.save'/>">
            </fieldset>
        </form:form>
    </jsp:attribute>
</my:layout>