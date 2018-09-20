<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ page import="models.*" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Member Admin Update</title>
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
	String memberPassWordVerify = "";
	String memberLoginId = "";
	if (request.getAttribute("GCA_Member") != null) {
		member = (GCA_Member) request.getAttribute("GCA_Member");
		memberId_ = member.getMemberId();
		clubId_ = member.getClubId();
		memberName_ = member.getMemberName();
		memberNickName = member.getMemberNickName();
		memberUsgaId = member.getMemberUsgaId();
		memberPassWord = member.getMemberPassWord();
		memberPassWordOrig = member.getMemberPassWord();
		memberPassWordVerify = member.getMemberPassWord();
		memberLoginId = member.getMemberLoginId();
	}
%>

<body>
 	<%@ include file="header.jsp"%> 
	<%@ include file="menu.jsp"%>
	
	<h2>Member Admin Update</h2>
	
	<form action="memberAdminUpdate" method="post">
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
					<td>Login Id</td>
					<td><input name="memberLoginId" type="text" value="<%=memberLoginId%>"/></td>
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
					<td hidden="hidden"><input name="memberPassWordOrig" type="hidden" value="<%=memberPassWordOrig%>"/></td>
				</tr>
				<tr>
					<td>Password Verification</td>
					<td><input name="memberPassWordVerify" type="password" value="<%=memberPassWordVerify%>"/></td>
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
	<form action="memberAdminDelete" method="post">
		<input name="memberId" type="hidden" value="<%=memberId_ %>"/>
		<input name="clubId" type="hidden" value="<%=clubId_ %>"/>
		<input name="memberName" type="hidden" value="<%=memberName_%>"/>
		<input name="memberLoginId" type="hidden" value="<%=memberLoginId%>"/>
		<input name="memberNickName" type="hidden" value="<%=memberNickName%>"/>
		<input name="memberUsgaId" type="hidden" value="<%=memberUsgaId%>"/>
		<input name="memberPassWord" type="hidden" value="<%=memberPassWord%>"/>
		<input name="submit" type="submit" value="Delete"/>
	</form>
	
	<%@ include file="footer.jsp"%>
</body>
</html>