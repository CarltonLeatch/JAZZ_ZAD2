<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if (request.getSession().getAttribute("login") != null) {
    response.sendRedirect("Profile.jsp");
    return;
} %>
<form method = "post" action = "Register">
<label>Login: <input type = "text" id ="login" name = "login" /></label><br/>
<label>Password: <input type = "text" id ="password" name = "password" /></label><br/>
<label>Confirm Password: <input type = "text" id ="cPassword" name = "cPassword" /></label><br/>
<label>E-mail: <input type = "email" id = "email" name = "email" /></label><br/>
<input type = "submit" value = "wyslij"/>
</form>
</body>
</html>