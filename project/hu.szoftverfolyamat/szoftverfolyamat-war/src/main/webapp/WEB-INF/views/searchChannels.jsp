<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui form segment">
	<h1 class="ui dividing header">Search Channels</h1>
	<div class="field">
		<label>Search term</label> <input id="searchTerm" type="text" placeholder="Enter your search term here (name, description, etc.)">
	</div>
	<div class="ui submit button" onClick="searchChannels();">Search</div>
</div>
<script type="text/javascript">
	$('input[type=text]').keypress(function (e) {
	  if (e.which == 13) {
	    searchChannels();
	    return false;
	  }
	});
</script>
<jsp:include page="viewChannels.jsp" />