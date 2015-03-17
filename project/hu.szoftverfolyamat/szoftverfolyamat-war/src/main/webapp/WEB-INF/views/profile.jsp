<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui segment">
	<div class="ui top attached tabular menu">
		<a class="active item" data-tab="settings">First</a> <a class="item"
			data-tab="imageUpload">Second</a>
	</div>
	<div class="ui bottom attached active tab segment" data-tab="settings">
		<jsp:include page="settings.jsp" />
	</div>
	<div class="ui bottom attached tab segment" data-tab="imageUpload">
		<jsp:include page="imageUpload.jsp" />
	</div>
	<script type="text/javascript">
		$('.menu .item').tab();
	</script>
</div>
