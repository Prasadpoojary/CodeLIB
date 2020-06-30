<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link href='<spring:url value="/resources/css/data.css"/>' rel="stylesheet" >
<meta name="theme-color" content="#012746">
<title>Admin | Dashboard</title>
</head>
<body>
    <section class="head">
        <h1>Spring Boot</h1>
        <h1>Prasad Poojary</h1>
    </section>
    <c:set value="${objects}" var="objects"/>
    <!-- --------------------------- -->
    <section class="view">
    	<c:choose>
    	<c:when test="${isUser}">
        <h2>${table }</h2>
        <ul>
        	<c:forEach items="${objects }" var="obj">
	            <li>
	                <a href="users/${obj.getId() }">${obj.getName() }</a>
	            </li>
            </c:forEach>
        </ul>
        </c:when>
        <c:otherwise>
        <h2>${table }</h2>
        <ul>
        	<c:forEach items="${objects }" var="obj">
	            <li>
	                <a href="${table}/${obj.getId() }">${obj.getTitle() }</a>
	            </li>
            </c:forEach>
        </ul>
        </c:otherwise>
        </c:choose>
    </section>
</body>
</html>