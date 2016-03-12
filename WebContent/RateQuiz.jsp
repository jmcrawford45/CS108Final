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
	int userid = Integer.parseInt(request.getParameter("userid"));
	int quizid = Integer.parseInt(request.getParameter("quiz_id"));
	//Quiz q = (Quiz)request.getSession().getAttribute("quiztaken");
	QuizManager qm = (QuizManager)request.getServletContext().getAttribute("quizmanager");
	ArrayList<Feedback> reviews = qm.getQuizFeedback(quizid);
%>
<title>Rate Quiz</title>
</head>
<body>
<div style="float:left; width:50%;">
<form action="SendFeedback" method="post">
<h2>Rating </h2>
 <table style="font-size:120%;">
     <tr>
     	<td align="left" width ="20%">Worst</td><td></td><td></td><td></td><td align="right" width ="20%">Best</td>
     	</tr>
     	<tr>
        <td align="center" width ="20%"><label>1</label><br><input type="radio" name="rating" value= "1"></td>
        <td align="center" width ="20%"><label>2</label><br><input type="radio" name="rating" value= "2"></td>
        <td align="center" width ="20%"><label>3</label><br><input type="radio" name="rating" value= "3"></td>
        <td align="center" width ="20%"><label>4</label><br><input type="radio" name="rating" value= "4"></td>
        <td align="center" width ="20%"><label>5</label><br><input type="radio" name="rating" value= "5"></td>      
    </tr>
 </table>
 <h2>Review </h2>
 <textarea name="review" rows="10" cols="50"></textarea><br>
 <input type = "hidden" name="userid" value = "<%=userid%>"> 
 <input type = "hidden" name="quizid" value = "<%=quizid%>"> 
 <input type = "submit" value = "Send Feedback and Explore Quizzes" class="button"/>
 </form>
 </div>
 <div style="float:right; width:50%;">
 <h2>Various User Reviews</h2>
	<%
		for (int i = 0; i < reviews.size(); i++){
			Feedback fb = reviews.get(i);
			Date d = new Date(fb.time);
			%>
			<div style="border:7px groove gray">
			<h3><font style="background-color:white;"><b><%=fb.user_id%></b> on <%=d%></font></h3> 
			 <p><font style="font-size:120%;"><%=fb.review%></font></p><br>
			<% 
				String rs = "";
				if(fb.rating > 0)
				for(int j= 0; j<fb.rating; j++) {					
					rs += " &#x2658;";					
				}
				%>
				<font style="font-size:200%;"><%=rs%></font><br>
				
				</div>
		<% 
		
		}
	%>
</div>
</body>
</html>