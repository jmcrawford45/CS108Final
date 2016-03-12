<%@page import="tableabstraction.TableAbstraction"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">

<title>Add Friends</title>
</head>
<body>

<%
String to = request.getParameter("to");
if(to == null) to = "";
//User defUser = (User)request.getSession().getAttribute("user");//correct//
User defUser = TableAbstraction.getUser(request);
if(defUser == null){
	RequestDispatcher dispatch = 
			request.getRequestDispatcher("Register.html");
	dispatch.forward(request, response);
	return;
}

String from = defUser.getDisplayName();

%>

<form action="SendFriendRequest" method="post">  
<p>Add: <input type="text" name="toAdd" value = "<%=to%>"/></p>    
<%-- <input type = "hidden" name="fromUser" value = "<%= from%>">  
<p>Body:<br> <input type="text" name="message"/> --%>
<input type="submit" value = "Add Friend" />
</form>


<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>

</body>
</html>