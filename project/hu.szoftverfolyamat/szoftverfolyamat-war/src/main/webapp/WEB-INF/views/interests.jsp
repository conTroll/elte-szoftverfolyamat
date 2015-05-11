<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<h3>User interests</h3>

	<div id="interests">
		<c:if test="${fn:length(interests) gt 0}">
			<c:forEach var="interest" items="${interests}">
				<a href="#"><span>${interest.name}</span></a>
			</c:forEach>
		</c:if>
	</div>

	<div class="ui search">
		<div class="ui icon input">
			<input class="prompt" type="text" placeholder="Search countries...">
			<i class="smile icon"></i>
		</div>
		<div class="results"></div>
	</div>
	<a href="#">Add new interest</a>
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