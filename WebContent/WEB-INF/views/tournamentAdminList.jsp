<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ page import="springwork.model.Courses" %>
    <%@ page import="models.GCA_Tournament" %>
	<%@ page import="java.util.Date" %>
	<%@ page import="java.util.Map" %>
	<%@ page import="java.util.Map.Entry" %>
	<%@ page import="java.util.TreeMap" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Tournament Administration</title>
	<style><%@include file="../views/DropDown.css"%></style>
	
	<style>
		.clicked {
			background-color = blue;
		}
	</style>
	
	<script>
		void storeTournamentId(tournamentId) {
			  document.thisForm.tournamentId.value = tournamentId;
		}

		void add() {
			  document.thisForm.tournamentId.value = 0;
			  document.getElementById("thisForm").submit();
		}
	</script>	
</head>

<%
	Map<Date,GCA_Tournament> tournaments = (TreeMap<Date,GCA_Tournament>) request.getAttribute("tournaments");
	int clubId_ = 0;
	if (session.getAttribute("clubId") != null) {
		clubId_ = (Integer) session.getAttribute("clubId");
	}
%>

<body>
	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Tournament Administration List</h2>
	<div>
		<table border="1">
			<tr>
				<th>Date</th>
				<th>Name</th>
			</tr>
			<%for (Entry<Date,GCA_Tournament> t : tournaments.entrySet()) {
				Date date = t.getKey();
				GCA_Tournament tournament = t.getValue();
				int tournamentId = tournament.getTournamentId();%>
				<tr>
					<td><%=date%></td>
					<td><%=tournament.getTournamentName()%></td>
					<td>
						<form action="tournamentAdminUpdate" method="GET">
							<input type="hidden" name="tournamentId" value="<%=tournamentId%>"/>
							<input type="submit" value="Edit"/>
						</form>
					</td>
				</tr>
			<%}%>
		</table>
		<div>
			<form action="tournamentAdminUpdate" method="GET">
				<input type="hidden" name="tournamentId" value="0"/>
				<input type="submit" value="Add"/>
			</form>
		</div>
	</div>
	
	<%@ include file="footer.jsp"%>
</body>

</html>