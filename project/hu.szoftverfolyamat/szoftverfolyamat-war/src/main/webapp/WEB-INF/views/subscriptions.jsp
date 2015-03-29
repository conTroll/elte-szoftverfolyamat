<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui form segment">
	<h1 class="ui dividing header">My Channels</h1>
	<c:choose>
		<c:when test="${empty activeSubs}">
			<p>You don't have any subscriptions.</p>
			<p><a href="javascript:void(0);" onclick="$('#browseChannelsLink').click();">Search</a> for some new channels, which you can subscribe to.</p>
		</c:when>
		<c:otherwise>
			<div class="ui divided relaxed animated list">
				<c:forEach items="${activeSubs}" var="channel">
					<div class="item linkitem">
						<img class="ui top aligned avatar image"
							src="<c:url value="/getImage/${userProfile.avatarId}"/>">
						<div class="content">
							<div class="header">${activeSubs.name}</div>
							${activeSubs.description}
						</div>
					</div>
				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${empty pendingSubs}">

		</c:when>
		<c:otherwise>
			<h1 class="ui dividing header">Pending Subscriptions</h1>
			<div class="ui divided relaxed animated list">
				<c:forEach items="${pendingSubs}" var="channel">
					<div class="item linkitem">
						<img class="ui top aligned avatar image"
							src="<c:url value="/getImage/${userProfile.avatarId}"/>">
						<div class="content">
							<div class="header">${pendingSubs.name}</div>
							${pendingSubs.description}
						</div>
					</div>
				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>
</div>