<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Bird Sightings</title>
</head>
<body>
	<form method= "post" action= "navigationServlet">
		<table>
			<!--c:forEach is a JSTL tag that allows us to cycle through a list and 
		print out each item as it goes through the list like an enhanced for loop
		You must use 'items' 'currentitem' -->
			<c:forEach items="${requestScope.allSightings}" var="currentsighting">
				<tr>
					<td><input type="radio" name="id"
						value="${currentsighting.id}"></td>
					<td>${currentsighting.siteDate }</td>
					<td>${currentsighting.county }</td>
					<td>${currentsighting.bird }</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value= "edit" name="doThisToItem">
		<input type="submit" value= "delete" name="doThisToItem">
		<input type="submit" value= "add" name="doThisToItem">
	</form>
</body>
</html>