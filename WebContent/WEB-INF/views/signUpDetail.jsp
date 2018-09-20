<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ page import="springwork.model.SignUpBean" %>
    <%@ page import="models.GCA_Tournament" %>
    <%@ page import="models.GCA_SignUp" %>
    <%@ page import="commonFunctions.CommonFunctions" %>
    <%@ page import="java.util.Map" %>
    <%@ page import="java.util.Map.Entry" %>
    <%@ page import="java.util.TreeMap" %>
    <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>SignUp Detail</title>
	<style><%@include file="../views/DropDown.css"%></style>	
</head>

<body>
<%
	String signedUpFlag = "False";
	if (request.getAttribute("signedUpFlag") != null) {
		signedUpFlag = (String) request.getAttribute("signedUpFlag");
	}
	GCA_SignUp signUp = new GCA_SignUp();
	if (request.getAttribute("signUp") != null) {
		signUp = (GCA_SignUp) request.getAttribute("signUp");
	}
	Date tournamentDate = null;
	if (request.getAttribute("tournamentDate") != null) {
		tournamentDate = (Date) request.getAttribute("tournamentDate");
	}
	String tournamentName = "";
	if (request.getAttribute("tournamentName") != null) {
		tournamentName = (String) request.getAttribute("tournamentName");
	}
%>

	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Sign Up Detail</h2>
	
	<form:form action="signUpDetail" method="POST" modelAttribute="signUp">
		Member<input type="text" readonly name="memberName" value="${memberName}"/><br>

		Date<input readonly name="tournamentDate" 
			value="<fmt:formatDate value='${tournamentDate}' pattern='yyyy-MM-dd'/>"
			/><br>

		Tournament<input type="text" name="tournamentName" readonly value="${tournamentName}"/><br>
		<form:radiobutton path="signedUpFlag" name="signedUpFlag" value="True" label="Playing"/>
		<form:radiobutton path="signedUpFlag" name="signedUpFlag" value="False" label="Not Playing"/><br>
		<form:hidden path="clubId" name="clubId" /><br>
		<form:hidden path="memberId" name="memberId" /><br>
		<form:hidden path="signUpDate" name="signUpDate" /><br>
		<form:hidden path="tournamentId" name="tournamentId" /><br>
		<form:hidden path="signUpId" name="signUpId" /><br>

		<input type="submit" value="Update"/><br>
	</form:form>

	<%@ include file="footer.jsp"%>
</body>

</html>