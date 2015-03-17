package hu.szoftverfolyamat.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostDto {

	private long postId;
	private String text;
	private long authorCredentialId;
	private String creationDate;
	private UserProfileDataDto userProfileDataDto;
	private List<CommentDto> commentDtos;
}
