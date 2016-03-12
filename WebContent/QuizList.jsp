<%@page import="quiz.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<%
	QuizManager qm = new QuizManager(DBConnection.connect());
	request.getSession().setAttribute("quizmanager", qm);
	ArrayList<Quiz> quizzes = qm.getAllQuizzes();
%>
<title>Quiz Catalog</title>
</head>
<body>
<h1>Quizzes List</h1>
<% 
for(int i = 0; i < quizzes.size(); i++){   
	Quiz q = quizzes.get(i);
	Date d = new Date(q.time_created);
	user.User u = tableabstraction.TableAbstraction.getUser(q.creator_id, qm.con);
	String u1 = (u == null) ? "null" : u.getDisplayName();
	%>
	<a style="font-size:40px;color:white" href="QuizSummary.jsp?quiz_id=<%=q.id%>"><%=q.name%></a> created by <a href ="DisplayUser.jsp?user=<%=u1%>"><%=u1 %></a> on <%=d %><br>
<%	
}
%>
<form action="SetUpNewQuiz.jsp" method="post">
<input type = "submit" value = "Create a new Quiz" class="button"/>
</form>
</body>
</html>