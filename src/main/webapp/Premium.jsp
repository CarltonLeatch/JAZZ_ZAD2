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
Witaj na stronie premium
<a href = "Profile.jsp" >Powrót</a>
</body>
</html>