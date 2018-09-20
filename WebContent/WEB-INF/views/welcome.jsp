<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Welcome</title>
</head>

<body>
	<%@ include file="header.jsp"%>

	<h2>Welcome to Golf Club Assistant</h2>
	<form action="login" method="get">
		<input name="login" type="submit" value="Login" /><br>
	</form>
	<hr>
	<p>Let us help you administer your golf club</p>
	<br>
	<form action="enrollClub" method="get">
		<input name="enrollClub" type="submit" value="Enroll Club" /><br>
	</form>

	<%@ include file="footer.jsp"%>
</body>
</html>