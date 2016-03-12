<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

<p><b>Quiz World </b></p>
<p> Please log in. </p>
<form action="LoginServlet" method="post">    
<p>Name: <input type="text" name="name" /></p>    
<p>Password: <input type="password" name="password"/>  
<input type="submit" value = "Login" /></p>
</form>
<!-- <a href="CreateAccount.html">Create Account</a>
 -->

<form action = "CreateAccount.jsp" method="post">
		<input type = "submit" value = "Create Account" class="button"/>      
</form>  
<form action = "Welcome.jsp" method="post">
		<input type = "submit" value = "Site Home" class="button"/>      
</form>  

</body>
</html>