package hu.szoftverfolyamat.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ChannelPostCommentDto {

	private Long id;
	private ChannelPostDto post;
	private UserProfileDataDto author;
	private String text;
	private Date creationDate;

}
