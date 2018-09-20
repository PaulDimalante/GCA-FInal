<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ page import="springwork.model.SignUpBean" %>
    <%@ page import="models.GCA_Tournament" %>
    <%@ page import="models.GCA_SignUp" %>
    <%@ page import="models.GCA_Member" %>
    <%@ page import="java.util.Map" %>
    <%@ page import="java.util.Map.Entry" %>
    <%@ page import="java.util.TreeMap" %>
    <%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>SignUp List Admin</title>
	<style><%@include file="../views/DropDown.css"%></style>	
</head>

<body>
	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Sign Up List Admin</h2>
	
	<%	
		SignUpBean signUpBean = new SignUpBean();
		Map<Date,GCA_Tournament> tournaments = new TreeMap<Date,GCA_Tournament>();
		Map<Integer,Map<Integer,GCA_SignUp>> signUps = new TreeMap<Integer,Map<Integer,GCA_SignUp>>();
		Map<Integer,GCA_Member> members = new TreeMap<Integer,GCA_Member>();
		
		if (request.getAttribute("signUpBean") != null) {
			signUpBean = (SignUpBean) request.getAttribute("signUpBean");
			tournaments = signUpBean.getTournaments();
			signUps = signUpBean.getSignUps();
			members = signUpBean.getMembers();
		}
		
		Date currentDate = new Date();
	
		int clubId = 0;
		if (session.getAttribute("clubId") != null) {
			clubId = (Integer) session.getAttribute("clubId");
		}
	%>

	<table border="1">
		<tr>
			<th>Date</th>
			<th>Tournament</th>
			<th>Member</th>
			<th>Signed up</th>
		</tr>
		<%Date tournamentDateOld = null;
		for (Entry<Date,GCA_Tournament> t : tournaments.entrySet()) {
			Date tournamentDate = t.getKey();
			GCA_Tournament tournament = t.getValue();
			int tournamentId = tournament.getTournamentId();
			String tournamentName = tournament.getTournamentName();
			GCA_SignUp signUp = new GCA_SignUp();
			Date signUpDate = signUp.getSignUpDate();
			int signUpId = 0;
			String signedUpFlag = "False";
			GCA_Member member = new GCA_Member();
			int memberId_ = 0;
			String memberName_ = "";
			
			if (tournamentDateOld == null || ! tournamentDateOld.equals(tournamentDate)) {
			%>
				<tr>
					<form:form action="signUpDetailAdminAdd" method="GET" modelAttribute="signUp">
						<td><input name="tournamentDate" type="date" value="<%=tournamentDate%>"></td>
						<td><input name="tournamentName" value="<%=tournamentName%>"></td>
						<td><input name="memberName" value=""></td>
						<td><form:hidden path="signedUpFlag" name="signedUpFlag" value="False"/></td>
						<td hidden="hidden"><form:input path="clubId" name="clubId" value="<%=clubId%>"/></td>
						<td hidden="hidden"><form:input path="memberId" name="memberId" value="0"/></td>
						<td hidden="hidden"><form:input path="signUpDate" name="signUpDate" value="<%=null%>" type="date"/></td>
						<td hidden="hidden"><form:input path="tournamentId" name="tournamentId" value="<%=tournamentId%>"/></td>
						<td hidden="hidden"><form:input path="signUpId" name="signUpId" value="0"/></td>

						<td><input type="submit" value="Add"/></td>
					</form:form>
				</tr>
			<%}

			tournamentDateOld = tournamentDate;
			
			if (signUps.containsKey(tournamentId)) {
				for (Entry<Integer, GCA_SignUp> e : signUps.get(tournamentId).entrySet()) {
					signUp = e.getValue();
					memberId_ = signUp.getMemberId();
					memberName_ = members.get(memberId_).getMemberName(); 
					signUpId = signUp.getSignUpId();
					signedUpFlag = signUp.getSignedUpFlag();
					%>
	
					<tr>
						<form:form action="signUpDetailAdminUpdate" method="GET" modelAttribute="signUp">
							<td><input name="tournamentDate" type="hidden" value="<%=tournamentDate%>"></td>
							<td><input name="tournamentName" type="hidden" value="<%=tournamentName%>"></td>
							<td><input name="memberName" value="<%=memberName_%>"></td>
							<td><form:input readonly="true" path="signedUpFlag" name="signedUpFlag" value="<%=signedUpFlag%>"/></td>
							<td hidden="hidden"><form:input path="clubId" name="clubId" value="<%=clubId%>"/></td>
							<td hidden="hidden"><form:input path="memberId" name="memberId" value="<%=memberId_%>"/></td>
							<td hidden="hidden"><form:input path="signUpDate" name="signUpDate" value="<%=signUpDate%>" type="date"/></td>
							<td hidden="hidden"><form:input path="tournamentId" name="tournamentId" value="<%=tournamentId%>"/></td>
							<td hidden="hidden"><form:input path="signUpId" name="signUpId" value="<%=signUpId%>"/></td>
	
							<td><input type="submit" value="Edit"/></td>
						</form:form>
					</tr>
				<%}
			}
		}%>
	</table>
	
	<%@ include file="footer.jsp"%>
</body>

</html>