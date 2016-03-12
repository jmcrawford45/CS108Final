<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Announcements Here</title>
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<link href = "j.png" rel="icon" type="image/gif">

</head>
<body>
<h1>Share important news with users here!</h1>
<form action="Announce" method="post">  
<p>Body:<br> <input type="text" name="message" style="width:96%;height:90px;background-color:gold;color:olive;border:none;padding:2%;font:22px/30px sans-serif;"/>
<input type="submit" value = "Announce" class = Button /></p>
</form>

<br>
<form action = "AdminPage.jsp" method="post">
<input type = "submit" value = "Admin" class="button"/>
</form>
<form action = "HomePage.jsp" method="post">
<input type = "submit" value = "Home" class="button"/>
</form>
</body>
</html>