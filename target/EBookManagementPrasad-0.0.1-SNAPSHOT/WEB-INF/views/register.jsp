<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="theme-color" content="#000000">
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-1/css/all.css">
<link href='<spring:url value="/resources/css/register.css"/>' rel="stylesheet" >
<script src='<spring:url value="/resources/js/jquery.js"/>'></script>
<script src='<spring:url value="/resources/js/register.js"/>'></script>
<title>CodeLIB | Register</title>
</head>
<body>
    <div class="loading">
        <h1 class="lhead">Loading</h1>
        <div class="lshow">
            <i class="fa fa-desktop"></i>
            <div class="ldata"><span>0</span><span>1</span><span>1</span><span>0</span><span>0</span><span>1</span><span>0</span><span>1</span><span>1</span><span>1</span><span>0</span><span>1</span><span>0</span><span>0</span><span>1</span><span>1</span><span>0</span></div>
            <i class="fa fa-server"></i>
        </div>
    </div>
    <div class="msgBase">
        <div class="msg">
            <p class="msgCont">${msg}</p>
            <button class="msgCloseBtn">OK</button>
        </div>
    </div>
   
    <section>
        <div class="container">
            <div class="heading">
                <h2>Wel<span>come</span></h2>
                <p><a href="login" class="login">Login</a><a href="register" class="register">Register</a></p>
            </div>
            <form action="register-user" class="registerForm" method="post">
            <div class="inpDiv email">
                <input type="text" name="name" id="username" required>
                <label class="unameLab">Full name</label>
            </div>
            <div class="inpDiv name">
                <input type="email" name="email" id="username" required>
                <label class="unameLab">E-mail Address</label>
            </div>
            <div class="inpDiv">
                <input type="password" name="password1" id="password1" required>
                <label class="pword1Lab">Password</label>
                <button type="button" class="logo"><i class="fa fa-eye"></i></button>
                <button type="button" class="logo offLogo"><i class="fa fa-eye-slash"></i></button>
            </div>
            <div class="inpDiv">
                <input type="password" name="password2" id="password1" required>
                <label class="pword1Lab">Confirm Password</label>
                <button type="button" class="logo"><i class="fa fa-eye"></i></button>
                <button type="button" class="logo offLogo"><i class="fa fa-eye-slash"></i></button>
            </div>
            <div class="submitDiv">
                <button type="submit">Register</button>
            </div>
        </form>
        </div>
    </section>

</body>
</html>