<%@page import="quiz.*"%>
<%@page import="question.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<%
	Quiz quiz = (Quiz)request.getSession().getAttribute("pquiz");
	ArrayList<Question> pqs = (ArrayList<Question>)request.getSession().getAttribute("pquestions");
	//HashMap<Question, Integer> pr = (HashMap<Question, Integer>)request.getSession().getAttribute("precord");
	
%>
<title><%=quiz.name%>: Practice Mode</title>
</head>

<body>
<form action="CheckPracticeSP" method="post">
<%
	for (int i = 0; i < pqs.size(); i++) {
		String display = "<b>" + (i+1) + ". </b>" + pqs.get(i).returnHTMLQuestion(i) + "<br>";
		out.print(display);
	}
%>
<br>
<input type = "submit" value = "Submit" class="button"/>
</form>
</body>
</html>