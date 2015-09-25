<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="message" scope="request" class="java.lang.String" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
<%=message%>
<form action="userregister" method="post">
<table>
	<tr>
		<td>Username:</td>
		<td><input type="text" name="username" /></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type="password" name="password1" /></td>
	</tr>
	<tr>
		<td>Retype Password:</td>
		<td><input type="password" name="password2" /></td>
	</tr>
	<tr>
		<td><input type="submit" name="confirm" value="Confirm"/></td>
	</tr>
</table>
</form>
</body>
</html>