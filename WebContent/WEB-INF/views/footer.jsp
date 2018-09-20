<%
	String message = "";
	if (request.getAttribute("message") != null) {
		message = (String) request.getAttribute("message");
		request.setAttribute("message", "");
	}
%>

<hr>
<p><%= message %></p>
<br>
