<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ui segment">
	<div class="ui reply form">
		<div class="field">
			<textarea id="postText"></textarea>
		</div>
		<div class="ui blue labeled submit icon button"
			onclick="submitPost();">
			<i class="icon edit"></i> What's in your mind
		</div>
	</div>
</div>
<div class="ui segment">
	<div class="ui comments">
		<h3 class="ui dividing header">News</h3>
		<c:forEach items="${postList}" var="post">
			<div id="post${post.postId}" class="comment">
				<div class="avatar">
					<img src="/images/avatar/small/joe.jpg">
				</div>
				<div class="content">
					<a class="author">${post.userProfileDataDto.fullName}</a>
					<div class="metadata">
						<span class="date">${post.creationDate}</span>
					</div>
					<div class="text">${post.text}</div>
					<div class="actions">
						<a class="replyButton"> Reply </a>
						<c:if
							test="${(currentUserId == post.userProfileDataDto.credentialId)}">
							<a class="deletePost"> Delete </a>
						</c:if>
					</div>
				</div>
			</div>
			<div class="comments" style="margin: 2% 0% 2% 5%;">
				<c:forEach items="${post.commentDtos}" var="comm">
				<div id="comment${comm.commentId}" class="comment">
					<div class="avatar">
						<img src="/images/avatar/small/joe.jpg">
					</div>
					<div class="content">
						<a class="author">${comm.userProfileDataDto.fullName}</a>
						<div class="metadata">
							<span class="date">${comm.creationDate}</span>
						</div>
						<div class="text">${comm.text}</div>
						<div class="actions">
							<c:if
								test="${(currentUserId == post.userProfileDataDto.credentialId)}">
								<a class="deleteComment"> Delete </a>
							</c:if>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
	<script type="text/javascript">
		$(".deletePost").click(function() {
			deletePost($(this).parent().parent().parent().attr('id'));
		});
		$(".deleteComment").click(function() {
			deleteComment($(this).parent().parent().parent().attr('id'));
		});
		$(".replyButton").click(function() {
			if(($(this).parent().parent().parent()).has( ".ui.reply.form" ).length == 0) {
				$( '<div style="display: none;" class="ui reply form">' +
					'<div class="field">' +
					'<textarea id="replyText"></textarea>' +
					'</div>' +
					'<div class="ui blue labeled submit icon button"' +
					'id="submitComment">' +
					'<i class="icon edit"></i> Send Reply' +
					'</div>' +
					'</div>' ).appendTo($(this).parent().parent().parent()).slideDown("fast");
					$("#submitComment").click(function () {
						submitComment($(this).parent().parent().attr('id'));
					});
			} else {
				$(this).parent().parent().parent().find( ".ui.reply.form" ).slideUp("fast", function() { $( this ).remove(); } );
			}
		});
	</script>
</div>