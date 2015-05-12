<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form id="recommendedFriendsForm" action="recommendedFriends" method="post"
	class="ui form pilled segment">
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="publicHabitat" type="checkbox"
				value="${recommendedFriendsRequest.publicHabitat}" /> <label>Recommend by your habitat?</label>
		</div>
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="publicJobAndWorkplace" type="checkbox"
				value="${recommendedFriendsRequest.publicJobAndWorkplace}" /> <label>Recommend by your job?</label>
		</div>
	</div>
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="publicBirthday" type="checkbox"
				value="${recommendedFriendsRequest.publicBirthday}" /> <label>Recommend by your birthday?</label>
		</div>
	</div>
	
	<div class="inline field">
		<div class="ui toggle checkbox">
			<input name="publicBirthday" type="checkbox"
				value="${recommendedFriendsRequest.publicBirthday}" /> <label>Recommend by your birthday?</label>
		</div>
	</div>
	<input name="credentialId" value="${profileFormRequest.credentialId}"
		type="hidden" /> <input type="submit" class="ui blue submit button"
		value="Save" />
</form>
<script type="text/javascript">
		$('.ui.checkbox').checkbox();

		$('#recommendedFriendsForm').form();
</script>