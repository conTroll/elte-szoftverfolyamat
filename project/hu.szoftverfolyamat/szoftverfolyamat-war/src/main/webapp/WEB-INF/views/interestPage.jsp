<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="ui segment">
    <div class="ui ">
        <h2 class="ui dividing header">Interest: ${interest.name}</h2>
    </div>

	<h3 class="ui header">Users with this interest</h3>

    <c:if test="${fn:length(users) gt 0}">
		<div class="ui three column grid">
            <c:forEach var="user" items="${users}">
				<div class="column">
					<div id="contact${user.credentialId}" rel="${user.credentialId}" class="ui fluid card">
						<div class="dimmable image">
							<div class="ui inverted dimmer">
								<div class="content">
									<div class="center">
										<div onClick="loadProfileView( ${user.credentialId} );" class="ui blue button">View Profile</div>
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
								<a class="delete"  style="float: right; cursor: pointer;"> Delete </a>
								<a class="messages" style="cursor: pointer;"> Chat </a>
							</c:if>
							<c:if test="${(not user.friend) and (currentUserId != user.credentialId)}">
								<a class="addContact"
								   style="float: right; cursor: pointer;"> Add Friend </a>
							</c:if>
						</div>
					</div>
				</div>
            </c:forEach>
		</div>
    </c:if>
    <c:if test="${fn:length(users) eq 0}">
		<div class="ui form segment">
			<p>No one has this interest yet :(</p>
		</div>
    </c:if>

    <h3>Channels with this interest</h3>

    <div class="ui form segment">
        <c:if test="${fn:length(channels) gt 0}">
            <c:forEach items="${channels}" var="channel">
                <div class="item linkitem">
                    <img class="ui top aligned avatar image"
                         src="<c:url value="/channels/getImage/${channel.id}"/>">
                    <div class="content">
                        <div class="header">${channel.name}</div>
                            ${channel.description}
                    </div>
                </div>
            </c:forEach>
        </c:if>

        <c:if test="${fn:length(channels) eq 0}">
            <p>No channel uses this interest yet :(</p>
        </c:if>
    </div>
</div>
<script type="text/javascript">
	$('.special.cards .image').dimmer({
		on : 'hover'
	});

    $(".messages").click(function() {
        loadMessagesWithUser($(this).parent().parent().attr('rel'));
    });
	$(".delete").click(function() {
		deleteContact($(this).parent().parent().attr('id'));
	});
	$(".addContact").click(function() {
		addContact($(this).parent().parent().attr('id'));
	});
	
	
</script>