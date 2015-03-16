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
	<form:form action="create" method="post"
		commandName="createChannelRequest"
		style="width: 80%; margin-left: 10%; margin-top: 10%;"
		class="ui form pilled segment">
		<c:if test="${not empty error}">
			<div class="ui error message">
				<div class="header">Oops! Something went wrong. :(</div>
				<p>${error}</p>
			</div>
		</c:if>

		<div class="field">
			<label>Channel Name</label>
			<form:input path="name" placeholder="Channel Name" type="text" />
		</div>
		<div class="field">
			<label>Channel Description</label>
			<form:input path="description" placeholder="Description" type="text" />
		</div>
		<div class="inline field">
			<div class="ui checkbox">
				<form:checkbox path="open" />
				<label>Can subscribe to?</label>
			</div>
		</div>
		<form:button class="ui blue submit button">Submit</form:button>
	</form:form>
</body>
</html>