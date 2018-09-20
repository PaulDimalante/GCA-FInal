<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ page import="models.GCA_Tournament" %>
    <%@ page import="springwork.model.Courses" %>
	<%@ page import="java.util.Map" %>
	<%@ page import="java.util.TreeMap" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Tournament Detail</title>
	<style><%@include file="../views/DropDown.css"%></style>
	
	<style>
		table tr td th {
			border:1px solid black;
			collapse-border:collapse;
		}
	</style>	
</head>

<%
	int clubId = (Integer) session.getAttribute("clubId");
	Courses courses = (Courses) session.getAttribute("courses");
	GCA_Tournament tournament = new GCA_Tournament();
	if (request.getAttribute("tournament") != null) {
		tournament = (GCA_Tournament) request.getAttribute("tournament");
	}
	int courseId = tournament.getCourseId();
	String courseName = courses.getCoursesById().get(courseId).getCourseName();
%>

<body>
	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Tournament Detail</h2>
	
	<br>
	<table>
		<tr hidden="hidden">
			<td><%=tournament.getTournamentId()%></td>
			<td><%=clubId%></td>
			<td><%=tournament.getCourseId()%></td>
		</tr>
		<tr>
			<td>Tournament</td>
			<td><%=tournament.getTournamentName()%></td>
		</tr>
		<tr>
			<td>Perpetual Flag</td>
			<td><%=tournament.getAlwaysFlag()%></td>
		</tr>
		<tr>
			<td>Start Date</td>
			<td><%=tournament.getTournamentStartDate()%></td>
			<td>&nbsp&nbsp&nbsp</td>
			<td>End Date</td>
			<td><%=tournament.getTournamentEndDate()%></td>
		</tr>
		<tr>
			<td>Course Name</td>
			<td><%=courseName%></td>
		</tr>
		<tr>
			<td>Rules</td>
			<td><%=tournament.getStrokeMatchRule()%></td>
		</tr>
		<tr>
			<td>Handicaps</td>
			<td><%=tournament.getHandicapRule()%></td>
			<td>&nbsp&nbsp&nbsp</td>
			<td>Reduction%</td>
			<td><%=tournament.getHandicapReductionPct()%></td>
		</tr>
		<tr>
			<td>Team Size</td>
			<td><%=tournament.getTeamSize()%></td>
		</tr>
		<tr>
			<td>Group Play</td>
			<td><%=tournament.getGroupFlag()%></td>
		</tr>
		<tr>
			<td>Points Scoring</td>
			<td><%=tournament.getPointsFlag()%></td>
			<td>&nbsp&nbsp&nbsp</td>
			<td>points front nine</td>
			<td><%=tournament.getPointsFrontNine()%></td>
			<td>&nbsp&nbsp&nbsp</td>
			<td>points back nine</td>
			<td><%=tournament.getPointsBackNine()%></td>
			<td>&nbsp&nbsp&nbsp</td>
			<td>points over all</td>
			<td><%=tournament.getPointsOverAll()%></td>
		</tr>
		<tr>			
			<td>In Flight</td>
			<td><%=tournament.getInFlightFlag()%></td>
		</tr>
		<tr>
			<td>Must Qualify</td>
			<td><%=tournament.getQualifierFlag()%></td>
			<td>&nbsp&nbsp&nbsp</td>
			<td>Qualifier Tournament</td>
			<td><%=tournament.getQualifyingTournamentId()%></td>
		</tr>
	</table>
	<br>

	<%@ include file="footer.jsp"%>
</body>

</html>