<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui segment">
	<div class="ui comments">
		<h3 class="ui dividing header">My Channels</h3>
		<div class="ui list">
			<c:forEach items="${channels}" var="channel">
				<div class="item">
	    			<img class="ui top aligned avatar image" src="<c:url value="/getImage/${userProfile.avatarId}"/>">
	    			<div class="content">
	      				<div class="header">${channel.name}</div>
	      				${channel.description}
	       			</div>
	      		</div>	
			</c:forEach>
		</div>
	</div>
</div>