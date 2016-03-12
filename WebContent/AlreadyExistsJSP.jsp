<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Already Exists</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">

</head>
<body>

<p><b> The Name <%= request.getParameter("name")%> Is Already In Use.</b></p>
<p> Please enter another name and password </p>
<form action="AccountCreateServlet" method="post">  
<p>Name: <input type="text" name="name" /></p>    

<p>Password: <input type="text" name="password"/>
<input type="submit" value = "Login" /></p>
</form>

</body>
</html>