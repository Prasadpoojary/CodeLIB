<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="theme-color" content="#ffffff">
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-1/css/all.css">
    <link href='<spring:url value="/resources/css/forgetStyle.css"/>' rel="stylesheet" >
    <title>Forget Password</title>
</head>
<body>
    </div>
    <div class="container">
    	<p class="error">${error }</p>
        <form action="getpassword"  method="POST">
        <p class="para">Your will get Your Password through E-mail</p>
        <div class="inp">
            <input type="email" name="email" id="" required placeholder="Your E-mail Address">
        </div>
        <button type="submit"><i class="fas fa-paper-plane"></i></button>
        </form>
    </div>
</body>
</html>