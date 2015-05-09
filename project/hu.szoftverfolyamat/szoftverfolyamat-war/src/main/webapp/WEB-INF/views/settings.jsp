<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<form id="profileForm" action="profile" method="post"
	class="ui form">
	<div class="ui error message">
		<div class="header">Some error occured</div>
	</div>
	<div class="two fields">
		<div class="field">
			<label>Full Name</label>
			<input name="fullName" value="${profileFormRequest.fullName}"
				placeholder="Full Name" type="text" />
		</div>
		<div class="field">
			<label>Short Name</label>
			<input name="shortName" value="${profileFormRequest.shortName}"
				placeholder="Short Name" type="text" />
		</div>
	</div>
	<div class="two fields">
		<div class="field">
			<label>Username</label>
			<input READONLY name="username" value="${profileFormRequest.username}" placeholder="Userame" type="text" />
		</div>
		<div class="field">
			<label>Password</label>
			<input name="password" value="${profileFormRequest.password}" placeholder="Password" type="password" />
		</div>
	</div>
	<div class="field">
		<label>Email</label>
		<input name="email" value="${profileFormRequest.email}" placeholder="Email"
			type="text" />
	</div>
	<div class="field">
		<label>Habitat</label>
		<input name="habitat" value="${profileFormRequest.habitat}" placeholder="Habitat"
			type="text" />
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="publicHabitat" type="checkbox" value="${profileFormRequest.publicHabitat}" <c:if test="${profileFormRequest.publicHabitat == 'on'}"> checked </c:if>/>
			<label>Is habitat public?</label>
		</div>
	</div>
	<div class="two fields">
		<div class="field">
			<label>Job</label>
			<input name="job" value="${profileFormRequest.job}" placeholder="Job"
				type="text" />
		</div>
		<div class="field">
		<label>Workplace</label>
			<input name="workplace" value="${profileFormRequest.workplace}"
				placeholder="Workplace" type="text" />
		</div>
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="publicJobAndWorkplace" type="checkbox" value="${profileFormRequest.publicJobAndWorkplace}" <c:if test="${profileFormRequest.publicJobAndWorkplace == 'on'}"> checked </c:if> />
			<label>Is job and workplace public?</label>
		</div>
	</div>
	<div class="field">
		<label>Birthday</label>
		<input name="birthday" value="${profileFormRequest.birthday}"
			placeholder="YYYY.MM.dd" type="text" />
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="publicBirthday" type="checkbox" value="${profileFormRequest.publicBirthday}" <c:if test="${profileFormRequest.publicBirthday == 'on'}"> checked </c:if> />
			<label>Is birthday public?</label>
		</div>
	</div>
	<input name="credentialId" value="${profileFormRequest.credentialId}"
				type="hidden" />
	<input name="" type="submit" class="ui blue submit button" value="Save" />
</form>

<%--<div id="interests">--%>
    <%--<c:if test="${fn:length(interests) gt 0}">--%>
        <%--<c:forEach var="interest" items="${interests}">--%>
            <%--<a href="#"><span>${interest.name}</span></a>--%>
        <%--</c:forEach>--%>
    <%--</c:if>--%>
    <%--<div>--%>
        <%--<a href="#">Add new interest</a>--%>
    <%--</div>--%>
<%--</div>--%>

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

<script type="text/javascript">
	$('.ui.checkbox').checkbox();
	
	$('#profileForm').form({
		fullName : {
			identifier : 'fullName',
			rules : [ {
				type : 'empty',
				prompt : 'Full Name is required!'
			}, ]
		},
		shortName : {
			identifier : 'shortName',
			rules : [ {
				type : 'empty',
				prompt : 'Short Name is required!'
			}, ]
		},
		password : {
			identifier : 'password',
			rules : [ {
				type : 'empty',
				prompt : 'Password is required!'
			}, ]
		},
		email : {
			identifier : 'email',
			rules : [ {
				type : 'email',
				prompt : 'Not valid email address!'
			}, ]
		},
		habitat : {
			identifier : 'habitat',
			rules : [ {
				type : 'empty',
				prompt : 'Habitat is required!'
			}, ]
		},
		job : {
			identifier : 'job',
			rules : [ {
				type : 'empty',
				prompt : 'Job is required!'
			}, ]
		},
		workplace : {
			identifier : 'workplace',
			rules : [ {
				type : 'empty',
				prompt : 'Workplace is required!'
			}, ]
		},
		birthday : {
			identifier : 'birthday',
			rules : [ {
				type : 'empty',
				prompt : 'Birthday is required!'
			}, ]
		}
	}, {
		inline : true,
		on : 'blur',
		transition : 'fade down',
	});
</script>