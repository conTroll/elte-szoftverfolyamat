<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui special cards">
	<c:forEach items="${contactList}" var="contact">
		<div id="contact${contact.credentialId}" rel="${contact.credentialId}" class="card">
			<div class="dimmable image">
				<div class="ui inverted dimmer">
					<div class="content">
						<div class="center">
							<div class="ui blue button">View Profile</div>
						</div>
					</div>
				</div>
				<img src="http://semantic-ui.com/images/avatar/large/elliot.jpg">
			</div>
			<div class="ui bottom attached content segment">
				<a class="ui header">${contact.fullName}</a>
				<div class="meta"></div>
				<a> ${contact.friendNumber} Friends </a>
				<c:if test="${contact.friend}">
                    <a class="delete"  style="float: right; cursor: pointer;"> Delete </a>
                    <a class="messages" style="cursor: pointer;"> Chat </a>
				</c:if>
				<c:if test="${not contact.friend}">
				<a class="addContact"
					style="float: right; cursor: pointer;"> Add Friend </a>
				</c:if>
			</div>
		</div>
	</c:forEach>
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