<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="springwork.model.LoginBean" %>
<%@ page import="models.GCA_Club" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<style><%@include file="../views/DropDown.css"%></style>	
</head>
 
<%
	String loginId = "";
	String passWord = "";
	LoginBean loginBean = new LoginBean();
	if (request.getAttribute("loginBean") != null) {
		loginBean = (LoginBean) request.getAttribute("loginBean");
	}
	if (request.getAttribute("loginId") != null) {
		loginId = (String) request.getAttribute("loginId");
	}
	if (request.getAttribute("passWord") != null) {
		passWord = (String) request.getAttribute("passWord");
	}
	//testing
	//loginId = "Dimalante, Joseph";
	//passWord = "rusty";
%>

<body>
	<%@ include file="header.jsp"%>
	
	<h2>Login</h2>
	<form action="login" method="post">
		Login Name:<input name="loginId" type="text" placeholder="Enter Login Id" autofocus value="<%=loginId%>"><br>
		Password:<input name="passWord" type="password" placeholder="Enter Password" value="<%=passWord%>"><br>
		Club<table>
			<tr>
				<td>
					<select name="clubId" size="1">
						<%for (Map.Entry<String,GCA_Club> c : loginBean.getMemberClubs().entrySet()) {
							String clubName = c.getKey();
							int clubId = c.getValue().getClubId();
							%><option value="<%=clubId%>"><%=clubName%></option><%
						}%>
					</select>
				</td>
			</tr>
		</table><br>
		<input name="loginBtn" type="submit" value="Login" /><br>
	</form>

	<%@ include file="footer.jsp"%>
</body>
</html>