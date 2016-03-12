<%@page import="user.FriendEntry"%>
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
String status = user.getStatus();
String bio = user.getBio();
String imageStr = user.getImage();
ArrayList<FriendEntry> quizzes = user.getQuizzes();//changed
ArrayList<String> friends = user.getFriends(); 
%>
<img src= "<%=imageStr%>"/>
<p class = "name"> <%=name %> <br></p>


<p class = "achievements"> Achievements <br>
<%
for(int i = 0; i < User.ACHIEVEMENTS.length; i++){
	if(user.hasAchieved(User.ACHIEVEMENTS[i])){
		%>
		<%=User.ACHIEVE_STRINGS[i] %><br>
		<% 
		
	}
}


%>

<p class = "status"> Status <br> <%=status %><br></p>
<p class = "bio"> About me  <br> <%=bio %> <br></p>
<p class = "quizH"> Top Quizzes <br>  
<%-- <% 
for(int i = 0; i < quizzes.size(); i++){   
	//String quiz = quizzes.get(i);  
	%>
	<%= quiz %><br>  
<%	
}
%> --%>
</p>

<p class = "friends"> Friends <br>
<%
for(int i = 0; i < friends.size(); i++){
	String friend = friends.get(i);
	%>
	<a href="DisplayUser.jsp?user=<%=friend%>" class = "friends"> <%= friend%></a><br> 
<%
}
%>
</p>
<br>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>



</body>
</html>