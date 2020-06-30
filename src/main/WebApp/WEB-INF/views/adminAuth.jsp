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
<link href='<spring:url value="/resources/css/adminLog.css"/>' rel="stylesheet" >
<title>Admin | Login</title>
</head>
<body>
<div class="head">
   <h1>Welcome Prasad Poojary</h1>
</div>
<div class="cont">
   <form action="/dashboard" method="post">
         <div class="password">
             <input type="password" placeholder="Password" name="password" id="" required>
         </div>
         <button type="submit">Verify</button>
   </form>
</div>
</body>
</html>