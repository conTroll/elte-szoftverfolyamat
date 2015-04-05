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
	src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/semantic/javascript/semantic.js"/>"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>elte szoftverfolyamat</title>
</head>
<body class="ui page">
	<div style="text-align: center; margin-top: 5%;">
		<h1>YASS</h1>
	</div>
	<div style="width: 50%; margin-left: 25%; margin-top: 5%;"
		class="ui<c:if test="${not empty error}"> error</c:if> piled segment">
		<div class="ui two column middle aligned relaxed grid basic segment">
			<div class="column">
				<form id="loginForm" class="ui form segment"
					action="<c:url value='j_spring_security_check' />" method="POST">
					<div class="ui error message">
						<div class="header">ERROR</div>
						<p>Invalid username or password!</p>
					</div>
					<div class="field">
						<label>Username</label>
						<div class="ui left labeled icon input">
							<input name="username" type="text" placeholder="Username">
							<i class="user icon"></i>
							<div class="ui corner label">
								<i class="asterisk icon"></i>
							</div>
						</div>
					</div>
					<div class="field">
						<label>Password</label>
						<div class="ui left labeled icon input">
							<input name="password" type="password" placeholder="Password">
							<i class="lock icon"></i>
							<div class="ui corner label">
								<i class="asterisk icon"></i>
							</div>
						</div>
					</div>
					<input type="submit" class="ui blue submit button" value="Login" />
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
			<div class="ui vertical divider">Or</div>
			<div class="center aligned column">
				<div class="huge green ui labeled icon button"
					onclick="location.href='<c:url value="/registration"/>';">
					<i class="signup icon"></i> Sign Up
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#loginForm').form({
			username : {
				identifier : 'username',
				rules : [ {
					type : 'empty',
					prompt : 'Username is required'
				}, ]
			},
			password : {
				identifier : 'password',
				rules : [ {
					type : 'empty',
					prompt : 'Password is required'
				}, ]
			}
		}, {
			inline : true,
			on : 'blur',
			transition : 'fade down'
		});
	</script>
</body>
</html>