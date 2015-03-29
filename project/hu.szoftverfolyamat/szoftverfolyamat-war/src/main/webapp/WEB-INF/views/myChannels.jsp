<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui segment">
	<div class="ui comments">
		<div id="headSection">
			<h1 class="ui dividing header">My Channels</h1>
			<div class="ui dividing blue labeled submit icon button"
				onclick="document.location.href = '/szoftverfolyamat-war/channels/create';">
				<i class="icon edit"></i> Create new...
			</div>
		</div>
		<c:choose>
			<c:when test="${empty channels}">
				<p>You don't have any channels, yet. Go ahead, and make one! :)</p>
			</c:when>
			<c:otherwise>
				<div class="ui divided relaxed animated list">
					<c:forEach items="${channels}" var="channel">
						<div class="item linkitem">
							<img class="ui top aligned avatar image"
								src="<c:url value="/getImage/${userProfile.avatarId}"/>">
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
</div>