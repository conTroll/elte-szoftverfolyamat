<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui">
	<div class="ui top attached tabular menu">
		<div class="active item" data-tab="settings">Profile</div>
		<div class="item" data-tab="imageUpload">Profile picture</div>
	</div>
	<div class="ui bottom attached active tab segment" data-tab="settings">
		<jsp:include page="settings.jsp" />
	</div>
	<div class="ui bottom attached tab segment" data-tab="imageUpload">
		<jsp:include page="imageUpload.jsp" />
	</div>

	<script type="text/javascript">
		$('.tabular.menu .item').tab({history:false});
	</script>
</div>
