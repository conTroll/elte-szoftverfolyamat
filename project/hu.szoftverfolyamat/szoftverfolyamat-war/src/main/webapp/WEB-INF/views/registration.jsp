<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/semantic/css/semantic.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/app.css"/>" />
<script type="text/javascript"
	src="<c:url value="/semantic/javascript/semantic.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>elte szoftverfolyamat</title>
</head>
<body>
	<div style="width: 80%; margin-left: 10%; margin-top: 10%;" class="ui form pilled segment">
		<div class="ui error message">
			<div class="header">Action Forbidden</div>
			<p>You can only sign up for an account once with a given e-mail
				address.</p>
		</div>
		<div class="two fields">
			<div class="field">
				<label>First Name</label> <input placeholder="First Name"
					type="text">
			</div>
			<div class="field">
				<label>Last Name</label> <input placeholder="Last Name" type="text">
			</div>
		</div>
		<div class="field">
			<label>Gender</label>
			<div class="ui fluid selection dropdown">
				<div class="text">Select</div>
				<i class="dropdown icon"></i> <input type="hidden" name="gender">
				<div class="menu">
					<div class="item" data-value="male">Male</div>
					<div class="item" data-value="female">Female</div>
				</div>
			</div>
		</div>
		<div class="field">
			<label>Username</label> <input placeholder="Username" type="text">
		</div>
		<div class="field">
			<label>Password</label> <input type="password">
		</div>
		<div class="inline field">
			<div class="ui checkbox">
				<input type="checkbox"> <label>I agree to the Terms
					and Conditions</label>
			</div>
		</div>
		<div class="ui blue submit button">Submit</div>
	</div>
</body>
</html>