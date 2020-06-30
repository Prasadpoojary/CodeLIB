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
<meta name="theme-color" content="#012746">
<link href='<spring:url value="/resources/css/adminEdit.css"/>' rel="stylesheet" >
<title>Admin | Dashboard</title>
</head>
<body>
<section class="head">
    <h1>Spring Boot</h1>
    <h1>Prasad Poojary</h1>
</section>
<section class="view">
    <c:choose>
    <c:when test="${isUser }">
    <c:set value="${object}"  var="objects"/>
    <c:forEach items="${objects }" var="object">
   <form action="/edituser" method="put">
         <div class="fullname">
             <label for="uid">ID</label><br>
             <input type="text" value="${object.getId() }" name="id" id="uid" required>
         </div>
        <div class="fullname">
            <label for="name">Full name</label><br>
            <input type="text" value="${object.getName() }" name="name" id="name" required>
        </div>
        <div class="email">
            <label for="email">E-mail Address</label><br>
            <input type="email" value="${object.getUsername() }" name="username" id="email" required>
        </div>
        <div class="encPassword">
            <label for="encpass">Encrypted Password</label><br>
            <input type="text" value="${object.getPassword() }"  name="password" id="encpass" required>
        </div>
        <div class="password">
            <label for="pass">Password</label><br>
            <input type="text" value="${object.getfPass() }" name="fpass" id="pass" required>
        </div>
    <a href="delete/${object.getId() }"><button type="button" class="delete">Delete</button></a>
   </form> 
   </c:forEach>
   </c:when>
   <c:when test="${isBook }">
   <c:set value="${object}"  var="objects"/>
    <c:forEach items="${objects }" var="object">
   <form action="/editbook" method="POST">
    <div>
        <label for="bid">ID</label><br>
        <input type="text" value="${object.getId() }" name="id" id="bid" required>
    </div>
   <div>
       <label for="title">Book Title</label><br>
       <input type="text" value="${object.getTitle() }" name="title" id="title" required>
   </div>
   <div>
       <label for="auther">Auther</label><br>
       <input type="text" value="${object.getAuther() }" name="auther" id="auther" required>
   </div>
   <div>
       <label for="uid">Uploader ID</label><br>
       <input type="text" value="${object.getUid() }"  name="uid" id="uid" required>
   </div>
   <div>
       <label for="awslink">FileName</label><br>
       <input type="text" value="${object.getFilename() }" name="awsurl" id="awslink" required>
   </div>
     <div>
          <label for="cat">Category</label><br>
          <input type="text" value="${object.getCategory() }" name="cat" id="cat" required>
     </div>
     <div>
          <label for="date">Date</label><br>
          <input type="text" value="${object.getDate() }" name="date" id="date" required>
     </div>
     <button type="submit">Update</button><a href="books/delete/${object.getId() }">    <a href="delete/${object.getId() }"><button type="button" class="delete">Delete</button></a>

</form>
</c:forEach>
</c:when>
<c:otherwise>
<c:set value="${object}"  var="objects"/>
    <c:forEach items="${objects }" var="object">
<form action="/editcourse" method="POST">
     <div>
          <label for="cid">ID</label><br>
          <input type="text" value="${object.getId() }" name="id" id="cid" required>
     </div>
     <div>
           <label for="cname">Course name</label><br>
           <input type="text" value="${object.getTitle() }" name="title" id="cname" required>
     </div>
     <div>
         <label for="source">Source</label><br>
          <input type="text" value="${object.getSource() }" name="source" id="source" required>
     </div>
     <div>
         <label for="uid">Uploader ID</label><br>
          <input type="text" value="${object.getUid() }"  name="uid" id="uid" required>
     </div>
     <div>
          <label for="url">URL</label><br>
          <input type="url" value="${object.getUrl() }" name="url" id="url" required>
     </div>
     <div>
          <label for="cat">Category</label><br>
          <input type="text" value="${object.getCategory() }" name="cat" id="cat" required>
     </div>
     <div>
          <label for="date">Date</label><br>
          <input type="text" value="${object.getDate() }" name="date" id="date" required>
     </div>
     <button type="submit">Update</button><a href="courses/delete/${object.getId() }">    <a href="delete/${object.getId() }"><button type="button" class="delete">Delete</button></a>


 </form>
 </c:forEach>
 </c:otherwise>
 </c:choose>
</section>
</body>
</html>