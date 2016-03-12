<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href = "j.png" rel="icon" type="image/gif">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	String title = request.getParameter("title");
	String message = request.getParameter("message");
%>
<title><%=title %></title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
</head>
<body>
<h1><%= title %></h1>
<p><%= message %></p>

<form action = "CreateAccount.html" method="post">
<input type = "submit" value = "Try Again" class="button"/>

</form>

</body>
</html>