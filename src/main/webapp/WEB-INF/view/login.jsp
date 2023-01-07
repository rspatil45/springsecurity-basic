<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<c:if test="${param.error != null }">
		<i style="color:red">Invalid login or password</i>
	</c:if>
	<c:if test="${param.logout != null}">
		<i style="color:red">Your are successfully logged out</i>
	</c:if>
	<h1>Custom Login Page</h1>
	<form:form action="process-login" method="POST">
	Username : <input type="text" name="username" />
	<br>
	<br>
	Password : <input type="password" name="password" />
	<input type="submit" value="login" />
	</form:form>
</body>
</html>