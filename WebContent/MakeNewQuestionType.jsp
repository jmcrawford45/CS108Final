<%@ page import="quiz.*"%>
<%@ page import="question.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<title>New Question</title>
</head>
<body>
<%
	Quiz q = (Quiz) request.getSession().getAttribute("newquiz");
	String type = request.getParameter("type");
	if(type.equals("response-question")){
		out.println("<h1>New Response Question</h1>");
		out.println(ResponseQuestion.returnHTMLBlankTemplate());
	} else if (type.equals("fib-question")){
		out.println("<h1>New Fill-in-the-Blank Question</h1>");
		out.println(FiBQuestion.returnHTMLBlankTemplate());
	} else if (type.equals("mc-question")){
		out.println("<h1>New Multiple Choice Question</h1>");
		out.println(MCQuestion.returnHTMLBlankTemplate());
		
	} else if (type.equals("mcma-question")){
		out.println("<h1>New Multiple Choice Multiple Answer Question</h1>");
		out.println(MCMAQuestion.returnHTMLBlankTemplate());
		
	} else if (type.equals("maresponse-question")){
		out.println("<h1>New Multiple Response Question</h1>");
		out.println(MAResponseQuestion.returnHTMLBlankTemplate());
		
	} else if (type.equals("matching-question")){
		out.println("<h1>New Matching Question</h1>");
		out.println(MatchingQuestion.returnHTMLBlankTemplate(Integer.parseInt(request.getParameter("numberquestions"))));
		
	} else if (type.equals("pic-response-question")){
		out.println("<h1>New Picture Response Question</h1>");
		out.println(PictureResponseQuestion.returnHTMLBlankTemplate());
		
	} else {
		out.println("<h1>Question type undefined.</h1>");
		//TODO: define what happens (go back to quiz overview page?)
		
	}
%>



</body>
</html>