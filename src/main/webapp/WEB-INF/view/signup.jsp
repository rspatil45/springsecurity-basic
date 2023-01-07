<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Signup Here !</h1>
	<form:form action="process-signup" method="POST" modelAttribute="signupdto">
		<form:input path="username" type="text"/>
		<br>
		<form:input path="password" type="password" />
		<br>
		<input type="submit" value="signup">
	</form:form>
</body>
</html>