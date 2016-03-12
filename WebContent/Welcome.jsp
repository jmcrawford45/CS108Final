<%@page import="java.sql.Connection"%>
<%@page import="tableabstraction.TableAbstraction"%>
<%@page import="java.util.ArrayList"%>  
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome To QuizWorld</title>
<link href = "j.png" rel="icon" type="image/gif">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
         
</head>    
<body>  
<%
java.sql.Connection con = (java.sql.Connection)request.getSession().getServletContext().getAttribute("connection");
tableabstraction.Stats stats = TableAbstraction.getStats(con);
%>

<h1> Announcements</h1>      
<%
ArrayList<String> announcements = stats.getMsgs();
for(int i = 0; i < announcements.size(); i++){
	String msg = announcements.get(i);%>
	<%=msg %><br><%
}   
%>

<h1> Popular Quizzes</h1>

 
<form action = "WelcomeScreen.jsp" method="post">
		<input type = "submit" value = "Login" class="button"/>      
</form>  
<form action = "CreateAccount.jsp" method="post">
		<input type = "submit" value = "Create Account" class="button"/>      
</form>    

</body>
</html>