<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/semantic/css/semantic.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/app.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/semantic/javascript/semantic.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/szoftverfolyamat-war.js"/>"></script>
<title>YASS</title>
</head>
<body>
	<div class="ui center fluid menu">
		<div class="item">YASS</div>
		<div class="right menu">
			<div class="item">${username}</div>
			<a class="item"> <i class="mail icon"></i> Messages
				<div class="ui red label">1</div>
			</a> <a class="item" href="<c:url value="/j_spring_security_logout"/>">
				<i class="sign out icon"></i> Logout
			</a>
		</div>
	</div>
	<div class="ui grid">
		<div class="four wide column">
			<div class="ui vertical fluid menu">
				<a id="newsLink" class="item"> News <i class="home icon"></i>
				</a> <a id="searchUsersLink" class="item"> Search Users <i
					class="search icon"></i>
				</a> <a id="showContactsLink" class="item"> Show Contacts <i
					class="users icon"></i>
				</a> <a id="browseChannelsLink" class="item"> Browse Channels <i
					class="feed icon"></i>
				</a> <a id="myChannelsLink" class="item"> My Channels <i
					class="users icon"></i>
				</a> <a id="settingsLink" class="item"> Settings <i
					class="settings icon"></i>
				</a>
			</div>
		</div>
		<div id="dynamicContent" class="eight wide column">
			<jsp:include page="news.jsp" />
		</div>
		<div class="four wide column">
			<div class="ui segment">
				<p>Advertisement</p>
				<div class="ui rectangle ad">
					<img style="width: 100%;"
						src="http://3.bp.blogspot.com/-f3EtRHQvl6I/UUWF5J9jr9I/AAAAAAAAACs/VR5c9GL4Aa4/s798/your-ad-here.jpg" />
				</div>
			</div>
			<div class="ui segment">
				<p>Advertisement</p>
				<div class="ui rectangle ad">
					<img style="width: 100%;"
						src="http://3.bp.blogspot.com/-f3EtRHQvl6I/UUWF5J9jr9I/AAAAAAAAACs/VR5c9GL4Aa4/s798/your-ad-here.jpg" />
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#newsLink").click(function() {
			loadNews();
		});
		$("#searchUsersLink").click(function() {
			loadSearchUsers();
		});
		$("#showContactsLink").click(function() {
			loadShowContacts();
		});
		$("#browseChannelsLink").click(function() {
			loadBrowseChannels();
		});
		$("#myChannelsLink").click(function() {
			alert("Not supported yet!");
		});
		$("#settingsLink").click(function() {
			alert("Not supported yet!");
		});
	</script>
</body>
</html>