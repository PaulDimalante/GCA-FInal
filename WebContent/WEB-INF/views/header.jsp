<%
	String memberName = "";

	if (session.getAttribute("memberName") != null) {
		memberName = (String) session.getAttribute("memberName");
	}
%>

<h1>Golf Club Assistant (GCA)</h1>
<hr>
<%
	if (memberName != "") {
		%><h2>Hello <%=memberName%></h2>
		<hr><%
	}	
%>

