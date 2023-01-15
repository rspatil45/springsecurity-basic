<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<c:if test="${param.invalidPassword != null}">
		<i style="color: red">Error occurred while password change</i>
	</c:if>
	<div style="align-content: center;">
		<h1>Reset Password</h1>
		<form:form action="changePassword" modelAttribute="changePasswordDTO"
			method="POST">
			<label>Old Password</label>
			<form:input path="oldPassword" />
			<br>
			<label>New Password</label>
			<form:input path="newPassword" />
			<br>
			<label>Confirm Password</label>
			<form:input path="confirmPassword" />
			<br>
			<input type="submit" value="change password">
		</form:form>
	</div>

</body>
</html>