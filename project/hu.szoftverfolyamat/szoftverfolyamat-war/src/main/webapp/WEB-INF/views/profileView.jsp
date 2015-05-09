<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="ui grid">
		<div class="six wide column">
			<img class="ui medium rounded image"
				src="<c:url value="/getImage/${userProfileDataDto.avatarId}"/>">
		</div>
		<div class="ten wide column">
			<form class="ui form pilled segment">
				<h1 class="ui header">Profile data</h1>
				<div class="ui divider"></div>
				<div class="ui error message">
					<div class="header">Some error occured</div>
				</div>
				<div class="two fields">
					<div class="field">
						<label>Full Name</label> <input disabled name="fullName"
							value="${userProfileDataDto.fullName}" type="text" />
					</div>
					<div class="field">
						<label>Short Name</label> <input disabled name="shortName"
							value="${userProfileDataDto.shortName}" type="text" />
					</div>
				</div>
				<div class="field">
					<label>Email</label> <input disabled name="email"
						value="${userProfileDataDto.email}" type="text" />
				</div>
				<c:if test="${userProfileDataDto.publicHabitat}">
					<div class="field">
						<label>Habitat</label> <input disabled name="habitat"
							value="${userProfileDataDto.habitat}" type="text" />
					</div>
				</c:if>
				<c:if test="${userProfileDataDto.publicJobAndWorkplace}">
					<div class="two fields">
						<div class="field">
							<label>Job</label> <input disabled name="job"
								value="${userProfileDataDto.job}" type="text" />
						</div>
						<div class="field">
							<label>Workplace</label> <input disabled name="workplace"
								value="${userProfileDataDto.workplace}" type="text" />
						</div>
					</div>
				</c:if>
				<c:if test="${userProfFileDataDto.publicBirthday}">
					<div class="field">
						<label>Birthday</label> <input disabled name="birthday"
							value="${userProfileDataDto.birthday}" type="text" />
					</div>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>