<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="theme-color" content="#012746">
<link href='<spring:url value="/resources/css/admin.css"/>' rel="stylesheet" >
<title>Admin | Dashboard</title>
</head>
<body>
<section class="head">
    <h1>Spring Boot</h1>
    <h1>Prasad Poojary</h1>
</section>
<section class="view">

    <h2>Database</h2>
    <ul>
        <li>
            <a href="/dashboard/users">Users</a>
        </li>
        <li>
            <a href="/dashboard/books">Books</a>
        </li>
        <li>
            <a href="/dashboard/courses">Courses</a>
        </li>
    </ul>
</section>
</body>
</html>