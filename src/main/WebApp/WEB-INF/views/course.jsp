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
<link href='<spring:url value="/resources/css/courses.css"/>' rel="stylesheet" >
<meta name="theme-color" content="#050136">
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-1/css/all.css">
<link rel="stylesheet" href='<spring:url value="/resources/css/fontawesome/css/all.css"/>'>
<script src='<spring:url value="/resources/js/jquery.js"/>'></script>
<script src='<spring:url value="/resources/js/courses.js"/>'></script>
<title>E-Books | courses</title>
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
            <p class="msgCont">${msg }</p>
            <button class="msgCloseBtn">OK</button>
        </div>
    </div>
    <section class="head">
        <div class="title">
            <h1>E-Courses Collection<i class="fa fa-feather-alt"></i></h1>
        </div>
        <div class="menuBar">
            <button class="menuToggle"><i class="fa fa-bars"></i></button>
        </div>
        <div class="nav">

            <ul>
            <button class="navCloseBtn"><i class="fa fa-times"></i></button>
            <a href="/">
                <li class="home">Home</li>
            </a>
            <a href="/courses">
                <li class="courses">Courses</li>
            </a>
            <a href="/upload">
                <li class="upload">Upload</li>
            </a>
            <c:set var="isAuthenticated" value="${isAuthenticated}"/>
		      <c:choose>
		      <c:when test="${isAuthenticated}">
		      <a href="/logout">
		          <li class="logout">Logout</li>
		      </a>
		      </c:when>
		      <c:otherwise>
		       <a href="/login">
		          <li class="logout">Login</li>
		      </a>
		      </c:otherwise>
		      </c:choose>
          
        </ul>
        </div>
       
    </section>
<c:set var="courses" value="${courses }"/>
<c:set var="categories" value="${categories }"/>
    <section class="filter">
        <div class="filterDiv">
            <input type="search" placeholder="Search Here" name="" id="search">
            <a href=""><button class="searchBtn"><i class="fa fa-search"></i></button cl></a>
        </div>
        <div class="category">
            <input type="search" value="" placeholder="Category" name="" list=""> 
            <datalist id="searchDataCont">
               <c:forEach items="${categories }" var="category">
                <option value="${category }"></option>
               </c:forEach>
            </datalist> 
        </div>
    </section>
    <section class="view">
    <c:forEach items="${courses }" var="course">
        <div class="courseCont">
            <div class="courseTitle">
                <h2>${course.getTitle() }</h2>
            </div>
            <div class="courseFrom">
                <p><span>- </span>${course.getSource() }</p>
            </div>
            <div class="courseUploader">
                <p><span>Uploaded By </span>${course.getUploader() }</p>
            </div>
            <div class="courseButton">
                <a href="${course.getUrl() }" target="_blank">View <i class="fa fa-feather-alt"></i></a>
            </div>
        </div>
	</c:forEach>
    </section>

</body>
</html>