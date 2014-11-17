<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<form:form action="registration" method="post" commandName="userCredentialDto" style="width: 80%; margin-left: 10%; margin-top: 10%;" class="ui form pilled segment">
		<div class="ui error message">
			<div class="header">Action Forbidden</div>
			<p>You can only sign up for an account once with a given e-mail
				address.</p>
		</div>
		<div class="two fields">
			<div class="field">
				<label>Full Name</label> <form:input path="userProfileDataDto.fullName" placeholder="Full Name"
					type="text" />
			</div>
			<div class="field">
				<label>Short Name</label> <form:input path="userProfileDataDto.shortName" placeholder="Short Name" type="text" />
			</div>
		</div>
		<div class="two fields">
			<label>Username</label> <form:input path="username" placeholder="Userame"
					type="text" />
			<label>Password</label> <form:input path="password" placeholder="Password"
					type="password" />
		</div>
		<div class="field">
			<label>Email</label> <form:input path="userProfileDataDto.email" placeholder="Email"
					type="text" />
		</div>
		<div class="field">
			<label>Habitat</label> <form:input path="userProfileDataDto.habitat" placeholder="Habitat"
					type="text" />
		</div>
		<div class="inline field">
			<div class="ui checkbox">
				<form:checkbox path="userProfileDataDto.publicHabitat" /> <label>Is habitat public?</label>
			</div>
		</div>
		<div class="two fields">
			<label>Job</label> <form:input path="userProfileDataDto.job" placeholder="Job"
					type="text" />
			<label>Workplace</label> <form:input path="userProfileDataDto.workplace" placeholder="Workplace"
					type="text" />
		</div>
		<div class="inline field">
			<div class="ui checkbox">
				<form:checkbox path="userProfileDataDto.publicJobAndWorkplace" /> <label>Is job and workplace public?</label>
			</div>
		</div>
		<div class="field">
			<label>Birthday</label> <form:input path="userProfileDataDto.birthday" placeholder="YYYY.MM.dd"
					type="text" />
		</div>
		<div class="inline field">
			<div class="ui checkbox">
				<form:checkbox path="userProfileDataDto.publicBirthday" /> <label>Is birthday public?</label>
			</div>
		</div>
		<form:button class="ui blue submit button">Submit</form:button>
	</form:form>
</body>
</html>