package hu.szoftverfolyamat.dto;

import lombok.Data;

@Data
public class CommentDto {

	private long commentId;
	private String text;
	private long postId;
	private long authorCredentialId;
	private UserProfileDataDto userProfileDataDto;
	private String creationDate;
}
