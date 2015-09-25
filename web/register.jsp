<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="message" scope="request" class="java.lang.String" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="normal.css">
<script src="jquery/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<title>Register</title>
</head>
<body>

<div class="container" id="form-main">
<div class="panel panel-primary">
  <div class="panel-heading">Register Now</div>
  <div class="panel-body">
  <div><%=message%></div>
	<form  class="form-register" role="form" action="userregister" method="post">
	<div class="form-group">
	    <label for="username">Username</label>
	    <input type="text" class="form-control" name="username" id="username" data-toggle="popover" data-placement="left"  placeholder="Enter username" data-content="You can use letters or numbers" required autofocus>
	</div>
	<div class="form-group">
	    <label for="password1">Password</label>
	    <input type="password" class="form-control" name="password1" id="password1" data-toggle="popover" data-placement="left" placeholder="Password" data-content="Use at least 6 characters" required>
	</div>
	<div class="form-group">
	    <label for="password2">Retype Password</label>
	    <input type="password" class="form-control" name="password2" id="password2" placeholder="Retype password" required>
	</div>		
	<div class="form-group">
		 <button class="btn btn-primary btn-block" type="submit">Confirm</button>		
	</div>	
	</form>
   </div>
</div>
</div>
<script>
$('#username').popover({trigger: 'focus'});
$('#password1').popover({trigger: 'focus'});
</script>
</body>
</html>