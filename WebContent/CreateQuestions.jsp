<%@ page import="quiz.*"%>
<%@ page import="question.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<%
	
	Quiz q = (Quiz)request.getSession().getAttribute("newquiz");
%>
<title>CurrentQuestions</title>
</head>
<body>
<h1>Current Questions in Quiz</h1>

<%
	if(q.questions.size() == 0) out.println("No questions currently in quiz.");
	for(int i = 0; i < q.getNumQuestions(); i++){
		Question curr = q.getQuestionbyIndex(i);
		out.println(curr.returnHTMLDisplayStatic());
		out.println("<br>\r");
		out.println("<form action=\"editQuestion.jsp\" method=\"post\">");
		out.println("<input name=\"questionIndex\" type=\"hidden\" value=\"" + i + "\"/>");
		out.println("<input name=\"type\" type=\"hidden\" value=\"" + curr.getType() + "\"/>");
		out.println("<input type=\"submit\" value=\"Edit\"/>");
		out.println("</form>");
		out.println("<form action=\"deleteQuestionServlet\" method=\"post\">");
		out.println("<input name=\"questionIndex\" type=\"hidden\" value=\"" + i + "\"/>");
		out.println("<input name=\"type\" type=\"hidden\" value=\"" + curr.getType() + "\"/>");
		out.println("<input type=\"submit\" value=\"Delete\"/>");
		out.println("</form>");
	}

%>


<h1>To create a new question, select its type:</h1>
<form action="MakeNewQuestionType.jsp" action="post">
<input type="radio" value="response-question" name="type" />Response Question<br>
<input type="radio" value="fib-question" name="type" />Fill-in-the-Blank Question<br>
<input type="radio" value="mc-question" name="type" />Multiple Choice Question<br>
<input type="radio" value="mamc-question" name="type" />Multiple Answer Multiple Choice Question<br>
<input type="radio" value="maresponse-question" name="type" />Multiple Answer Multiple Response Question<br>
<input type="radio" value="matching-question" name="type" />Matching Question
(Number of questions: <input type="text" name="numberquestions" />) <br>
<input type="radio" value="picresponse-question" name="type" />Picture-Response Question<br><br>

<input type="submit" value="Submit"/>
</form>
<form action="AddQuiz" method="post">

<input type="submit" value="Add Quiz" class="button"/>
</form>

</body>
</html>