<%@page import="quiz.*"%>
<%@page import="java.util.*"%>
<%@page import="tableabstraction.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">
<%
	QuizManager qm = (QuizManager)request.getServletContext().getAttribute("quizmanager");
	int user_id = tableabstraction.TableAbstraction.getUser(request).getID();
	Quiz q = null;
	if (request.getParameter("quiz_id") == null) {
		q = new Quiz(123, 223, 2, "test quiz 123", false, false, true, 0, "quiz123", true);
	} else {
		int id = Integer.parseInt(request.getParameter("quiz_id"));
		q = qm.getQuizByID(id);
	}
	user.User u = TableAbstraction.getUser(q.creator_id, qm.con);
	String u1 = (u==null) ? "null" : u.getDisplayName();
	ArrayList<Performance> userscores = qm.getUserQuizPerformances(q.id, user_id);
	ArrayList<Performance> bestscores = qm.getQuizBestPerformances(q.id);
	ArrayList<Performance> bestrecentscores = qm.getQuizBestRecentPerformances(q.id);
	ArrayList<Performance> recentscores = qm.getQuizRecentPerformances(q.id);
%>
<title><%=q.name%> Summary</title>
</head>
<body>
<h1> <%=q.name%> </h1>
<h2>Quiz Creator</h2>
<a href ="DisplayUser.jsp?user=<%=u1%>"><%=u1 %></a> <br>
<h2>Category </h2><p><%=QuizManager.CATEGORIES[1]%></p>  
<h2>Description</h2><p><%=q.description%></p><br>
<h2>Your Past Performances on this Quiz</h2>
<p class = "userquizscores"> 
<% 
for(int i = 0; i < userscores.size(); i++){   
	Performance p = userscores.get(i);
	Date d = new Date(p.start);
	%>
	You scored <%=p.score %>% with time <%=p.time %>ms on <%=d%><br>  
<%	
}
%>
</p>
<h2>Highest Performers of All Time</h2>
<p class = "bestscores">
<% 
for(int i = 0; i < bestscores.size(); i++){   
	Performance p = bestscores.get(i);
	Date d = new Date(p.start);
	user.User u2 = TableAbstraction.getUser(p.user_id, qm.con);
	String u2Name = (u==null) ? "null" : u.getDisplayName();
	%>
	<a href ="DisplayUser.jsp?user=<%=u2Name%>"><%=u2Name %></a> scored <%=p.score %>% with time <%=p.time %>ms on <%=d%><br>  
<%	
}
%>
</p>
<h2>Top Performers in the Last Day</h2>
<p class = "bestrecentscores">  
<% 
for(int i = 0; i < bestrecentscores.size(); i++){   
	Performance p = bestrecentscores.get(i);
	Date d = new Date(p.start);
	user.User u2 = TableAbstraction.getUser(p.user_id, qm.con);
	String u2Name = (u==null) ? "null" : u.getDisplayName();
	%>
	<a href ="DisplayUser.jsp?user=<%=u2Name%>"><%=u2Name %></a> scored <%=p.score %>% with time <%=p.time %>ms on <%=d%><br>   
<%	
}
%>
</p>
<h2>Recent Performances</h2>
<p class = "recentquizscores"> 
<% 
for(int i = 0; i < recentscores.size(); i++){   
	Performance p = recentscores.get(i);
	Date d = new Date(p.start);
	user.User u2 = TableAbstraction.getUser(p.user_id, qm.con);
	String u2Name = (u==null) ? "null" : u.getDisplayName();
	%>
	<a href ="DisplayUser.jsp?user=<%=u2Name%>"><%=u2Name %></a> scored <%=p.score %>% with time <%=p.time %>ms on <%=d%><br> 
<%	
	}
%>

<form action = "TakeQuiz" method="post">
<input type = "hidden" name="quizid" value = "<%=q.id%>">    
<input type = "hidden" name="userid" value = "<%=user_id%>"> 
<input type = "hidden" name="mode" value = "count"> 
<input type = "submit" value = "Take Quiz in Recorded Mode" class="button"/>
</form>
<%
	if(q.practice) {
%>
<form action = "TakePracticeQuiz" method="post">
<input type = "hidden" name="quizid" value = "<%=q.id%>">    
<input type = "hidden" name="userid" value = "<%=user_id%>">
<input type = "hidden" name="mode" value = "practice">  
<input type = "submit" value = "Take Quiz in Practice Mode" class="button"/>
</form>
<%
	}
%>
<% 
	if(q.creator_id == user_id)  {
%>
<form action = "EditQuiz.jsp" method="post">
<input type = "hidden" name="quizid" value = "<%=q.id%>">    
<input type = "hidden" name="userid" value = "<%=user_id%>"> 
<input type = "submit" value = "Edit Quiz" class="button"/>
</form>
<%
	}
%>
<form action="MarkInappropriate" method="post">
<input type = "hidden" name="quizid" value = "<%=q.id%>">    
<input type = "hidden" name="userid" value = "<%=user_id%>"> 
<input type = "submit" value = "Mark as Innappropriate" class="button"/>
</form>

<br>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>

</body>
</html>
