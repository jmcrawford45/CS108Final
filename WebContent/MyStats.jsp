<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your History</title>
</head>
<body>
<% 
user.User defUser = tableabstraction.TableAbstraction.getUser(request);
if(defUser == null){
	RequestDispatcher dispatch = 
			request.getRequestDispatcher("Register.html");
	dispatch.forward(request, response);
	return;
}
if(defUser.getQuizzes().size() != 0){
	%>  <h1>Recent Activity</h1>   <%
	java.util.ArrayList<user.FriendEntry> quizHistory = defUser.getQuizzes();
	for(user.FriendEntry e: quizHistory){
		String u1 = e.getU1();
		String quiz = e.getQuizName();
		String id = "" + e.getQuizID();
		%>
		<%=u1 + e %><a href = "QuizSummary.jsp?quiz_id=<%=id%>"><%=quiz%></a><br> <%
	} 
}
%>
</body>
</html>