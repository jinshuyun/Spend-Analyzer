<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spending analyzer</title>
<!--Load the AJAX API-->

<link rel="stylesheet" href="jquery/jquery-ui.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="normal.css">
<script src="jquery/jquery-1.9.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-ui.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script>
//Load the Visualization API library and the piechart library.
google.load('visualization', '1.0', {'packages':['corechart']});
google.setOnLoadCallback(drawChart);
google.setOnLoadCallback(drawChart2);
// ... draw the chart...
function drawChart() {

        // Create the data table.
       var data = new google.visualization.DataTable(${data});
        // Set chart options
       var options = {'title':'All Categories',
        			   'is3D':true,
                       'width':500,
                       'height':500};

        // Instantiate and draw our chart, passing in some options.
       
	       var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	       chart.draw(data, options);
     	
      }
     
function drawChart2() {

    // Create the data table.
    var data2 = new google.visualization.DataTable(${data2});
    // Set chart options
    var options = {'title':'Spending History',
                   'width':500,
                   'height':400
                   };

    // Instantiate and draw our chart, passing in some options.
   
   		 var chart = new google.visualization.ColumnChart(document.getElementById('chart_div2'));
    	 chart.draw(data2, options);
  
  }
</script>

</head>
<body>

<nav class="navbar navbar-default" role="navigation">
  <div class="container">
  	<div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="visualization?mon=0">Spending</a>
    </div>  
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="visualization?mon=1" title="Recent one month spending">1 Month</a></li>
        <li><a href="visualization?mon=2" title="Last month spending">2 Month</a></li>
        <li><a href="visualization?mon=3" title="Last last month spending">3 Month</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Chart Report <b class="caret"></b></a>
          <ul class="dropdown-menu">            
            <li><button class="btn btn-link" id="line" name="line" onclick="myFunction('popup?chart=line', 'Line')">Line</li>
            <li class="divider"></li>
            <li><button class="btn btn-link" id="bar" name="bar" onclick="myFunction('popup?chart=bar', 'Bar')">Bar</li>
            <li class="divider"></li>
            <li><button class="btn btn-link" id="area" name="area" onclick="myFunction('popup?chart=area', 'Area')" >Area</li>
            <li class="divider"></li>
            <li><button class="btn btn-link" id="combo" name="combo" onclick="myFunction('popup?chart=combo', 'Combo')">Combo</li> 
            <li class="divider"></li>
            <li><button class="btn btn-link" id="stack" name="stack" onclick="myFunction('popup?chart=stack', 'Stacked Bar')">Stacked Bar</li>                   
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="Back to previous steps">Transactions <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li><a href="step1">Upload a new transaction file</a></li>
            <li class="divider"></li>
            <li><a href="step2">Edit the transaction</a></li>       
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><button class="btn btn-link" id="logout" data-toggle="modal" data-target="#deleteform" >Logout</button></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>



<!--Display Chart --> 
<div class="container">
	<div class="panel panel-default">
	  <div class="panel-heading">All Categories</div>
	  <div class="panel-body">
	  <div class="row">
	   <div class="col-xs-12 col-sm-12 col-md-6" id="chart_div"></div>
	   <div class="col-xs-12 col-sm-12 col-md-6" id="chart_div2"></div>
	  </div>
	  </div>
	</div>
	<div class="panel panel-default">
	  <div class="panel-heading">Transactions</div>
	  <div class="panel-body">
	  <div class="table-responsive">
	  <table class="table table-striped">
		<tr align="center">
			<td><Strong>Date</Strong></td>
			<td><Strong>Description</Strong></td>
			<td><Strong>Amount</Strong></td>
			<td><Strong>Category</Strong></td>
		</tr>
		<c:forEach var="data" items="${paneldata}">
			<tr align="center">			   
				<td>${data.date}</td>
				<td>${data.description}</td>
				<td>${data.amount}</td>
				<td>${data.category}</td>
			</tr>
		</c:forEach>
	  </table>
	  </div>
	  </div>
	  </div>
</div>
<form action="visualization" method="post">
<div class="modal fade bs-example-modal-sm" id="deleteform" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4>Confirm</h4>
      </div> 
      <div class="modal-body">
         <p>Delete the history data before log out?</p>
      </div>
      <div class="modal-footer">
      	<input type="submit" class="btn btn-primary" name="yes" value="Yes">
      	<input type="submit" class="btn btn-default" name="no" value="No">
      </div>
     </div>
    </div>
   </div>
</form>
	<div id="mydialog" title="Item Detail">
		<iframe id="myframe" src="" width="650" height="500" frameborder="0" scrolling="no">
		</iframe>
	</div>
	
<script type="text/javascript">
	$(document).ready(
		function() {
			$("#mydialog").dialog({
				autoOpen:false, 
				modal: true, 
				width: 700,
				height: 650,
				position:['center', 'center'], 
				resizable: false, 
				closeOnEscape: true,
				});
			});
			
	function myFunction(source, title) {
	$("#myframe").attr("src", source);
	$("#mydialog").dialog("option", "title", title);
	$("#mydialog").dialog("open");
}

    $(function() {        
        $('a').tooltip({
       	 position: {
    		 my: "center",
    		 at: "bottom+18",
    		 },
    });
    })

</script>
</body>
</html>