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
	Performance p = (Performance)request.getAttribute("performance");
	int userid = Integer.parseInt(request.getParameter("userid"));
%>
<title>Results</title>
</head>
<body>
<p class = "score"> Score: <%=p.score%>% </p>
<p class = "time"> Time: <%=p.time%>ms<br></p>
<form action="RateQuiz.jsp" method="post">
<input type = "hidden" name="quiz_id" value =<%=p.quiz_id%>>
<input type = "hidden" name="userid" value = "<%=userid%>"> 
<input type = "submit" value = "Rate Quiz" class="button"/>
</form>
<a style="font-size:40px;color:white" href="QuizList.jsp">Quizzes List</a>
</body>
</html>