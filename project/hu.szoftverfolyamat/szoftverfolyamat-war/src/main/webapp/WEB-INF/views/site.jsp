<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/semantic/semantic.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/app.css"/>" />
<script type="text/javascript"
	src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/semantic/semantic.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/szoftverfolyamat-war.js"/>"></script>
<title>YASS</title>
</head>
<body>

	<!-- Modal dialog: Successful channel creation -->
	<div class="ui modal" id="successfulChannel">
		<i class="close icon"></i>
		<div class="header">Channel Created!</div>
		<div class="content">
			<div class="description">Channel successfully created!</div>
		</div>
		<div class="actions">
			<div class="ui button">OK</div>
		</div>
	</div>

	<c:if test="${successfulChannelCreation}">
		<script type="text/javascript">
			loadOwnChannels();
			$('#successfulChannel').modal('show');
		</script>
	</c:if>

	<div class="ui center fluid menu">
		<div class="item">YASS</div>
		<div class="right menu">
			<div class="item">${username}</div>
			<a id="showMessagesLink" class="item"> <i class="mail icon"></i>
				Messages
				<div class="ui red label"><span id="unreadMessageCounter">${numberOfMessages}</span></div>
			</a> <a class="item" href="<c:url value="/j_spring_security_logout"/>">
				<i class="sign out icon"></i> Logout
			</a>
		</div>
	</div>
	<div class="ui grid">
		<div class="four wide column">
			<div class="ui vertical fluid menu">
				<a id="newsLink" class="active teal item"> News <i class="ui inline mini loader"></i><i class="home icon"></i></a>
				<a id="searchUsersLink" class="teal item"> Search Users <i class="ui inline mini loader"></i><i class="search icon"></i></a>
				<a id="recommendedFriends" class="teal item"> Recommended People <i class="ui inline mini loader"></i><i class="search icon"></i></a>
				<a id="showContactsLink" class="teal item"> Show Contacts <i class="ui inline mini loader"></i><i class="users icon"></i></a>
				<a id="browseChannelsLink" class="teal item"> Search Channels <i class="ui inline mini loader"></i><i class="rss icon"></i></a>
				<a id="mySubscriptionsLink" class="teal item"> My Subscriptions <i class="ui inline mini loader"></i><i class="bookmark icon"></i></a>
				<a id="myChannelsLink" class="teal item"> My Channels <i class="ui inline mini loader"></i><i class="signup icon"></i></a>
                <a id="interestsLink" class="teal item"> Interests <i class="ui inline mini loader"></i><i class="smile icon"></i></a>
				<a id="settingsLink" class="teal item"> Settings <i class="ui inline mini loader"></i><i class="settings icon"></i></a>
			</div>
		</div>
		<div id="dynamicContent" class="eight wide column">
			<jsp:include page="news.jsp" />
		</div>
		<div class="four wide column">
			<div class="ui segment">
				<p>Advertisement</p>
				<div class="ui rectangle ad"></div>
			</div>
			<div class="ui segment">
				<p>Advertisement</p>
				<div class="ui rectangle ad"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("a.teal").click(function(event){
			$("a.teal").removeClass("active");
			$(event.target).addClass("active");
			$(event.target).children("i.loader").addClass("active");
		});
		$("#newsLink").click(function() {
			loadNews();
		});
		$("#searchUsersLink").click(function() {
			loadSearchUsers();
		});
		$("#showMessagesLink").click(function() {
			loadMessages();
		});
		$("#recommendedFriends").click(function() {
			loadRecommendedFriends();
		});
		$("#browseChannelsLink").click(function() {
			loadBrowseChannels();
		});
		$("#myChannelsLink").click(function() {
			loadOwnChannels();
		});
		$("#mySubscriptionsLink").click(function() {
			loadSubscribedChannels();
		});
        $("#interestsLink").click(function() {
            loadInterests();
        });
		$("#settingsLink").click(function() {
			loadSettings();
		});
		$("#showMessagesLink").click(function() {
			loadMessages();
		});
	</script>
</body>
</html>
