<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "j.png" rel="icon" type="image/gif">

<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<title>Search Quizzes</title>
</head>
<body>
<h1>Find quiz by id or view all</h1>

<form action="QuizSummary.jsp" method="post">  
<p>Search: <input type="text" name="user"/></p>    
<input type="submit" value = "Find Quiz" />
</form>
<form action = "ViewQuizzes.jsp" method="post">
		<input type = "submit" value = "View All" class="button"/>      
</form>  

<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>

</body>
</html>