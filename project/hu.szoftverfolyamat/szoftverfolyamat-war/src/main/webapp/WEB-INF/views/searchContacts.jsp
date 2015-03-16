<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui form segment">
	<h1 class="ui dividing header">Search User Data</h1>
	<div class="field">
		<label>E-mail Address</label> <input id="email" type="text" placeholder="...">
	</div>
	<div class="field">
		<label>Full Name</label> <input id="fullName" type="text" placeholder="...">
	</div>
	<div class="field">
		<label>Place</label> <input id="place" type="text" placeholder="...">
	</div>
	<div class="field">
		<label>Job</label> <input id="job" type="text" placeholder="...">
	</div>
	<div class="ui submit button" onClick="searchContacts();">Search</div>
</div>
<jsp:include page="viewContacts.jsp" />