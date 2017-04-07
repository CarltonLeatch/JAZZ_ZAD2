<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if (request.getSession().getAttribute("login") == null) {
    response.sendRedirect("index.jsp");
    return;
} %>
<form method = "POST" action = "showAll">
<input type="submit" value="ShowAll"/></form>
<form method = "POST" action = "Profile">
<input type="submit" value="wyswietl profil"/></form>
<form method = "POST" action = "Logout">
<input type="submit" value="Wyloguj"/></form>
<form method = "POST" action = "Premium">
<input type="submit" value="Strona Premium"/></form>
</body>
</html>