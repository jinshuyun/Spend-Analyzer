<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
<body>
${message}
<table>
<tr>
  <td>Date</td>
  <td>Description</td>
  <td>Amount</td>
  <td>Category</td>
</tr>
<c:forEach var="transaction" items="${transactions}">
<tr>
	<td>${transaction.date}</td>
	<td>${transaction.description}</td>
	<td>${transaction.amount}</td>
	<td>${transaction.category}</td>
</tr>
</c:forEach>
</table>
<form action="upload" method="post" enctype="multipart/form-data">
  <input type="file" name="uploadfile">
  <input type="submit" value="Submit">
</form> 

</body>
</html>