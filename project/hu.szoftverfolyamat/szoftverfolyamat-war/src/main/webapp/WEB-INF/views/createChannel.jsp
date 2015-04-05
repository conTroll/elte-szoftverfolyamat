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
	src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/semantic/javascript/semantic.js"/>"></script>
<script type="text/javascript">
	$(function() {
		$('.ui.checkbox').checkbox();
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>elte szoftverfolyamat</title>
</head>
<body>

	<form:form action="create" method="post"
		commandName="createChannelRequest"
		style="width: 80%; margin-left: 10%; margin-top: 10%;"
		class="ui form pilled segment">

		<c:if test="${not empty error}">
			<div class="ui error message visible">
				<p>${error}</p>
			</div>
		</c:if>

		<c:choose>
			<c:when test="${not empty error}">
				<div class="field error">
					<label>Channel Name <span style="color: red">*</span></label>
					<form:input path="name" placeholder="Channel Name" type="text" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="field">
					<label>Channel Name <span style="color: red">*</span></label>
					<form:input path="name" placeholder="Channel Name" type="text" />
					<form:errors path="name" cssclass="error"></form:errors>
				</div>
			</c:otherwise>
		</c:choose>

		<div class="field">
			<label>Channel Description</label>
			<form:input path="description" placeholder="Description" type="text" />
			<form:errors path="description" cssclass="error"></form:errors>
		</div>
		<div class="inline field">
			<div class="ui toggle checkbox">
				<form:checkbox path="open" />
				<label>Open channel?</label>
			</div>
		</div>
		<p>Posts in open channels are visible to anyone, and anyone can join automatically.</p>
		<p>Posts in closed channels are visible only to members, and new members can join only with approval of the channel leader.</p>
		<form:button class="ui blue labeled submit icon button">
			<i class="icon edit"></i> Create
		</form:button>
		<div class="ui blue submit button"
			onclick="document.location.href = '/szoftverfolyamat-war/';">
			Return to site
		</div>
	</form:form>

</body>
</html>