<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Message</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">

</head>
<body>
<%
String body = request.getParameter("body");
String from = request.getParameter("from");
String index = request.getParameter("index");
%>
<%= body %><br><br>


<form action="DeleteMessage" method="post"> 
<input type="hidden" name = "index" value = "<%=index %>">    
<input type="submit" value = "Delete Message" />
</form>
<a href="ComposeTextMessage.jsp?to=<%=from%>"> Reply</a>

<br>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>

</body>
</html>