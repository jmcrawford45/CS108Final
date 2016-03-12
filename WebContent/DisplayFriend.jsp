
 
 
 
 
 <%@page import="tableabstraction.TableAbstraction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Friend</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">

</head>
<body>

<%  
java.sql.Connection con = (java.sql.Connection)request.getSession().getServletContext().getAttribute("connection");
User user = (User)TableAbstraction.getUser(request.getParameter("user"), con);
User defUser = TableAbstraction.getUser(request);
if(defUser == null){
	RequestDispatcher dispatch = 
			request.getRequestDispatcher("Register.html");
	dispatch.forward(request, response);
	return;
}

String name = user.getDisplayName();  
String status = user.getStatus();
String bio = user.getBio();
String imageStr = user.getImage();
ArrayList<user.FriendEntry> quizzes = user.getRecent();
ArrayList<String> friends = user.getFriends(); 
String admin = (defUser.getAdminStatus()) ?  "" : "hidden = \"hidden\"";
%>
<img src= "<%=imageStr%>"/>
<p class = "name"> <%=name %> <br></p>

<form action = "RemoveFriend" method="post">  
		<input type = "hidden" name="toRemove" value = "<%=name %>">  
		<input type = "submit" value = "RemoveFriend" class="Button"/>  
</form>  
<form action = "SendChallenge.jsp" method="post">
		<input type = "hidden" name="toChallenge" value = "<%=name %>">  
		<input type = "submit" value = "Challenge" class="Button"/>  
</form>  

<form action = "ComposeTextMessage.jsp" method="post">
		<input type = "hidden" name="to" value = "<%=name %>">  
		<input type = "submit" value = "Send Message" class="Button"/>  
</form>  


<p class = "achievements"> Achievements <br>
<%
for(int i = 0; i < User.ACHIEVEMENTS.length; i++){
	if(defUser.hasAchieved(User.ACHIEVEMENTS[i])){
		%>
		<%=User.ACHIEVE_STRINGS[i] %><br>
		<% 
		
	}
}
  

%>
<p class = "status"> Status <br> <%=status %><br></p>
<p class = "bio"> About me  <br> <%=bio %> <br></p>
<% 
if(quizzes.size() != 0){
	%>  <p class = "quizH">Recent Activity<br></p>   <%
	for(user.FriendEntry e: quizzes){
		String u1 = e.getU1();
		String quiz = e.getQuizName();
		String id = "" + e.getQuizID();
		%>
		<%=u1 + e %><a href = "QuizSummary.jsp?quiz_id=<%=id%>"><%=quiz%></a><br> <%
	}
}
%>


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
<form action = "EditUser" method="post">
		<input type = "submit" <%= admin%> value = "Promote to Admin" class="button"/>  
		<input type = "hidden" name = "user" value = <%=name %> />
		<input type = "hidden" name = "type" value = "promote"/>    
</form> 
<form action = "EditUser" method="post">
		<input type = "submit" <%= admin%> value = "Remove User" class="button"/>      
		<input type = "hidden" name = "user" value = <%=name %> />
		<input type = "hidden" name = "type" value = "remove"/> 
</form> 

</body>
</html>

