<%-- 
    Document   : index
    Created on : Nov 25, 2014, 2:59:55 PM
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message var="title" key="index.title"/>
<my:layout title="${title}">
    <jsp:attribute name="body">
        <h1><a href="<c:url value='/'/>">Hello World!</a></h1>
    </jsp:attribute>
</my:layout>
