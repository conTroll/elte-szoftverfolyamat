<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="uploadImage" method="post" enctype="multipart/form-data"
	class="ui form">
	<div class="ui card">
		<div class="ui image">
			<img id="preview" src="<c:url value="/getImage/${imageId}"/>"
				class="visible content">
		</div>
		<div class="content">
			<div class="meta">
				<div onClick="$('#fileSelector').click();"
					class="ui blue fluid button">Select</div>
				<br />
				<button class="ui blue fluid submit button">Save</button>
			</div>
		</div>
	</div>
	<input id="fileSelector" style="visibility: hidden;" type="file"
		name="file" />
</form>
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}

	$("#fileSelector").change(function() {
		readURL(this);
	});
</script>