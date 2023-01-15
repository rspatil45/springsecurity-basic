<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${param.passwordChanged != null}">
		<i style="color: green;">Password Changed successfully</i>
	</c:if>
	
	<h1>Hi ${username}</h1>
	<h4>Role Assigned : ${roles}</h4>

	<sec:authorize access="hasAuthority('Trainer')">
		<a href="/springsecurity/trainer">Show Trainer's Dashboard</a>
	</sec:authorize>
	<br>
	
	<sec:authorize access="hasAuthority('Coder')">  
	<a href="/springsecurity/coder">Show Student's Dashboard</a>
	</sec:authorize>
	<br>
	&nbsp;
	<div>
		<a href="deleteUser?username=${username}">Delete Account</a>
	</div>
	<div>
		<a href="changePassword">Change Password</a>
	</div>

	<form:form action="logout" method="POST">
		<input type="submit" value="logout">
	</form:form>
</body>
</html>