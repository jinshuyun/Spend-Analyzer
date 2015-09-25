<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="message" scope="request" class="java.lang.String" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="normal.css">
<title>Login</title>
</head>
<body>

<div class="container" id="form-main">
<div><%=message%><div>
<form class="form-signin" role="form" action="userlogin" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="text" class="form-control" name="username" id="username" placeholder="Enter username" required autofocus>
    <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>   
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <br>
    <a href="register">Create an account</a>
</form>
</div>
</body>
</html>