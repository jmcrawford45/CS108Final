<%@ page import="quiz.*"%>
<%@ page import="question.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">
<title>Question Deleted</title>
</head>
<body>

<h2>Question deleted!</h2>
<br>

Be sure to save quiz to publish changes to quiz. <br><br>

<form action = "CreateQuestions.jsp" method="post">    
<input type = "submit" value = "Go Back To Quiz Overview" class="button"/>
</form>
<br>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>

</body>
</html>
