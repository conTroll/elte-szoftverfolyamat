<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<h3>User interests</h3>
	
	<div class="ui grid">
		<div class="eight wide right aligned column">
			<div class="ui search">
				<div class="ui icon input">
					<input id="interestInput" class="prompt" type="text" placeholder="Search countries...">
					<i class="smile icon"></i>
				</div>
				<div class="results"></div>
			</div>
		</div>
		<div class="eight wide column">
			<div onClick="addInterestToCurrentUser( $('#interestInput').val() );" class="ui blue button">Add new interest</div>
		</div>
		<div class="sixteen wide column">
			<div id="interests">
				<c:if test="${fn:length(interests) gt 0}">
					<div class="ui list">
					<c:forEach var="interest" items="${interests}">
						<div class="item">
							<img class="ui avatar image" src="<c:url value="/images/interest.png"/>">
							<div class="content">
							  <a id="interest${interest.name}" class="header">${interest.name}</a>
							  <a onClick="deleteInterestFromCurrentUser( $('#interest${interest.name}').text() );" class="description">Delete</a>
							</div>
						</div>
					</c:forEach>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">
		// initializes with default endpoint /show_all/{query}
		$('.ui.search').search({
			apiSettings : {
				url : '<c:url value="/interests/show_all?prefix={query}"/>'
			},
			searchFullText: false
		});
	</script>
</div>