<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui segment">
	<div class="ui tabular menu">
		<div class="item" data-tab="tab-name">Settings</div>
		<div class="item" data-tab="tab-name2">Profile picture upload</div>
	</div>
	<div class="ui tab" data-tab="tab-name">
		<jsp:include page="settings.jsp" /> 
	</div>
	<div class="ui tab" data-tab="tab-name2">
		<jsp:include page="imageUpload.jsp" /> 
	</div>
</div>
