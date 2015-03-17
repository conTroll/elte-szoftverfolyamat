<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="profile" method="post"
	commandName="userCredentialDto"
	style="width: 80%; margin-left: 10%; margin-top: 10%;"
	class="ui form pilled segment">
	<div class="ui error message">
		<div class="header">Action Forbidden</div>
		<p>You can only sign up for an account once with a given e-mail
			address.</p>
	</div>
	<div class="two fields">
		<div class="field">
			<label>Full Name</label>
			<form:input path="userProfileDataDto.fullName"
				placeholder="Full Name" type="text" />
		</div>
		<div class="field">
			<label>Short Name</label>
			<form:input path="userProfileDataDto.shortName"
				placeholder="Short Name" type="text" />
		</div>
	</div>
	<div class="two fields">
		<label>Username</label>
		<form:input path="username" placeholder="Userame" type="text" />
		<label>Password</label>
		<form:input path="password" placeholder="Password" type="password" />
	</div>
	<div class="field">
		<label>Email</label>
		<form:input path="userProfileDataDto.email" placeholder="Email"
			type="text" />
	</div>
	<div class="field">
		<label>Habitat</label>
		<form:input path="userProfileDataDto.habitat" placeholder="Habitat"
			type="text" />
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<form:checkbox path="userProfileDataDto.publicHabitat" />
			<label>Is habitat public?</label>
		</div>
	</div>
	<div class="two fields">
		<label>Job</label>
		<form:input path="userProfileDataDto.job" placeholder="Job"
			type="text" />
		<label>Workplace</label>
		<form:input path="userProfileDataDto.workplace"
			placeholder="Workplace" type="text" />
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<form:checkbox path="userProfileDataDto.publicJobAndWorkplace" />
			<label>Is job and workplace public?</label>
		</div>
	</div>
	<div class="field">
		<label>Birthday</label>
		<form:input path="userProfileDataDto.birthday"
			placeholder="YYYY.MM.dd" type="text" />
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<form:checkbox path="userProfileDataDto.publicBirthday" />
			<label>Is birthday public?</label>
		</div>
	</div>
	<form:input path="userProfileDataDto.credentialId"
				type="hidden" />
	<form:input path="userProfileDataDto.enabled"
				type="hidden" />
	<form:input path="userProfileDataDto.userProfileDataDto.credentialId"
				type="hidden" />
	<form:input path="userProfileDataDto.userProfileDataDto.friendNumber"
				type="hidden" />
	<form:input path="userProfileDataDto.userProfileDataDto.avatarId"
				type="hidden" />
	<form:button class="ui blue submit button">Submit</form:button>
</form:form>
<script type="text/javascript">
	$(document).ready(function() {
		$('.ui.checkbox').checkbox();
	});
</script>