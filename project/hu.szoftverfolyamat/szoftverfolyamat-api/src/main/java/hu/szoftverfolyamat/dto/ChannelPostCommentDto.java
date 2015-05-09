package hu.szoftverfolyamat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChannelPostCommentDto {

	private Long id;
	private ChannelPostDto post;
	private UserProfileDataDto author;
	private String text;
	private Date creationDate;

}
