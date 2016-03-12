<%@page import="tableabstraction.TableAbstraction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Self</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">

</head>
<body>

<%  

java.sql.Connection con = (java.sql.Connection)request.getSession().getServletContext().getAttribute("connection");
User user = (User)TableAbstraction.getUser(request.getParameter("user"), con);

String name = user.getDisplayName();  
String bio = user.getBio();
String imageStr = user.getImage();
ArrayList<user.FriendEntry> quizzes = user.getQuizzes();
ArrayList<String> friends = user.getFriends(); 
%>
<img src= "<%=imageStr%>"/>
<p class = "name"> <%=name %> <br></p>

<form action = "SendFriendRequest" method="post">
		<input type = "hidden" name="toAdd" value = "<%=name %>">  
		<input type = "submit" value = "Add Friend" class="Button"/>  
</form>  
To View More Information, Add This User As A Friend!



<br>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>



</body>
</html>