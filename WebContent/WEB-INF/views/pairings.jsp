<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="springwork.model.PairingsList" %>   
<%@ page import="springwork.model.Pairing" %>   
<%@ page import="commonFunctions.CommonFunctions" %>   
<%@ page import="java.util.Date"%>
<%@ page import="java.util.TreeMap"%>
<%@ page import="java.util.Map"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Pairings</title>
	<style><%@include file="../views/DropDown.css"%></style>	
</head>

<%
	PairingsList pairingsList = new PairingsList();
	Map<Date, Pairing> map = new TreeMap<Date, Pairing>();
	String dateOfPlayFormatted = "";
	if (request.getAttribute("PairingsList") != null) {
		pairingsList = (PairingsList) request.getAttribute("PairingsList");
		map = pairingsList.getPairingsList();
		dateOfPlayFormatted = pairingsList.getDateOfPlayFormatted();
	}
%>

<body>
	<%@ include file="header.jsp"%>
	<%@ include file="menu.jsp"%>
	
	<h2>Pairings - DUMMY DATA</h2>
	<table border = "1" cellpadding = "5" cellspacing = "5">
		<caption>Pairings</caption>
		<thead>
			<td colspan = "5"><%=dateOfPlayFormatted%></td>
		</thead>
		<tfoot>
			<td colspan = "5">Rules</td>
		</tfoot>
		<tbody>
			<tr>
				<th>Tee Time</th>
				<th>Captain</th>
				<th>Player2</th>
				<th>Player3</th>
				<th>Player4</th>
			</tr>
			
			<%
				for (Map.Entry<Date,Pairing> e : map.entrySet()) {
					Pairing p = e.getValue();
					String teeTimeFormatted = p.getTeeTimeFormatted();
					String player1 = p.getPlayer1();
					String player2 = p.getPlayer1();
					String player3 = p.getPlayer1();
					String player4 = p.getPlayer1();
					%>
					<tr>
						<td><%=teeTimeFormatted%></td>
						<td><%=player1%></td>
						<td><%=player2%></td>
						<td><%=player3%></td>
						<td><%=player4%></td>
					</tr>
					<%
				}
			%>
			
		</tbody>
	</table>

	<%@ include file="footer.jsp"%>
</body>
</html>