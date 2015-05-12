<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="ui segment">
	<c:if test="${not empty isRecommendByWorkplace}">
		<h3 class="ui dividing header">Recommended by workplace:</h3>
		<c:if
			test="${fn:length(recommendFriendsMap['COMMON_WORKPLACE']) gt 0}">
			<div class="ui three column grid">
				<c:forEach var="user"
					items="${recommendFriendsMap['COMMON_WORKPLACE']}">
					<div class="column">
						<div id="contact${user.credentialId}" rel="${user.credentialId}"
							class="ui fluid card">
							<div class="dimmable image">
								<div class="ui inverted dimmer">
									<div class="content">
										<div class="center">
											<div onClick="loadProfileView( ${user.credentialId} );"
												class="ui blue button">View Profile</div>
										</div>
									</div>
								</div>
								<img src="<c:url value="/getImage/${user.avatarId}"/>">
							</div>
							<div class="ui bottom attached content segment">
								<a class="ui header">${user.fullName}</a>
								<div class="meta"></div>
								<a> ${user.friendNumber} Friends </a>
								<c:if test="${user.friend}">
									<a class="delete" style="float: right; cursor: pointer;">
										Delete </a>
									<a class="messages" style="cursor: pointer;"> Chat </a>
								</c:if>
								<c:if
									test="${(not user.friend) and (currentUserId != user.credentialId)}">
									<a class="addContact" style="float: right; cursor: pointer;">
										Add Friend </a>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${fn:length(recommendFriendsMap['COMMON_WORKPLACE']) eq 0}">
			<div class="ui form segment">
				<p>No one found :(</p>
			</div>
	    </c:if>
	</c:if>
	<c:if test="${not empty isRecommendByHabitat}">
		<h3 class="ui dividing header">Recommended by habitat:</h3>
		<c:if test="${fn:length(recommendFriendsMap['COMMON_HABITAT']) gt 0}">
			<div class="ui three column grid">
				<c:forEach var="user"
					items="${recommendFriendsMap['COMMON_HABITAT']}">
					<div class="column">
						<div id="contact${user.credentialId}" rel="${user.credentialId}"
							class="ui fluid card">
							<div class="dimmable image">
								<div class="ui inverted dimmer">
									<div class="content">
										<div class="center">
											<div onClick="loadProfileView( ${user.credentialId} );"
												class="ui blue button">View Profile</div>
										</div>
									</div>
								</div>
								<img src="<c:url value="/getImage/${user.avatarId}"/>">
							</div>
							<div class="ui bottom attached content segment">
								<a class="ui header">${user.fullName}</a>
								<div class="meta"></div>
								<a> ${user.friendNumber} Friends </a>
								<c:if test="${user.friend}">
									<a class="delete" style="float: right; cursor: pointer;">
										Delete </a>
									<a class="messages" style="cursor: pointer;"> Chat </a>
								</c:if>
								<c:if
									test="${(not user.friend) and (currentUserId != user.credentialId)}">
									<a class="addContact" style="float: right; cursor: pointer;">
										Add Friend </a>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${fn:length(recommendFriendsMap['COMMON_HABITAT']) eq 0}">
			<div class="ui form segment">
				<p>No one found :(</p>
			</div>
	    </c:if>
	</c:if>
	<c:if test="${not empty isRecommendByJob}">
		<h3 class="ui dividing header">Recommended by job:</h3>
		<c:if test="${fn:length(recommendFriendsMap['COMMON_JOB']) gt 0}">
			<div class="ui three column grid">
				<c:forEach var="user" items="${recommendFriendsMap['COMMON_JOB']}">
					<div class="column">
						<div id="contact${user.credentialId}" rel="${user.credentialId}"
							class="ui fluid card">
							<div class="dimmable image">
								<div class="ui inverted dimmer">
									<div class="content">
										<div class="center">
											<div onClick="loadProfileView( ${user.credentialId} );"
												class="ui blue button">View Profile</div>
										</div>
									</div>
								</div>
								<img src="<c:url value="/getImage/${user.avatarId}"/>">
							</div>
							<div class="ui bottom attached content segment">
								<a class="ui header">${user.fullName}</a>
								<div class="meta"></div>
								<a> ${user.friendNumber} Friends </a>
								<c:if test="${user.friend}">
									<a class="delete" style="float: right; cursor: pointer;">
										Delete </a>
									<a class="messages" style="cursor: pointer;"> Chat </a>
								</c:if>
								<c:if
									test="${(not user.friend) and (currentUserId != user.credentialId)}">
									<a class="addContact" style="float: right; cursor: pointer;">
										Add Friend </a>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${fn:length(recommendFriendsMap['COMMON_JOB']) eq 0}">
			<div class="ui form segment">
				<p>No one found :(</p>
			</div>
	    </c:if>
	</c:if>
	<c:if test="${not empty isRecommendByInterests}">
		<h3 class="ui dividing header">Recommended by interests:</h3>
		<c:if
			test="${fn:length(recommendFriendsMap['COMMON_INTERESTS']) gt 0}">
			<div class="ui three column grid">
				<c:forEach var="user"
					items="${recommendFriendsMap['COMMON_INTERESTS']}">
					<div class="column">
						<div id="contact${user.credentialId}" rel="${user.credentialId}"
							class="ui fluid card">
							<div class="dimmable image">
								<div class="ui inverted dimmer">
									<div class="content">
										<div class="center">
											<div onClick="loadProfileView( ${user.credentialId} );"
												class="ui blue button">View Profile</div>
										</div>
									</div>
								</div>
								<img src="<c:url value="/getImage/${user.avatarId}"/>">
							</div>
							<div class="ui bottom attached content segment">
								<a class="ui header">${user.fullName}</a>
								<div class="meta"></div>
								<a> ${user.friendNumber} Friends </a>
								<c:if test="${user.friend}">
									<a class="delete" style="float: right; cursor: pointer;">
										Delete </a>
									<a class="messages" style="cursor: pointer;"> Chat </a>
								</c:if>
								<c:if
									test="${(not user.friend) and (currentUserId != user.credentialId)}">
									<a class="addContact" style="float: right; cursor: pointer;">
										Add Friend </a>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${fn:length(recommendFriendsMap['COMMON_INTERESTS']) eq 0}">
			<div class="ui form segment">
				<p>No one found :(</p>
			</div>
	    </c:if>
	</c:if>
</div>
