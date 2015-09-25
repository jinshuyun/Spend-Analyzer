<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spending analyzer</title>
<!--Load the AJAX API-->
<script src="jquery/jquery-1.9.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery/jquery-ui.js"></script>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">

<link rel="stylesheet" href="normal.css">
 <link rel="stylesheet" href="jquery/jquery-ui.css">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script>
//Load the Visualization API library and the piechart library.
google.load('visualization', '1.0', {'packages':['corechart']});

google.setOnLoadCallback(drawChartLine);
google.setOnLoadCallback(drawChartBar);
google.setOnLoadCallback(drawChartCombo);
google.setOnLoadCallback(drawChartArea);
google.setOnLoadCallback(drawStackedBar);
// ... draw the chart...
  
function drawChartLine() {
    var data3 = new google.visualization.DataTable(${data3});

   
      var options = {
    		    title: 'How is my Top 3 popular categories spending?',
    		    legend: { position: 'bottom' },
    		    width: 650,
                height: 500
    		  };

     
   		 var chart = new google.visualization.LineChart(document.getElementById('pic1'));  
   		 chart.draw(data3, options);
      
  }


function drawChartBar() {
	var data4 = new google.visualization.DataTable(${data4});

  var options = {
   			title: 'How is my Top 3 popular categories spending?',
   			vAxis: {title: 'Month',  titleTextStyle: {color: 'black'}},
   			legend: { position: 'bottom' },
		    width: 650,
            height: 500
  };

 
 	 var chart = new google.visualization.BarChart(document.getElementById('pic1'));
  	 chart.draw(data4, options);
  
}
function drawChartCombo() {
    // Some raw data (not necessarily accurate)
    var data5 = new google.visualization.DataTable(${data5});

    var options = {
      title : 'Monthly average spending on Top 3 pupular categories',
      vAxis: {title: "Amount"},
      hAxis: {title: "Month"},
      seriesType: "bars",
      legend: { position: 'bottom' },
      series: {
    	       0: {color: "#0489B1"},
    	       1: {color: "#8A0886"},
    	       2: {color: "#088A08"},
    	       3: {color: "#FF0000", type: "line"}},
      width: 650,
      height: 500
    };

    
    	var chart = new google.visualization.ComboChart(document.getElementById('pic1'));  
    	chart.draw(data5, options);
   
  }
function drawChartArea() {
	var data6 = new google.visualization.DataTable(${data6});
    var options = {
      title: 'How is Top 3 popular categories on my spending?',
      hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
      vAxis: {minValue: 0},
      legend: { position: 'bottom' },
	  width: 650,
      height: 500
    };

    var chart = new google.visualization.AreaChart(document.getElementById('pic1'));
    chart.draw(data6, options);
  }

function drawStackedBar() {
	var data7 = new google.visualization.DataTable(${data7});
	var options = {
			title : 'Monthly spending on the following categories',
	        width: 700,
	        height: 500,
	        legend: { position: 'top', maxLines: 4 },
			bar: { groupWidth: '55%' },
	        isStacked: true,
	};

    var chart = new google.visualization.BarChart(document.getElementById('pic1'));
    chart.draw(data7, options);
  }
</script>

</head>
<body>
<div id="pic1" align="left"></div>
</body>
</html>