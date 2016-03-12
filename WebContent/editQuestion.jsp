<%@ page import="quiz.*"%>
<%@ page import="question.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Question</title>
</head>
<body>
<%
	Quiz q = (Quiz) request.getSession().getAttribute("newquiz");
	String type = request.getParameter("type");
	int index = Integer.parseInt(request.getParameter("questionIndex"));
	Question curr = q.getQuestionbyIndex(index);
	
	if(type.equals("response-question")){
		out.println("<h1>Edit Response Question</h1>");
		out.println(ResponseQuestion.returnHTMLEditTemplate(curr.getQuestion(), curr.getAnswer().getAnswerAt(0), index));
		
	} else if (type.equals("fib-question")){
		out.println("<h1>Edit Fill-in-the-Blank Question</h1>");
		FiBQuestion fib = (FiBQuestion) curr;
		out.println(FiBQuestion.returnHTMLEditTemplate(fib.getPreText(), fib.getPostText(), fib.getAnswer().getAnswerAt(0), index));
		
	} else if (type.equals("mc-question")){
		MCQuestion mc = (MCQuestion) curr;
		out.println("<h1>Edit Multiple Choice Question</h1>");
		out.println(MCQuestion.returnHTMLEditTemplate(mc.getQuestion(), mc.getAnswer().convertAnswerToString(), mc.getAdditional(), index));
		
	} else if (type.equals("mcma-question")){
		MCMAQuestion mcma = (MCMAQuestion) curr;
		out.println("<h1>Edit Multiple Choice Multiple Answer Question</h1>");
		out.println(MCMAQuestion.returnHTMLEditTemplate(mcma.getQuestion(), mcma.getAnswer().convertAnswerToString(), mcma.getAdditional(), index));
		
	} else if (type.equals("maresponse-question")){
		MAResponseQuestion marq = (MAResponseQuestion) curr;
		out.println("<h1>Edit Multiple Response Question</h1>");
		out.println(MAResponseQuestion.returnHTMLEditTemplate(marq.getQuestion(), marq.getAnswer().convertAnswerToString(), index));
		
	} else if (type.equals("matching-question")){
		MatchingQuestion mq = (MatchingQuestion) curr;
		out.println("<h1>Edit Matching Question</h1>");
		out.println(MatchingQuestion.returnHTMLEditTemplate(mq, index));
		
	} else if (type.equals("pic-response-question")){
		PictureResponseQuestion prq = (PictureResponseQuestion) curr;
		out.println("<h1>Edit Picture Response Question</h1>");
		out.println(PictureResponseQuestion.returnHTMLEditTemplate(prq.getQuestion(), prq.getAnswer().convertAnswerToString(), prq.getPicURL(), index));
		
	} else {
		RequestDispatcher dispatch = request.getRequestDispatcher("FailedQuestionIssue.jsp?problem=The question type was not recognized.");
		dispatch.forward(request, response);
	}

%>

</body>
</html>