<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ page import="models.*" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>MembersAdmin</title>
	<style><%@include file="../views/DropDown.css"%></style>	
</head>

<%
	GCA_Member member = new GCA_Member();
	int memberId_ = -1;
	int clubId_ = -1;
	String memberName_ = "";
	String memberNickName = "";
	String memberUsgaId = "";
	String memberPassWord = "";
	String memberPassWordOrig = "";
	if (request.getAttribute("GCA_Member") != null) {
		member = (GCA_Member) request.getAttribute("GCA_Member");
		memberId_ = member.getMemberId();
		clubId_ = member.getClubId();
		memberName_ = member.getMemberName();
		memberNickName = member.getMemberNickName();
		memberUsgaId = member.getMemberUsgaId();
		memberPassWord = member.getMemberPassWord();
		memberPassWordOrig = member.getMemberPassWord();
	}
%>

<body>
 	<%@ include file="header.jsp"%> 
	<%@ include file="menu.jsp"%>
	
	<h2>Members</h2>
	
	<form action="members" method="post">
		<table border = "1" cellpadding = "5" cellspacing = "5">
			<caption>Member</caption>
			<thead>
				<td colspan = "2">Member Information</td>
			</thead>
			<tfoot>
				<td colspan = "2"></td>
			</tfoot>
			<tbody>
				<tr>
					<th>Field</th>
					<th>Data</th>
				</tr>
				<tr>
					<td>Name</td>
					<td><input name="memberName" type="text" value="<%=memberName_%>"/></td>
				</tr>
				<tr>
					<td>Nick Name</td>
					<td><input name="memberNickName" type="text" value="<%=memberNickName%>"/></td>
				</tr>
				<tr>
					<td>USGA Id</td>
					<td><input name="memberUsgaId" type="text" value="<%=memberUsgaId%>"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input name="memberPassWord" type="password" value="<%=memberPassWord%>"/></td>
					<td><input name="memberPassWordOrig" type="hidden" value="<%=memberPassWordOrig%>"/></td>
				</tr>
			</tbody>
		</table>
		<%
		String strMemberId = String.valueOf(memberId_);
		String strClubId = String.valueOf(clubId_);
		%>
		<input name="memberId" type="hidden" value="<%=memberId_ %>"/>
		<input name="clubId" type="hidden" value="<%=clubId_ %>"/>
		<input name="submit" type="submit" value="Update"/>
	</form>
	
	<%@ include file="footer.jsp"%>
</body>
</html>