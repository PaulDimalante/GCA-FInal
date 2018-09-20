<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ page import="springwork.model.SignUpBean" %>
    <%@ page import="models.GCA_Tournament" %>
    <%@ page import="models.GCA_SignUp" %>
    <%@ page import="java.util.Map" %>
    <%@ page import="java.util.Map.Entry" %>
    <%@ page import="java.util.TreeMap" %>
    <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>SignUp</title>
	<style><%@include file="../views/DropDown.css"%></style>	
</head>

<body>
<%
	SignUpBean signUpBean = new SignUpBean();
	Map<Date,GCA_Tournament> tournaments = new TreeMap<Date,GCA_Tournament>();
	Map<Integer,Map<Integer,GCA_SignUp>> signUps = new TreeMap<Integer,Map<Integer,GCA_SignUp>>();
	if (request.getAttribute("signUpBean") != null) {
		signUpBean = (SignUpBean) request.getAttribute("signUpBean");
		tournaments = signUpBean.getTournaments();
		signUps = signUpBean.getSignUps();
	}
	int clubId = 0;
	int memberId = 0;
	Date currentDate = new Date();
	if (session.getAttribute("memberId") != null) {
		memberId = (Integer) session.getAttribute("memberId");
	}
	if (session.getAttribute("clubId") != null) {
		clubId = (Integer) session.getAttribute("clubId");
	}
%>

	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Sign Up</h2>
	
	<table border="1">
		<tr>
			<th>Date</th>
			<th>Tournament</th>
			<th>Signed up</th>
		</tr>
		<%for (Entry<Date,GCA_Tournament> t : tournaments.entrySet()) {
			Date tournamentDate = t.getKey();
			GCA_Tournament tournament = t.getValue();
			int tournamentId = tournament.getTournamentId();
			String tournamentName = tournament.getTournamentName();
			GCA_SignUp signUp = new GCA_SignUp();

			if (signUps.containsKey(tournamentId)) {
				for (Entry<Integer,GCA_SignUp> s : signUps.get(tournamentId).entrySet()) {
					signUp = s.getValue();
					break;
				}
			}
			
			int signUpId = signUp.getSignUpId();
			String signedUpFlag = signUp.getSignedUpFlag();
			Date signUpDate = signUp.getSignUpDate();%>

			<tr>
				<form:form action="signUpDetail" method="GET" modelAttribute="signUp">
					<td><input name="tournamentDate" type="date" value="<%=tournamentDate%>"></td>
					<td><input name="tournamentName" value="<%=tournamentName%>"></td>
					<td><form:input readonly="true" path="signedUpFlag" name="signedUpFlag" value="<%=signedUpFlag%>"/></td>
					<td hidden="hidden"><form:input path="clubId" name="clubId" value="<%=clubId%>"/></td>
					<td hidden="hidden"><form:input path="memberId" name="memberId" value="<%=memberId%>"/></td>
					<td hidden="hidden"><form:input path="signUpDate" name="signUpDate" value="<%=signUpDate%>" type="date"/></td>
					<td hidden="hidden"><form:input path="tournamentId" name="tournamentId" value="<%=tournamentId%>"/></td>
					<td hidden="hidden"><form:input path="signUpId" name="signUpId" value="<%=signUpId%>"/></td>

					<td><input type="submit" value="Edit"/></td>
				</form:form>
			</tr>
		<%}%>
	</table>

	<%@ include file="footer.jsp"%>
</body>

</html>