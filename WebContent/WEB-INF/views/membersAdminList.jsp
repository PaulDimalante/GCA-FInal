<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ page import="models.GCA_Member" %>
	<%@ page import="java.util.Date" %>
	<%@ page import="java.util.Map" %>
	<%@ page import="java.util.Map.Entry" %>
	<%@ page import="java.util.TreeMap" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Member Administration List</title>
	<style><%@include file="../views/DropDown.css"%></style>
	
	<style>
		.clicked {
			background-color = blue;
		}
	</style>
	
	<script>
		void add() {
			  document.thisForm.tournamentId.value = 0;
			  document.getElementById("thisForm").submit();
		}
	</script>	
</head>

<%
	Map<String,GCA_Member> members = new TreeMap<String, GCA_Member>();
	if (request.getAttribute("members") != null) {
		members = (TreeMap<String, GCA_Member>) request.getAttribute("members");
	}
	int clubId_ = 0;
	if (session.getAttribute("clubId") != null) {
		clubId_ = (Integer) session.getAttribute("clubId");
	}
%>

<body>
	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Member Administration List</h2>
	<div>
		<table border="1">
			<tr>
				<th>Member</th>
			</tr>
			<%for (Entry<String,GCA_Member> m : members.entrySet()) {
				String memberDisplayName_ = m.getKey();
				GCA_Member member = m.getValue();
				int memberId_ = member.getMemberId();%>
				<tr>
					<td><%=memberDisplayName_%></td>
					<td hidden><%=memberId_%></td>
					<td>
						<form action="memberAdminUpdate" method="GET">
							<input type="hidden" name="memberId" value="<%=memberId_%>"/>
							<input type="submit" value="Edit"/>
						</form>
					</td>
				</tr>
			<%}%>
		</table>
		<div>
			<form action="memberAdminUpdate" method="GET">
				<input type="hidden" name="memberId_" value="0"/>
				<input type="submit" value="Add"/>
			</form>
		</div>
	</div>
	
	<%@ include file="footer.jsp"%>
</body>

</html>