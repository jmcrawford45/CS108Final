<%@page import="quiz.*"%>
<%@page import="question.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<%
	Quiz quiz = (Quiz)request.getSession().getAttribute("quiztaken");
	int i = Integer.parseInt(request.getParameter("index"));
	//String correct = request.getParameter("correct");

	Question q = quiz.getQuestionbyIndex(i);

%>
<title>Question <%=i+1%></title>
</head>
<body>
<%
	String s = q.returnHTMLSingleQuestion();
	out.print(s);
%>
</body>
</html>