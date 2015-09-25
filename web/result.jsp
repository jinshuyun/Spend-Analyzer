<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="jquery/jquery-ui.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="normal.css">
<link rel="stylesheet" href="offcanvas.css">
<script src="jquery/jquery-1.9.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-ui.js"></script>
<title>Step One</title>
</head>
<body>
${message}
<div class="container">
 <div class="row row-offcanvas row-offcanvas-right">
	 <div class="col-xs-12 col-sm-8 col-md-8 main">
     <p class="pull-right visible-xs">
       <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
     </p>
     <div class="table-responsive">
		<table class="table table-striped">
		<tr align="center">
		  <td>Date</td>
		  <td>Description</td>
		  <td>Amount</td>
		  <td>Category</td>
		</tr>
		<c:forEach var="transaction" items="${transactions}">
		<tr align="center">
			<td>${transaction.date}</td>
			<td>${transaction.description}</td>
			<td>${transaction.amount}</td>
			<td>${transaction.category}</td>
		</tr>
		</c:forEach>
		</table>
	</div>
	</div>
    <div class="col-xs-6 col-sm-4 col-md-4 sidebar-offcanvas" id="sidebar" role="navigation">
       <ul class="nav nav-sidebar">
         <li><a class="active"><b>Step 1</b> - Upload the transaction</a></li>
         <li><a href="step2" title="Skip to Step 2">Step 2 &nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-hand-right"></span></a></li>
		 <li><a href="#" class="disable">Step 3</a></li>
       </ul>
       <br>
       <div class="panel panel-default">
		  <div class="panel-heading">Upload the transaction file</div>
		  <div class="panel-body">
		    <form action="upload" method="post" enctype="multipart/form-data">
			  <input type="file" name="uploadfile">
			  <br>
			  <button type="submit" class="btn btn-primary">Submit</button>
			</form> 
		  </div>
		</div>       
     </div><!--/sidebar-->
 </div>
</div>
<script type="text/javascript">
$(function() {
    $('a').tooltip({
   	 position: {
		 my: "left",
		 at: "right+10",
		 },
});
})

$(document).ready(function () {
  $('[data-toggle=offcanvas]').click(function () {
    $('.row-offcanvas').toggleClass('active')
  });
});
</script>
</body>
</html>