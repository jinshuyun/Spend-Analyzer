<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>Step Two</title>
</head>
<body>
<div class="container">
<div class="row row-offcanvas row-offcanvas-right">
 <div class="col-xs-12 col-sm-8 col-md-8 main">
     <p class="pull-right visible-xs">
       <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
     </p>
     <div class="table-responsive">
		<table class="table table-striped">
		<tr align="center">
			<td><Strong>Date</Strong></td>
			<td><Strong>Description</Strong></td>
			<td><Strong>Amount</Strong></td>
			<td><Strong>Category</Strong></td>
			<td></td>
		</tr>
		
		<c:forEach var="transaction" items="${transactions}" varStatus="theCount">
		<tr align="center" id="${theCount.count}">
			<td>${transaction.date}</td>
			<td>${transaction.description}</td>
			<td>${transaction.amount}</td>
			<td>${transaction.category}</td>
			<td> <button class="btn btn-link " id="edit"  data-toggle="modal" data-target="#${theCount.count}" title="Click to Zoom" ><span class="glyphicon glyphicon-zoom-in"></span></button></td>
		</tr>
		<form action="recategory" method="post">
		<div class="modal fade" id="${theCount.count}" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			        <h4 class="modal-title">Edit this record </h4>
			      </div> 
			      <div class="modal-body">
					  <fieldset disabled>
					    <div class="form-group">
					      <label for="date">Date</label>
					      <input type="text" name="date" id="disabledTextInput" class="form-control" value="${transaction.date}">
					    </div>
					    <div class="form-group">
					      <label for="desc">Description</label>
					      <input type="text" name="desc" id="desc" class="form-control" value="${transaction.description}">
					    </div>
					    <div class="form-group">
					      <label for="amount">Amount</label>
					      <input type="text" name="amount" id="amount" class="form-control" value="${transaction.amount}">
					    </div>
					    <div class="form-group">
					      <label for="oldca">Category</label>
					      <input type="text" name="oldca" id="oldca" class="form-control" value="${transaction.category}">
					    </div>
					  </fieldset>
					  
					  	<div class="form-group">
					      <label for="newcat1">Re-category to</label>
					      	<select name="newcat1">
						      	<option value="" selected>--Choose--</option>
						      	<c:forEach var="category" items="${categories}">
						      	 <option value="${category.merchant}">${category.merchant}</option>
						      	</c:forEach>
					      	</select>
					    </div>
					    <div align="center">------<b>OR</b>------</div><br>
					    <div class="form-group">
					      <label for="newcat2">New a category?</label>
					      <input type="text" name="newcat2" id="newcat2" class="form-control" placeholder="Category Name" title="Add a new category for this record">
					      <input type="hidden" name="id" value="${transaction.id}">
					    </div>
			      </div>
			      <div class="modal-footer">
			      	<input type="submit" class="btn btn-primary" name="Add" value="Save Change">
			      	<input type="submit" class="btn btn-default" name="Remove" value="Delete">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div>
			    
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->	
		 </form>
		</c:forEach>
		</table>
	 </div>
		<form action="visualization" method="post">
			<input name="report" type="submit" class="btn btn-lg btn-primary" value="Generate a Report">
		</form>
 </div>
	  <div class="col-xs-6 col-sm-4 col-md-4 sidebar-offcanvas" id="sidebar" role="navigation">
       <ul class="nav nav-sidebar">
         <li><a href="step1" title="Back to upload a new file">Step 1 &nbsp;&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-hand-right"></span></a></li>
         <li><a class="active"><b>Step 2</b> - Manage the transaction records</a></li>
		 <li><a href="#" class="disable">Step 3</a></li>
       </ul>
      </div><!--/sidebar-->
</div>
</div>

<script type="text/javascript">
$(document).ready(function () {
	  $('[data-toggle=offcanvas]').click(function () {
	    $('.row-offcanvas').toggleClass('active')
	  });
	});
$('#edit').click(function() {
	$('#myModal').modal({
	   show : true,
	   keyboard : true,
	   backdrop : true
	});
});
$(function() {
    $('a').tooltip({
   	 position: {
		 my: "left",
		 at: "right+10",
		 },
	});
    $('button').tooltip({
   	 position: {
   		 my: "center",
   		 at: "right+70",
   		 },
   });
    $('input').tooltip({
      	 position: {
      		 my: "center",
      		 at: "right+50",
      		 },
      });
})
</script>
</body>
</html>