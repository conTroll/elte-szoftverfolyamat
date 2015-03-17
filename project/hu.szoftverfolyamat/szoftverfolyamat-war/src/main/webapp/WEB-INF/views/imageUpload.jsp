<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form action="uploadImage" method="post"
	enctype="multipart/form-data" class="ui form">
	<div class="field">
		<label>Select image:</label>
		<form:input type="file" name="file" />
	</div>
	<form:button class="ui blue submit button">Upload</form:button>
</form:form>