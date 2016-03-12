<%@page import="tableabstraction.TableAbstraction"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href = "j.png" rel="icon" type="image/gif">

<title>Send Challenge </title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">

</head>
<body>
<%
String to = request.getParameter("toChallenge");
System.out.print("Challenge: " + to);
User defUser = TableAbstraction.getUser(request);
if(defUser == null){
	RequestDispatcher dispatch = 
			request.getRequestDispatcher("Register.html");
	dispatch.forward(request, response);
	return;
}


String from = defUser.getDisplayName();
%>

<form action="SendChallengeMessage" method="post">  
<p>Challenge Quiz:  <input type="text" name="link" /></p>   
<input type = "hidden" name="fromUser" value = "<%= from%>">    
<input type = "hidden" name="toUser" value = "<%= to%>">  
<input type="submit" value = "Challenge!" />
</form>


<%
	quiz.QuizManager qm = new QuizManager(DBConnection.connect());
	request.getSession().setAttribute("quizmanager", qm);
	ArrayList<Quiz> quizzes = qm.getAllQuizzes();
%>
</head>
<body>
<h1>Quizzes List</h1>
<p>Pick a quiz id from below!</p>
<% 
for(int i = 0; i < quizzes.size(); i++){   
	Quiz q = quizzes.get(i);
	user.User u = tableabstraction.TableAbstraction.getUser(q.creator_id, qm.con);
	String u1 = (u == null) ? "null" : u.getDisplayName();
	%>
	<p><%=q.name%>--<%=q.id%><br>
<%	
}
%>

<br>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>


</body>
</html>
