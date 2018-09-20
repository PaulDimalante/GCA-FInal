<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>EnrollClub</title>
	<style><%@include file="../views/DropDown.css"%></style>	
</head>

<body>
	<%@ include file="header.jsp"%>

	<h2>Enroll Club</h2>
	
	<div class="container">
		<form action="enrollClub" method="POST">
			<fieldset>
				<legend>Club Info</legend>
				<div class="sub_field">
					<label>Club Name</label>
					<input name="clubName" type="text" placeHolder="Enter Club Name"/>
				</div>
				<div class="sub_field">
					<label>USGA Id</label>
					<input name="clubUsgaId" type="text" placeHolder="Enter USGA Id"/>
				</div>
				<div class="sub_field">
					<label>Name of Home Course</label>
					<input name="clubHomeCourseName" type="text" placeHolder="Enter Name of Home Course"/>
				</div>
				<div class="sub_field">
					<label>City</label>
					<input name="clubCity" type="text" placeHolder="Enter City"/>
				</div>
				<div class="sub_field">
					<label>State</label>
					<input name="clubState" type="text" placeHolder="Enter State"/>
				</div>
			</fieldset>
			<br>
			<fieldset>
				<legend>Club Administrator</legend>
				<div class="sub_field">
					<label>Name</label>
					<input name="memberName" type="text" placeHolder="Enter Member Name"/>
				</div>
				<div class="sub_field">
					<label>Nickname</label>
					<input name="memberNickName" type="text" placeHolder="Enter Member's Nickname"/>
				</div>
				<div class="sub_field">
					<label>Login Id</label>
					<input name="memberLoginId" type="text" placeHolder="Enter Member's Login Id"/>
				</div>
				<div class="sub_field">
					<label>USGA Id</label>
					<input name="memberUsgaId" type="text" placeHolder="Enter USGA Id"/>
				</div>
				<div class="sub_field">
					<label>Password</label>
					<input name="memberPassWord" type="password" placeHolder="Enter Password"/>
					<input name="memberPassWordOrig" type="hidden"/>
				</div>
				<div class="sub_field">
					<label>Password Verify</label>
					<input name="memberPassWordVerify" type="password" placeHolder="Verify Password"/>
				</div>
			</fieldset>
			<div class="sub_field">
				<input type="submit" value="Enroll Club"/>
			</div>
		</form>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>