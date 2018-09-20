<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<html>
<head>
	<style>
		* {
			border:1px solid black
		}
	</style>
</head>

<body>
	<form:form action="junk" method="GET" modelAttribute="junk">
		<legend>junk</legend>
		<fieldset>
			f1<form:input path="f1" id="f1" name="f1" type="text" cssStyle="size:20 width:40px background-color:grey" tabindex="1" />
			<br>
			f2<form:input path="f2" id="f2" name="f2" type="text" cssStyle="size:20 width:40px background-color:blue" tabindex="2" value="fff"></form:input>
			<br>
			f3<form:input path="f3" id="f3" name="f3" type="text" cssStyle="size:20 width:40px background-color:blue" tabindex="3" />
			<br>
			f4<form:input path="f4" id="f4" name="f4" type="text" cssStyle="size:20 width:40px background-color:blue" tabindex="4" />
			<br>
			f0<form:input path="f0" id="f0" name="f0" type="text" cssStyle="size:20 width:40px background-color:blue" tabindex="5" />
			<br>
		</fieldset>
		<input type="submit" value="submit"><br>
	</form:form>
	
	<script>
		document.getElementById("f1").addEventListener("change", function(){
			var vf2 = document.getElementById("f2");
			vf2.value = "Hello World!";
		});	
	</script>
</body>

</html>