<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui form segment">
	<h1 class="ui dividing header">My Subscriptions</h1>
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
							src="<c:url value="/channels/getImage/${channel.id}"/>">
						<div class="content">
							<div class="header">${channel.name}</div>
							${channel.description}
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
			<h3 class="ui dividing header">Pending Subscriptions</h3>
			<div class="ui divided relaxed animated list">
				<c:forEach items="${pendingSubs}" var="channel">
					<div class="item linkitem">
						<img class="ui top aligned avatar image"
							src="<c:url value="/channels/getImage/${channel.id}"/>">
						<div class="content">
							<div class="header">${channel.name}</div>
							${channel.description}
						</div>
					</div>
				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>
</div>