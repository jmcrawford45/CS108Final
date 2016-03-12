<%@page import="tableabstraction.TableAbstraction"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href = "j.png" rel="icon" type="image/gif">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Compose Text Message</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">

</head>
<body>  

<%
//User defUser = (User)request.getSession().getAttribute("user");//correct//
User defUser = TableAbstraction.getUser(request);
if(defUser == null){
	RequestDispatcher dispatch = 
			request.getRequestDispatcher("Register.html");
	dispatch.forward(request, response);
	return;
}

String from = defUser.getDisplayName();
String to;
if(request.getParameter("to") == null){
	to = "";
} else {
	to = request.getParameter("to");
}

%>

<form action="SendMessage" method="post">  
<p>To: <input type="text" name="toUser" value = "<%=to%>"style="width:44%;height:45px;background-color:gold;color:olive;border:none;padding:2%;font:12px/15px sans-serif;"/></p>    
<input type = "hidden" name="fromUser" value = "<%= from%>" style="width:96%;height:90px;background-color:gold;color:olive;border:none;padding:2%;font:22px/30px sans-serif;"/>  
<p>Body:<br> <input type="text" name="message"style="width:96%;height:90px;background-color:gold;color:olive;border:none;padding:2%;font:22px/30px sans-serif;"/><br>
<input type="submit" value = "Send Message" class = "Button"/></p>
</form>







<br>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>



</body>
</html>