<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="theme-color" content="#050136">
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-1/css/all.css">
<link href='<spring:url value="/resources/css/upload.css"/>' rel="stylesheet" >
<link rel="stylesheet" href='<spring:url value="/resources/css/fontawesome/css/all.css"/>'>
<script src='<spring:url value="/resources/js/jquery.js"/>'></script>
<script src='<spring:url value="/resources/js/upload.js"/>'></script>
<title>E-Books | upload</title>
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
        <h1>E-Books Collection<i class="fa fa-feather-alt"></i></h1>
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
            <a href="courses">
                <li class="courses">Courses</li>
            </a>
            <a href="upload">
                <li class="upload">Upload</li>
            </a>
		    <a href="logout">
		        <li class="logout">Logout</li>
		    </a>
		    
          
        </ul>
    </div>
   
</section>
</section>
<section class="view">
    <div class="userUploads">
        <h1 class="uutitle"><span>my</span> uploads</h1>
     <c:set var="upBooks" value="${uploadBooks}"/>
     <c:set var="upCourses" value="${uploadCourses}"/>
     <c:set var="category" value="${category}"/>
     <c:choose>
     <c:when test="${upBooks.size() == 0 && upCourses.size() == 0}">
    <div class="emptyCont">
        <div class="eiCross">
            <p>+</p>
        </div>
        <p class="eiMain">No uploads Found</p>
        <p class="eiSub">You can upload some E-Books and Free Online courses Google Drive URL or any other sources URL</p>
    </div>
    </c:when>
    <c:otherwise>
    <c:forEach items="${upBooks}" var="elementb">
        <a href="book/view/${elementb.getId()}">
            <div class="uu">
                 <p class="uubook">${elementb.getTitle()}</p>
                 <p class="uumeta">
                    <span class="uucategory">e-Book</span>
                    <a class="uudate">${elementb.getDate() }</a>
                    <a class = "deleteUploadBtn" href="/delete/book/${elementb.getId()}">Delete</a>
                </p>      
            </div>
        </a>
     </c:forEach>
     <c:forEach items="${upCourses}" var="elementc">
        <a href="course/view/${elementc.getId()}">
            <div class="uu">
                 <p class="uubook">${elementc.getTitle()}</p>
                 <p class="uumeta">
                    <span class="uucategory">Courses</span>
                    <a class="uudate">${elementc.getDate() }</a>
                    <a class = "deleteUploadBtn" href="/delete/course/${elementc.getId()}">Delete</a>
                </p>      
            </div>
        </a> 
         </c:forEach>
     </c:otherwise>
     </c:choose>
    </div>
    <div class="uploadForm">
        <h1><span>Up</span>load</h1>
        <div class="formCont">
            <div class="uploadBtnDiv">
                <button class="ebookBtn">E-BOOK</button>
                <button class="courseBtn">COURSE</button>
            </div>
            <form action="/upload-book" method="post" enctype="multipart/form-data">
            <div class="upBookForm">
                <div class="inpDiv">
                    <input type="text" name="title" required>
                    <label for="">Title of the Book</label>
                </div>
                <div class="inpDiv">
                    <input type="text" name="auther" required>
                    <label for="">Auther </label>
                </div>
                <div class="inpDiv">
                    <input type="search" value="" id="formCtgry" name="category" list="" required> 
                    <datalist id="formCategory">
                    <c:forEach items="${category }" var="categ">
                        <option value="${categ }"></option>
                    </c:forEach>
                    </datalist> 
                    <label for="">Category</label>
                </div>
                <div class="inpfile" >
                    <input type="file" class="file" name="file" accept=".pdf,.txt,.doc,.docx,.epub,.mobi,.azw,.azw3,.zip,.rar"  required>
                    <button type="button" class="select">Select File <i class="fa fa-file-alt"></i></button>
                </div>
                <button type="submit" class="upBookFormSubmit">Upload <i class="fa fa-cloud-upload-alt"></i></button>
            </div>
            </form> 
        </div>

        <form method="POST" action="/upload-course">
        <div class="upCourseForm">
            <div class="inpDiv">
                <input type="text" name="title" required>
                <label for="">Name of the Course</label>
            </div>
             <div class="inpDiv">
                <input type="text" name="source" required>
                <label for="">Source (eg: Udemy, Google Drive)</label>
            </div>
            <div class="inpDiv">
                <input type="url" name="url" required>
                <label for="">URL of the Course</label>
            </div>
            <div class="inpDiv">
                <input type="search" value="" id="formCourseCtgry" name="CourseCategory" list="" required> 
                <datalist id="formCourseCategory">
                    <c:forEach items="${category }" var="categ">
                        <option value="${categ }"></option>
                    </c:forEach>
                </datalist> 
                <label for="">Select Category</label>
            </div>
            <button type="submit" class="upBookFormSubmit">Upload <i class="fa fa-cloud-upload-alt"></i></button>
        </div>
        </form>
    </div>
    </div>
</section>


</body>
</html>