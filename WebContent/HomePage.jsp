




<%@page import="tableabstraction.TableAbstraction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<link href = "j.png" rel="icon" type="image/gif">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
</head>
<body>  


<%
User defUser = TableAbstraction.getUser(request);
if(defUser == null){
	RequestDispatcher dispatch = 
			request.getRequestDispatcher("Register.html");
	dispatch.forward(request, response);
	return;
}
java.sql.Connection con = (java.sql.Connection)request.getSession().getServletContext().getAttribute("connection");

String admin = (defUser.getAdminStatus()) ?  "" : "hidden = \"hidden\"";


tableabstraction.Stats stats = TableAbstraction.getStats(con);
%>
<h1>Welcome <%=defUser.getDisplayName() %></h1>
<h1> Announcements</h1>  
<%
ArrayList<String> announcements = stats.getMsgs();
for(int i = 0; i < announcements.size(); i++){
	String msg = announcements.get(i);%>
	<%=msg %><br> <%
} 
%>
<h1> Popular Quizzes</h1>

<h1> Recent Quizzes</h1>

 
<% 
if(defUser.getFriendLog().size() != 0){
	%>  <h1> Friend Activity</h1>   <%
	for(user.FriendEntry e: defUser.getFriendLog()){
			String u1 = e.getU1();  
			String u2 = e.getU2();
			String quiz = e.getQuizName();
			String id = "" + e.getQuizID();
			System.out.println(e.toString());
			if(!e.quizMessage()){
			%><a href ="DisplayUser.jsp?user=<%=u1%>"><%=u1 %></a>
			<%=e %><a href = "DisplayUser.jsp?user=<%=u2%>"><%=u2 %></a><br><%
			} else {
				%><a href ="DisplayUser.jsp?user=<%=u1%>"><%=u1%></a><%=e %>
				<a href = "QuizSummary.jsp?quiz_id=<%=id%>"><%=quiz%></a><br><%
			}
	}
}
%>
<% 
if(defUser.getRecent().size() != 0){
	%>  <h1>Recent Activity</h1>   <%
	ArrayList<user.FriendEntry> quizHistory = defUser.getRecent();
	for(user.FriendEntry e: quizHistory){
		String u1 = e.getU1();
		String quiz = e.getQuizName();
		String id = "" + e.getQuizID();
		%>
		<%=u1 + e %><a href = "QuizSummary.jsp?quiz_id=<%=id%>"><%=quiz%></a><br> <%
	}
}
%>



<div class = "buttons">
<form action = "DisplaySelf.jsp?user=<%=defUser.getDisplayName() %>" method="post">
		<input type = "submit" value = "My Profile" class="button"/>    
</form> 

<form action = "EditProfile.jsp" method="post">
		<input type = "submit" value = "Edit Profile" class="button"style="position: absolute; left: 250px; top: 50px;"/>    
</form> 
<form action = "DisplayInbox.jsp" method="post">
		<%
		if(defUser.messageCount() != 0){
			%> 		<input type = "submit" value = "Inbox" class="unreadMessage"style="position: absolute; left: 400px; top: 50px;"/>  
			    <%
		} else {
			%>   <input type = "submit" value = "Inbox" class="button"style="position: absolute; left: 400px; top: 50px;"/>   <%
		}

		%>
<!-- 		<input type = "submit" value = "Inbox" class="button"style="position: absolute; left: 400px; top: 50px;"/>      
 -->		<%if(defUser.fCount() != 0){
			%><i class="material-icons"style="position: absolute; left: 490px; top: 50px;">person_add</i><%
		}
		%>
		<%if(defUser.cCount() != 0){
			%><i class="material-icons"style="position: absolute; left: 510px; top: 50px;">whatshot</i><%
		}
		%>
		<%if(defUser.mCount() != 0){
			%><i class="material-icons"style="position: absolute; left: 470px; top: 50px;" >chat</i><%
		}  
		%>
</form>   
<form action = "ViewFriends.jsp" method="post">
		<input type = "submit" value = "Friends" class="button"style="position: absolute; left: 250px; top: 100px;"/>      
</form>  

<form action = "AddFriends.jsp" method="post">
		<input type = "submit" value = "Add Friends" class="button"style="position: absolute; left: 400px; top: 100px;"/>      
</form>  

<form action = "SearchProfiles.jsp" method="post">
		<input type = "submit" value = "Search" class="button"style="position: absolute; left: 250px; top: 150px;"/>      
</form>  
<form action = "QuizList.jsp" method="post">
		<input type = "submit" value = "Quizzes!" class="button"style="position: absolute; left: 400px; top: 150px;"/>      
</form>  
<form action = "AdminPage.jsp" method="post">
		<input type = "submit" <%= admin%> value = "Admin" class="button"/>      
</form> 
<form action = "Logout" method="post">
		<input type = "submit" value = "Logout" class="button"/>      
</form> 

<form action = "MyStats.jsp" method="post">
		<input type = "submit" value = "My Stats" class="button"/>      
</form> 

</div>


</body>
</html>