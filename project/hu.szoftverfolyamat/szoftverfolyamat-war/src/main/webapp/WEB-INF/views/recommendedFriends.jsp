<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form id="recommendedFriendsForm" action="recommendedFriends" method="post"
	class="ui form pilled segment">
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="recommendByHabitat" type="checkbox"
				value="${recommendedFriendsRequest.recommendByHabitat}" /> <label>Recommend by your habitat?</label>
		</div>
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="recommendByJob" type="checkbox"
				value="${recommendedFriendsRequest.recommendByJob}" /> <label>Recommend by your job?</label>
		</div>
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="recommendByWorkplace" type="checkbox"
				value="${recommendedFriendsRequest.recommendByWorkplace}" /> <label>Recommend by your workplace?</label>
		</div>
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="recommendByInterests" type="checkbox"
				value="${recommendedFriendsRequest.recommendByInterests}" /> <label>Recommend by your interests?</label>
		</div>
	</div>
	
	<div class="ui blue submit button" onClick="submitRecommendPeople($('#recommendedFriendsForm'));">Recommend friends</div>
</form>
<script type="text/javascript">
		$('.ui.checkbox').checkbox();

		$('#recommendedFriendsForm').form();
</script>