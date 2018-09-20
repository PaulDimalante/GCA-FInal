<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="springwork.model.Handicaps" %>    
<%@ page import="models.GCA_Member" %>   
<%@ page import="models.GCA_Handicap" %>   
<%@ page import="java.util.Map"%>
<%@ page import="java.util.TreeMap"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Handicaps</title>
	<style><%@include file="../views/DropDown.css"%></style>	
</head>

<%
	Handicaps handicapsBean;	
	Map<String,GCA_Member> members = new TreeMap<String,GCA_Member>(); 
	Map<Integer,GCA_Handicap> handicaps = new TreeMap<Integer,GCA_Handicap>(); 
	if (request.getAttribute("handicaps") != null) {
		handicapsBean = (Handicaps) request.getAttribute("handicaps");
		members = handicapsBean.getMembers();
		handicaps = handicapsBean.getHandicaps();
	}	
%>

<body>
	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Handicaps</h2>

	<table border="1">
		<thead>Handicaps</thead>
		<tfoot></tfoot>
		<tbody>
			<tr>
				<th hidden>MemberId</th>
				<th>Member</th>
				<th>Index</th>
			</tr>
			<%
				for (Map.Entry<String,GCA_Member> e : members.entrySet()) {
					GCA_Member m = e.getValue();
					int memberId_ = m.getMemberId();
					String memberDisplayName = m.getMemberDisplayName();
					double handicapIndex = 0;
					if (handicaps.containsKey(memberId_)) {
						handicapIndex = handicaps.get(memberId_).getHandicapIndex();
					} else {
						handicapIndex = 999;
					}%>
					<tr>
						<td hidden><%=memberId_%></td>
						<td><%=memberDisplayName%></td>
						<td align="right"><%=handicapIndex%></td>
					</tr>
				<%}
			%>
		</tbody>
	</table>

	<%@ include file="footer.jsp"%>
</body>
</html>