<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<%
	int quizid = Integer.parseInt(request.getParameter("quiz_id"));
	int userid = Integer.parseInt(request.getServletContext().getInitParameter("userid"));
%>
<title>Practice complete!</title>
</head>
<body>
<form action="TakePracticeQuiz" method="post">
<input type = "hidden" name = "quizid" value =<%=quizid%>>
<input type = "hidden" name = "userid" value =<%=userid%>>
<input type = "submit" value = "Take in Practice Mode again?" class="button"/>
</form>
<form action="TakeQuiz" method="post">
<input type = "hidden" name = "quizid" value =<%=quizid%>>
<input type = "hidden" name = "userid" value =<%=userid%>>
<input type = "submit" value = "Take Quiz in Record Mode" class="button"/>
</form>
<a style="font-size:40px;color:white" href="QuizList.jsp">Quizzes List</a>
</body>
</html>