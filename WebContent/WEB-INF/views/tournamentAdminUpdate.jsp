<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ page import="springwork.model.Courses" %>
    <%@ page import="models.GCA_Course" %>
    <%@ page import="models.GCA_Tournament" %>
	<%@ page import="java.util.Map" %>
	<%@ page import="java.util.Map.Entry" %>
	<%@ page import="java.util.TreeMap" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Tournament Update</title>
	<style><%@include file="../views/DropDown.css"%></style>	
</head>

<%
	Map<String,GCA_Course> courses = new TreeMap<String,GCA_Course>();
	if (session.getAttribute("courses") != null) {
		courses = ((Courses) session.getAttribute("courses")).getCoursesByName();
	}
	int clubId_ = 0;
	if (session.getAttribute("clubId") != null) {
		clubId_ = (Integer) session.getAttribute("clubId");
	}
	GCA_Tournament tournament = new GCA_Tournament();
	if (request.getAttribute("tournament") != null) {
		tournament = (GCA_Tournament) request.getAttribute("tournament");
	}
%>

<body>
	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Tournament Administration Update</h2>
	<div>
		<form:form action="tournamentAdminUpdate" method="POST" modelAttribute="tournament">
			<div>
				<form:hidden path="tournamentId"/>
				<form:hidden path="clubId" value="<%=clubId_%>"/>
				<form:label path="tournamentName">Tournament Name</form:label>
				<form:input path="tournamentName"/>
			</div>
			<div>
				<label>Perpetual Flag</label>
				<form:radiobutton path="alwaysFlag" name="alwaysFlag" value="True"/>Yes
				<form:radiobutton path="alwaysFlag" name="alwaysFlag" value="False"/>No
			</div>
			<div>
				<label>Start and Ending Dates</label>
				<form:input path="tournamentStartDate" type="date"/>
				<form:input path="tournamentEndDate" type="date"/>
			</div>
			<div>
				<label>Course</label>
				<form:select path="courseId" name="courseId">
				<%for (Entry<String,GCA_Course> c : courses.entrySet()) {
					String courseName = c.getKey();
					int courseId_ = c.getValue().getCourseId();%>
					<form:option name="courseId" value="<%=courseId_%>" label="<%=courseName%>"/>					
				<%}%>		
				</form:select>		
			</div>
			<div>
				<label>Stroke/Match Play Rules</label>
				<form:radiobutton path="strokeMatchRule" name="strokeMatchRule" value="Stroke"/>Stroke Play
				<form:radiobutton path="strokeMatchRule" name="strokeMatchRule" value="Match"/>Match Play
			</div>
			<div>
				<label>Handicap Rule</label>
				<form:radiobutton path="handicapRule" name="handicapRule" value="True"/>Handicaps
				<form:radiobutton path="handicapRule" name="handicapRule" value="False"/>No Handicaps
			</div>
			<div>
				<label>Handicap Reduction Pct</label>
				<form:input type="number" path="handicapReductionPct"/>
			</div>
			<div>
				<label>Team Size</label>
				<form:input type="number" path="teamSize"/>
			</div>
			<div>
				<label>Group Flag</label>
				<form:radiobutton path="groupFlag" name="groupFlag" value="True"/>Yes
				<form:radiobutton path="groupFlag" name="groupFlag" value="False"/>No
			</div>
			<div>
				<label>Points Flag</label>
				<form:radiobutton path="pointsFlag" name="pointsFlag" value="True"/>Yes
				<form:radiobutton path="pointsFlag" name="pointsFlag" value="False"/>No
			</div>
			<div>
				<label>Points Front Nine</label>
				<form:input type="number" path="pointsFrontNine"/>
			</div>
			<div>
				<label>Points Back Nine</label>
				<form:input type="number" path="pointsBackNine"/>
			</div>
			<div>
				<label>Points Overall</label>
				<form:input type="number" path="pointsOverAll"/>
			</div>
			<div>
				<label>Inflight Flag</label>
				<form:radiobutton path="inFlightFlag" name="inFlightFlag" value="True"/>Yes
				<form:radiobutton path="inFlightFlag" name="inFlightFlag" value="False"/>No
			</div>
			<div>
				<label>Qualifier Flag</label>
				<form:radiobutton path="qualifierFlag" name="qualifierFlag" value="True"/>Yes
				<form:radiobutton path="qualifierFlag" name="qualifierFlag" value="False"/>No
			</div>
			<div>
				<label>Qualifying Tournament</label>
				<form:select name="qualifyingTournamentId" path="qualifyingTournamentId">
					<form:option name="qualifyingTournamentId" value="0" label="None"/>
				</form:select>
			</div>
			<div>
				<input type="submit" value="Update"/>
			</div>
		</form:form>
	</div>
	
	<%@ include file="footer.jsp"%>
</body>

</html>