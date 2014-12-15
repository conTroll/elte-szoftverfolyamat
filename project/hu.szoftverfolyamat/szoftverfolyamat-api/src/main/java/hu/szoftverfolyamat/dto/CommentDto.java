package hu.szoftverfolyamat.dto;

public class CommentDto {

	private long commentId;
	private String text;
	private long postId;
	private long authorCredentialId;
	private UserProfileDataDto userProfileDataDto;
	private String creationDate;

	public long getAuthorCredentialId() {
		return this.authorCredentialId;
	}

	public long getCommentId() {
		return this.commentId;
	}

	public String getCreationDate() {
		return this.creationDate;
	}

	public long getPostId() {
		return this.postId;
	}

	public String getText() {
		return this.text;
	}

	public UserProfileDataDto getUserProfileDataDto() {
		return this.userProfileDataDto;
	}

	public void setAuthorCredentialId(long authorCredentialId) {
		this.authorCredentialId = authorCredentialId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUserProfileDataDto(UserProfileDataDto userProfileDataDto) {
		this.userProfileDataDto = userProfileDataDto;
	}
}
