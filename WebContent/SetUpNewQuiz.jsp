<%@page import="quiz.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="StyleSheet" TYPE="text/css" HREF="Style.css">
<title>Create a Quiz</title>
</head>
<body>
<h1>Create a Quiz</h1>

<form action= "FormNewQuiz" method="post">
<h2>Name</h2><input type="text" name="name" value=""style="width:100px; height:10px;"><br>
<h2>Description</h2><input type="text" name="description" value=""style="width:500px; height:10px;"><br>
<h2>Category (Choose One)</h2>
<%
	for (int i = 0; i < QuizManager.CATEGORIES.length; i ++){
		String cat = "<input type='radio' name='category_id' value=" + i + ">"
				+ QuizManager.CATEGORIES[i] + "<br>";
		out.print(cat);
	}
%>
<h2>Options</h2>
<input type="checkbox" name="random" value="random">Randomize question order<br><br>
<input type="checkbox" name="pages" value="multiple">Display questions on multiple pages<br>
<input type="checkbox" name="immediate" value="immediate">If questions are displayed on multiple pages, give answer feedback immediately <br><br>
<input type="checkbox" name="practice" value="practice">Allow quiz to be taken in practice mode<br><br>
<input type = "submit" value = "Create Questions" class="button"/>
</form>
</body>
</html>