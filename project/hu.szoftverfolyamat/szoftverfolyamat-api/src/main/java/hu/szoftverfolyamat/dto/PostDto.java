package hu.szoftverfolyamat.dto;

import java.util.List;

public class PostDto {

	private long postId;
	private String text;
	private long authorCredentialId;
	private String creationDate;
	private UserProfileDataDto userProfileDataDto;
	private List<CommentDto> commentDtos;

	public long getAuthorCredentialId() {
		return this.authorCredentialId;
	}

	public List<CommentDto> getCommentDtos() {
		return this.commentDtos;
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

	public void setCommentDtos(List<CommentDto> commentDtos) {
		this.commentDtos = commentDtos;
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
