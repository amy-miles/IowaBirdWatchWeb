<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="editSightingServlet" method="post">
		Sighting Date: <input type="text" name="month" placeholder="mm" size="4">
						<input type="text" name = "day" placeholder="dd" size="4">
						<input type="text" name="year" placeholder="yyyy" size="4">
		County: <input type="text" name="county" value="${itemToEdit.county }">
		Bird: <input type="text" name="bird" value="${itemToEdit.bird }">
		<input type="hidden" name="id" value="${itemToEdit.id }">
		<input type="submit"  value="Save Edited Sighting">
	</form>
</body>
</html>