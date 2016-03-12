<%@page import="tableabstraction.TableAbstraction"%>
<%@page import="messages.ChallengeMessage"%>
<%@page import="messages.Message"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Inbox</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">
       
</head>
<body>

My Inbox     

<% 
		User defUser = TableAbstraction.getUser(request);
		if(defUser == null){
			RequestDispatcher dispatch = 
				request.getRequestDispatcher("Register.html");
			dispatch.forward(request, response);
			return;
		}
		java.sql.Connection con = (java.sql.Connection)request.getSession().getServletContext().getAttribute("connection");


		java.util.ArrayList<messages.Message> inbox = defUser.getMessages();
		for(int i = 0; i < inbox.size(); i++){
			
			Message msg = inbox.get(i);    
			String type = msg.getType();
			String link = "";
			String toShow = msg.getFrom() + " : " + type;
			
			if(type == "ChallengeMessage"){
				ChallengeMessage msgCh = (ChallengeMessage)msg;
				link = msgCh.getLink();    
			}
			%>
			<form action = "DistinguishMessages" method="post">
				<input type = "hidden" name= "index" value = "<%=i %>"/>
				<input type = "hidden" name="type" value = "<%=type%>">
				<input type = "hidden" name="link" value = "<%=link%>">
				<input type = "hidden" name = "from" value = "<%=msg.getFrom() %>"/>
				<input type = "hidden" name = "string" value = "<%=msg.toString() %>"/> 
				<input type = "submit" value = "<%=toShow  %>" class = "linkB"/>
			</form>
	
			
		<%}%>
		<% 
		
%>
<a href= "ComposeTextMessage.jsp"> Compose New Message</a>    

<br>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>



</body>
</html>