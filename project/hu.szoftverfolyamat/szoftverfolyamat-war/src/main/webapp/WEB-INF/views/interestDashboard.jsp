<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui segment">
    <div class="ui comments">
        <h3 class="ui dividing header">Interests</h3>

		<div class="ui list">
			<c:forEach var="interest" items="${interests}">
				<div class="item">
					<img class="ui avatar image" src="<c:url value="/images/interest.png"/>">
					<div class="content">
					  <a class="header show" rel="${interest.id}">${interest.name}</a>
					</div>
				</div>
			</c:forEach>
		</div>
    </div>
</div>

<script type="text/javascript">
    $(".show").click(function() {
        loadInterestPage($(this).attr('rel'));
    });
</script>