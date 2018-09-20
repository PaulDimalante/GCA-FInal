<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="models.GCA_Member" %>
<%@ page import="models.GCA_Course" %>
<%@ page import="models.GCA_CourseTee" %>
<%@ page import="springwork.model.ScoresBean" %>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="java.util.TreeMap"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.io.PrintWriter"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Scores</title>
	<style>
		<%@include file="../views/DropDown.css"%>	
		
		table {
			border-collapse: collapse;
			table border = "1";
			cellpadding = "5";
			cellspacing = "5";
		}
		tr th td input {
			border-collapse: collapse;
		}
		.hole {
			width: 20px;
		}
		.nine {
			width: 60px;
		}
	</style>
</head>

<body>
<%
	int foresome = 1;
	int courseId = 0;
	int tournamentId = 0;
	Date dateOfRound = null;
	String groupName = "";
	String team = "";
	ScoresBean scoresBean = new ScoresBean();
	Map<String,GCA_Member> memberMap = new TreeMap<String,GCA_Member>();
	Map<String,GCA_Course> courseMap = new TreeMap<String,GCA_Course>();
	Map<Integer,Map<String, GCA_CourseTee>> courseTeeMapAll = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
	Map<String,GCA_CourseTee> courseTeeMap = new TreeMap<String,GCA_CourseTee>();
	if (request.getAttribute("scoresBean") != null) { 
		scoresBean = (ScoresBean) request.getAttribute("scoresBean");
		courseId = scoresBean.getCourseId();
		memberMap = scoresBean.getMembers();
		courseMap = scoresBean.getCourses();
		courseTeeMapAll = scoresBean.getCourseTees();
		groupName = scoresBean.getGroupName();
		team = scoresBean.getTeam();
	}
	if (courseId != 0) {
		courseTeeMap = courseTeeMapAll.get(courseId);
	}
	
	pageContext.setAttribute("memberMap", memberMap);
	pageContext.setAttribute("courseMap", courseMap);
	pageContext.setAttribute("courseTeeMap", courseTeeMap);
%>

	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Scores</h2>
	<form:form action="scores" method="POST" modelAttribute="scoresBean">
		<table width = "100%">
			<tbody>
				<tr>
					<th align="left">Date</th>
					<th align="left">Course</th>
					<th align="left">Tee</th>
					<th align="left">Round#</th>
					<th align="left">Tournament Score</th>
				</tr>
				<tr>
					<td><form:input path="dateOfRound" type="text" placeholder="Date of Round"/></td>
					<td>
						<form:select path="courseId" name="courseId" id="courseId" size="1">
						<form:option name="courseId" value="0" label="please select">please select</form:option>
						<%for (Map.Entry<String,GCA_Course> c : courseMap.entrySet()) {
							String courseName = c.getKey();
							int courseId_ = c.getValue().getCourseId();%>
							<form:option name="courseId" value="<%=courseId_%>"><%=courseName%></form:option>
						<%}%>
						</form:select>
					</td>
					<td>
						<form:select path="courseTeeId" name="courseTeeId" size="1">
							<form:option name="courseTeeId" value="0" label="please select">please select</form:option>
							<%for (Map.Entry<String,GCA_CourseTee> t : courseTeeMap.entrySet()) {
								int courseTeeId = t.getValue().getCourseTeeId();
								String tee = t.getKey();%>
								<form:option name="courseTeeId" value="<%=courseTeeId%>"><%=tee%></form:option>
							<%}%>
						</form:select>
					</td>
					<td><form:input path="roundNo" type="number" placeholder="1"/></td>
					<td>
						<form:radiobutton path="tournamentFlag" name="tournamentFlag" value="Yes" label="Yes"/><br>
						<form:radiobutton path="tournamentFlag" name="tournamentFlag" value="No" label="No"/>
					</td>
					<td hidden="hidden"><form:hidden path="groupName" value="<%=groupName%>"/></td>
					<td hidden="hidden"><form:hidden path="team" value="<%=team%>"/></td>
				</tr>
			</tbody>
		</table>
		<br>
	
		<table width="100%">
			<tbody>
				<tr style="border:1;">
					<th>Player</th>
					<%for (int h=1;h<10;h++) {%><th><%=h%></th><%}%>
					<th>frontnine</th>
					<%for (int h=10;h<19;h++) {%><th><%=h%></th><%}%>
					<th>backnine</th>
					<th>overall</th>
				</tr>
				<%for (int p=0;p<4;p++) {
					String mid = String.format("memberId[%d]",p);
					String fnine = String.format("frontNine[%d]",p);
					String bnine = String.format("backNine[%d]",p);
					String oall = String.format("overAll[%d]",p);
					String sid = String.format("scoreId[%d]",p);
					String midVal = String.format("scoresBean.getMemberId[%d]",p);
					String sidVal = String.format("scoresBean.getScoreId[%d]",p);
					String fnineVal = String.format("scoresBean.getFrontNine[%d]",p);
					String bnineVal = String.format("scoresBean.getBackNine[%d]",p);
					String oallVal = String.format("scoresBean.getOverAll[%d]",p);%>
					<tr style="border:1;">
						<td hidden="hidden"><form:hidden path="<%=sid%>" name="<%=sid%>" value="%<=sidVal%>"/></td>
						<td>
							<form:select path="<%=mid%>" name="<%=mid%>" size="1" value="<%=midVal%>">
								<form:option name="<%=mid%>" value="0">please select</form:option>
								<%for (Entry<String,GCA_Member> e : memberMap.entrySet()) {
									String n = e.getKey();
									int i = e.getValue().getMemberId();%>
									<form:option name="<%=mid%>" value="<%=i%>"><%=n%></form:option>
								<%}%>
							</form:select>
						</td>
						<%for (int h=0;h<9;h++) {
							String holeId = String.format("holeScoreId[%d][%d]",p,h);
							int holeIdVal = scoresBean.getHoleScoreId()[p][h];
							String hole = String.format("holeScore[%d][%d]",p,h);
							int holeVal = scoresBean.getHoleScore()[p][h];%>
							<td hidden="hidden"><form:hidden path="<%=holeId%>" value="<%=holeIdVal%>"/></td>
							<td><form:input path="<%=hole%>" class="hole" value="<%=holeVal%>"/></td>
						<%}%>
						<td><form:input path="<%=fnine%>" class="nine" value="<%=fnineVal%>"/></td>
						<%for (int h=9;h<18;h++) {
							String holeId = String.format("holeScoreId[%d][%d]",p,h);
							int holeIdVal = scoresBean.getHoleScoreId()[p][h];
							String hole = String.format("holeScore[%d][%d]",p,h);
							int holeVal = scoresBean.getHoleScore()[p][h];%>
							<td hidden="hidden"><form:hidden path="<%=holeId%>" value="<%=holeIdVal%>"/></td>
							<td><form:input path="<%=hole%>" class="hole" value="<%=holeVal%>"/></td>
						<%}%>
						<td><form:input path="<%=bnine%>" class="nine" value="<%=bnineVal%>"/></td>
						<td><form:input path="<%=oall%>" class="nine" value="<%=oallVal%>"/></td>
					</tr>
				<%}%>
			</tbody>
		</table>
		<input name="submit" type="submit" value="submit">
	</form:form>

	<%@ include file="footer.jsp"%>
</body>
</html>